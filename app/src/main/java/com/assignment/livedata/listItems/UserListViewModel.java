package com.assignment.livedata.listItems;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.os.AsyncTask;

import com.assignment.livedata.db.AppDatabase;
import com.assignment.livedata.db.UserModel;
import com.assignment.livedata.network.DataCallback;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class UserListViewModel extends AndroidViewModel {

    private  final LiveData<List<UserModel>> itemAndPersonList;

    private AppDatabase appDatabase;

    public UserListViewModel(Application application) {
        super(application);

        appDatabase = AppDatabase.getDatabase(this.getApplication());

        itemAndPersonList = appDatabase.itemAndPersonModel().getAllUserItems();
    }


    public LiveData<List<UserModel>> getItemAndPersonList() {
//        LiveData<List<UserModel>> list = Network.getProjectList();

        return itemAndPersonList;

    }

    public void deleteItem() {
        new deleteAsyncTask(appDatabase).execute();
    }

    private static class deleteAsyncTask extends AsyncTask<Void, Void, Void> {

        private AppDatabase db;

        deleteAsyncTask(AppDatabase appDatabase) {
            db = appDatabase;
        }

        @Override
        protected Void doInBackground(final Void... params) {
            db.itemAndPersonModel().deleteAllUsers();
            return null;
        }
    }


    //This method is using Retrofit to get the JSON data from URL
    public void loadUser() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(DataCallback.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final MutableLiveData<List<UserModel>> data = new MutableLiveData<>();

        DataCallback api = retrofit.create(DataCallback.class);
        Call<List<UserModel>> call = api.getUsers();


        call.enqueue(new Callback<List<UserModel>>() {
            @Override
            public void onResponse(Call<List<UserModel>> call, Response<List<UserModel>> response) {

                //finally we are setting the list to our MutableLiveData
                data.setValue(response.body());
                for(UserModel model : response.body()){
                    new addAsyncTask(appDatabase).execute(model);
                }
            }

            @Override
            public void onFailure(Call<List<UserModel>> call, Throwable t) {

            }
        });
    }

    private static class addAsyncTask extends AsyncTask<UserModel, Void, Void> {

        private AppDatabase db;

        addAsyncTask(AppDatabase appDatabase) {
            db = appDatabase;
        }

        @Override
        protected Void doInBackground(final UserModel... params) {
            db.itemAndPersonModel().addUser(params[0]);
            return null;
        }

    }

}

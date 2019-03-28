package com.assignment.livedata.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;



@Database(entities = {UserModel.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase instance;

    public static AppDatabase getDatabase(Context context) {
        if (instance == null) {
            instance =
                    Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "user_db")
                            .build();
        }
        return instance;
    }

    public static void destroyInstance() {
        instance = null;
    }

    public abstract UserModelDao itemAndPersonModel();

   /* private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            LiveData<List<UserModel>> list = Network.getProjectList();

        }
    };

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

    }*/
}

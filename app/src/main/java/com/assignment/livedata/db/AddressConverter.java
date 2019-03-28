package com.assignment.livedata.db;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

public class AddressConverter {

    @TypeConverter
    public Address storedStringToAddress(String value) {
        Gson gson = new Gson();
        Type type = new TypeToken<Address>() {}.getType();
        return gson.fromJson(value, type);
    }

    @TypeConverter
    public String addressToStoredString(Address address) {
        Gson gson = new Gson();
        return gson.toJson(address);
    }
}

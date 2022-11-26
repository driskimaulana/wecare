package com.kelompok4.wecare.viewmodel.utils;

import com.google.gson.Gson;

public class GsonUtils {

    private static Gson gson;

    public static Gson getGson() {
        if (gson == null) {
            gson = new Gson();
        }
        return gson;
    }

}

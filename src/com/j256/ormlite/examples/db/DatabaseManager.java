package com.j256.ormlite.examples.db;

import android.content.Context;

public class DatabaseManager {

    private static DatabaseManager mInstance;
    private static DatabaseHelper mHelper;

    public static DatabaseManager getInstance(Context context) {
        if (null == mInstance) {
            mInstance = new DatabaseManager(context);
        }

        return mInstance;
    }

    private DatabaseManager(Context context) {
        mHelper = new DatabaseHelper(context);
    }

    public DatabaseHelper getDatabaseHelper() {
        return mHelper;
    }
}

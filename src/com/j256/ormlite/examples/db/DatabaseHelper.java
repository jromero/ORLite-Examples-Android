package com.j256.ormlite.examples.db;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.examples.abstracted.data.BaseInfo;
import com.j256.ormlite.examples.abstracted.data.ExtendedInfo1;
import com.j256.ormlite.examples.abstracted.data.ExtendedInfo2;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    public static final String TAG = "DatabaseHelper";
    private static final String DATABASE_NAME = "ormlite.sqlite";
    private static final int DATABASE_VERSION = 1;
    private Dao<BaseInfo, Integer> mAbstractedBaseInfoDao;
    private Dao<ExtendedInfo1, Integer> mAbstractedExtendedInfo1Dao;
    private Dao<ExtendedInfo2, Integer> mAbstractedExtendedInfo2Dao;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, BaseInfo.class);
        } catch (java.sql.SQLException e) {
            Log.e(TAG, "Can't create table", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource,
            int oldVersion, int newVersion) {
        try {
            List<String> allSql = new ArrayList<String>();
            switch (oldVersion) {
            case 1:
                // this is where we want to execute all further update queries
                break;
            }
            for (String sql : allSql) {
                db.execSQL(sql);
            }
        } catch (SQLException e) {
            Log.e(TAG, "exception during onUpgrade", e);
            throw new RuntimeException(e);
        }
    }

    /*********************************************************************
     * Daos
     */

    /**
     * Get abstracted dao
     * 
     * @return
     * @throws java.sql.SQLException
     */
    public Dao<BaseInfo, Integer> getAbstractedBaseInfoDao()
            throws java.sql.SQLException {

        if (mAbstractedBaseInfoDao == null) {
            mAbstractedBaseInfoDao = getDao(BaseInfo.class);
        }

        return mAbstractedBaseInfoDao;
    }

    public Dao<ExtendedInfo1, Integer> getAbstractedExtendedInfo1Dao()
            throws java.sql.SQLException {

        if (mAbstractedExtendedInfo1Dao == null) {
            mAbstractedExtendedInfo1Dao = getDao(ExtendedInfo1.class);
        }

        return mAbstractedExtendedInfo1Dao;
    }

    public Dao<ExtendedInfo2, Integer> getAbstractedExtendedInfo2Dao()
            throws java.sql.SQLException {

        if (mAbstractedExtendedInfo2Dao == null) {
            mAbstractedExtendedInfo2Dao = getDao(ExtendedInfo2.class);
        }

        return mAbstractedExtendedInfo2Dao;
    }
}

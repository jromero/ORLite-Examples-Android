package com.j256.ormlite.examples.app;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.j256.ormlite.examples.R;
import com.j256.ormlite.examples.abstracted.data.BaseInfo;
import com.j256.ormlite.examples.abstracted.data.ExtendedInfo1;
import com.j256.ormlite.examples.abstracted.data.ExtendedInfo2;
import com.j256.ormlite.examples.db.DatabaseManager;
import com.j256.ormlite.table.TableUtils;

public class AbstractedActivity extends Activity implements OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abstracted);

        ((Button) findViewById(R.id.button1)).setOnClickListener(this);
        ((Button) findViewById(R.id.button2)).setOnClickListener(this);
        ((Button) findViewById(R.id.button3)).setOnClickListener(this);
        ((Button) findViewById(R.id.button4)).setOnClickListener(this);
        ((Button) findViewById(R.id.button5)).setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public void createOneOfEachUsingBaseInfoDao() throws SQLException {
        ExtendedInfo1 exInfo1 = new ExtendedInfo1();
        exInfo1.setSharedProperty("Created as ExtendedInfo1");

        ExtendedInfo2 exInfo2 = new ExtendedInfo2();
        exInfo2.setSharedProperty("Created as ExtendedInfo2");

        DatabaseManager.getInstance(this).getDatabaseHelper()
                .getAbstractedBaseInfoDao().create(exInfo1);

        DatabaseManager.getInstance(this).getDatabaseHelper()
                .getAbstractedBaseInfoDao().create(exInfo2);
    }

    public void createOneOfEachUsingExtendedInfoDao() throws SQLException {
        ExtendedInfo1 exInfo1 = new ExtendedInfo1();
        exInfo1.setSharedProperty("Created as ExtendedInfo1");

        ExtendedInfo2 exInfo2 = new ExtendedInfo2();
        exInfo2.setSharedProperty("Created as ExtendedInfo2");

        DatabaseManager.getInstance(this).getDatabaseHelper()
                .getAbstractedExtendedInfo1Dao().create(exInfo1);

        DatabaseManager.getInstance(this).getDatabaseHelper()
                .getAbstractedExtendedInfo2Dao().create(exInfo2);
    }

    public List<BaseInfo> getAllUsingBaseInfoDao() throws SQLException {

        List<BaseInfo> all = DatabaseManager.getInstance(this)
                .getDatabaseHelper().getAbstractedBaseInfoDao().queryForAll();

        return all;
    }

    public List<BaseInfo> getAllUsingExtendedInfoDao() throws SQLException {

        List<BaseInfo> all = new ArrayList<BaseInfo>();

        all.addAll(DatabaseManager.getInstance(this).getDatabaseHelper()
                .getAbstractedExtendedInfo1Dao().queryForAll());

        all.addAll(DatabaseManager.getInstance(this).getDatabaseHelper()
                .getAbstractedExtendedInfo2Dao().queryForAll());

        return all;
    }

    public void clearInfoTable() throws SQLException {
        TableUtils.clearTable(DatabaseManager.getInstance(this)
                .getDatabaseHelper().getConnectionSource(), BaseInfo.class);
    }

    @Override
    public void onClick(View button) {
        switch (button.getId()) {
        case R.id.button1:
            try {
                createOneOfEachUsingBaseInfoDao();
                Toast.makeText(this, "Done", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                new AlertDialog.Builder(this).setTitle("Error")
                        .setMessage(e.getMessage()).create().show();
            }
            break;
        case R.id.button2:
            try {
                createOneOfEachUsingExtendedInfoDao();
                Toast.makeText(this, "Done", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                new AlertDialog.Builder(this).setTitle("Error")
                        .setMessage(e.getMessage()).create().show();
            }
            break;
        case R.id.button3:
            try {
                List<BaseInfo> baseInfos = getAllUsingBaseInfoDao();

                // we'll never get this far
                List<String> bString = new ArrayList<String>();
                for (BaseInfo i : baseInfos) {
                    bString.add(i.toString());
                }

                new AlertDialog.Builder(this)
                        .setTitle("Items")
                        .setItems(
                                bString.toArray(new CharSequence[bString.size()]),
                                null).setPositiveButton("Ok", null).create()
                        .show();
            } catch (Exception e) {
                new AlertDialog.Builder(this).setTitle("Error")
                        .setMessage(Log.getStackTraceString(e)).create().show();
            }
            break;
        case R.id.button4:
            try {
                List<BaseInfo> baseInfos = getAllUsingExtendedInfoDao();
                List<String> bString = new ArrayList<String>();
                for (BaseInfo i : baseInfos) {
                    bString.add(i.toString());
                }

                new AlertDialog.Builder(this)
                        .setTitle("Items")
                        .setItems(
                                bString.toArray(new CharSequence[bString.size()]),
                                null)
                        .setPositiveButton("Ok",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog,
                                            int which) {
                                        new AlertDialog.Builder(
                                                AbstractedActivity.this)
                                                .setMessage(
                                                        "Did you notice that each entry is duplicated because we queried for all on two Daos. "
                                                                + "Furthermore, each entry was casted as the respective Dao that was called, "
                                                                + "not what it was original inserted as.")
                                                .create().show();
                                    }
                                }).create().show();
            } catch (Exception e) {
                new AlertDialog.Builder(this).setTitle("Error")
                        .setMessage(e.getMessage()).create().show();
            }
            break;
        case R.id.button5:
            try {
                clearInfoTable();
                new AlertDialog.Builder(this).setMessage("Done").create()
                        .show();
            } catch (Exception e) {
                new AlertDialog.Builder(this).setTitle("Error")
                        .setMessage(e.getMessage()).create().show();
            }
            break;
        default:
            break;
        }
    }
}

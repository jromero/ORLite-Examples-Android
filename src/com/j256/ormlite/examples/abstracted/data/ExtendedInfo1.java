package com.j256.ormlite.examples.abstracted.data;

import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "abstracted_info")
public class ExtendedInfo1 extends BaseInfo {

    public ExtendedInfo1() {
        // FOR ORMLITE
    }

    @Override
    public String getRecordType() {
        return "ExtendedInfo1";
    }

}

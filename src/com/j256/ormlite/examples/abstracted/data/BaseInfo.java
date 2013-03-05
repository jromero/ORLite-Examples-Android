package com.j256.ormlite.examples.abstracted.data;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "abstracted_info")
public abstract class BaseInfo implements IMethods {

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField
    private String sharedProperty;

    public BaseInfo() {
        // FOR ORMLITE
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSharedProperty() {
        return sharedProperty;
    }

    public void setSharedProperty(String sharedProperty) {
        this.sharedProperty = sharedProperty;
    }

    @Override
    public String toString() {
        return String.format("id:%d, sharedProperty:%s, recordType:%s",
                getId(), getSharedProperty(), getRecordType());
    }
}

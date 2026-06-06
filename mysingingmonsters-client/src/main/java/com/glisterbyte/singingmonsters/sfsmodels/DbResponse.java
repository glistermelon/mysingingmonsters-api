package com.glisterbyte.singingmonsters.sfsmodels;

public abstract class DbResponse extends SfsEventModel {

    public long last_updated;
    public long server_time;

    public abstract int getElementCount();

    public boolean isEmpty() {
        return getElementCount() == 0;
    }

}

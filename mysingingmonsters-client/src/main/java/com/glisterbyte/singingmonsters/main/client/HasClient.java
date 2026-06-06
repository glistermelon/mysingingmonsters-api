package com.glisterbyte.singingmonsters.main.client;

import com.glisterbyte.singingmonsters.main.catalog.Catalog;

public interface HasClient {

    Client getClient();

    default Catalog getCatalog() {
        return getClient().getCatalog();
    }

}
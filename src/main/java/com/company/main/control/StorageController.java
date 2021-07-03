package com.company.main.control;

import com.company.main.model.Storage;

public class StorageController {
    private final Storage storage;

    public StorageController(Storage storage) {
        this.storage = storage;
    }
    public Storage getStorage() {
        return storage;
    }
}

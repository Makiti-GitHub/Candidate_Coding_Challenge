package com.makiti.test.controller;

import com.makiti.test.entity.NumberStore;

import javax.ejb.*;
import javax.inject.Inject;

@Singleton
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
@Lock(LockType.WRITE)
public class NumberGenerator {

    @Inject
    private NumberStoreController numberStoreController;

    public long getCurrentNumber() {
        final NumberStore store = numberStoreController.getInstance();
        return store.getLastNumber();
    }

    public long getNextNumber() {
        final NumberStore store = numberStoreController.getInstance();
        numberStoreController.updateLastNumber(store.getLastNumber() + 1);
        return numberStoreController.getInstance().getLastNumber();
    }
}

package com.makiti.test.controller;

import com.makiti.test.entity.NumberStore;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

public class NumberStoreController {

    private static NumberStore instance;

    @PersistenceContext
    private EntityManager em;

    public NumberStore getInstance() {
        if (instance != null) {
            return instance;
        }

        try {
            final NumberStore savedStore = em.createNamedQuery(NumberStore.FIND_NUMBER_STORE, NumberStore.class)
                    .setMaxResults(1)
                    .getSingleResult();
            instance = savedStore;

        } catch (NoResultException nre) {
            final NumberStore store = new NumberStore();
            store.setLastNumber(0);
            em.persist(store);
            instance = store;
        }

        return instance;
    }

    public void updateLastNumber(final long number) {
        final NumberStore store = this.getInstance();
        store.setLastNumber(number);
        em.persist(store);
    }
}

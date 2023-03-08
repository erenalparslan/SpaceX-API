package com.erenalparslan.spacexapijava.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.erenalparslan.spacexapijava.dao.ShipDao;
import com.erenalparslan.spacexapijava.database.SpacexDatabase;
import com.erenalparslan.spacexapijava.model.Ship;

import java.util.List;

public class ShipRepository {

    ShipDao shipDao;

    public ShipRepository(Context context) {
        SpacexDatabase myDatabase = SpacexDatabase.getDatabase(context);
        shipDao = myDatabase.shipDao();
    }

    public void insertShip(Ship ship) {
        shipDao.insertShip(ship);
    }

    public void deleteShip(Ship ship) {shipDao.deleteShip(ship);
    }

    public LiveData<List<Ship>> getShip() {
        return shipDao.shipList();
    }
}

package com.erenalparslan.spacexapijava.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.erenalparslan.spacexapijava.dao.RocketDao;
import com.erenalparslan.spacexapijava.database.SpacexDatabase;
import com.erenalparslan.spacexapijava.model.Rocket;

import java.util.ArrayList;
import java.util.List;

public class RocketRepository {

    RocketDao rocketDao;

    public RocketRepository(Context context) {
        SpacexDatabase myDatabase = SpacexDatabase.getDatabase(context);
       rocketDao = myDatabase.rocketDao();
    }

    public void insertRocket(Rocket rocket) {
        rocketDao.insertRocket(rocket);
    }

    public void deleteRocket(Rocket rocket) {
        rocketDao.deleteRocket(rocket);
    }

    public LiveData<List<Rocket>> getUsers() {
        return rocketDao.rocketList();
    }

}

package com.erenalparslan.spacexapijava.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;


import com.erenalparslan.spacexapijava.dao.CapsuleDao;
import com.erenalparslan.spacexapijava.database.SpacexDatabase;
import com.erenalparslan.spacexapijava.model.Capsule;


import java.util.List;

public class CapsuleRepository {

    CapsuleDao capsuleDao;

    public CapsuleRepository(Context context) {
        SpacexDatabase myDatabase = SpacexDatabase.getDatabase(context);
        capsuleDao = myDatabase.capsuleDao();
    }

    public void insertCapsule(Capsule capsule) {
        capsuleDao.insertCapsule(capsule);
    }

    public void deleteCapsule(Capsule capsule) {capsuleDao.deleteCapsule(capsule);
    }

    public LiveData<List<Capsule>> getCpsule() {
        return capsuleDao.capsuleList();
    }
}

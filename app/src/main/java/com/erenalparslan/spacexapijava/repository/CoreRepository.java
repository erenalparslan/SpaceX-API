package com.erenalparslan.spacexapijava.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.erenalparslan.spacexapijava.dao.CoreDao;
import com.erenalparslan.spacexapijava.database.SpacexDatabase;
import com.erenalparslan.spacexapijava.model.Core;


import java.util.List;

public class CoreRepository {

    CoreDao coreDao;

    public CoreRepository(Context context) {
        SpacexDatabase myDatabase = SpacexDatabase.getDatabase(context);
        coreDao = myDatabase.coreDao();
    }

    public void insertCore(Core core) {
        coreDao.insertCore(core);
    }

    public void deleteCore(Core core) {coreDao.deleteCore(core);
    }

    public LiveData<List<Core>> getCore() {
        return coreDao.coreList();
    }
}

package com.erenalparslan.spacexapijava.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;


import com.erenalparslan.spacexapijava.model.Core;

import java.util.List;

@Dao
public interface CoreDao {

    @Insert
    void insertCore(Core core);
    @Delete
    void deleteCore(Core core);
    @Query("Select * from Core")
    LiveData<List<Core>> coreList();
}

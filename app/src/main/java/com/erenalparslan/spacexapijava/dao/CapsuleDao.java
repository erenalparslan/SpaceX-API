package com.erenalparslan.spacexapijava.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.erenalparslan.spacexapijava.model.Capsule;
import java.util.List;

@Dao
public interface CapsuleDao {


    @Insert
    void insertCapsule(Capsule capsule);
    @Delete
    void deleteCapsule(Capsule capsule);

    @Query("Select * from Capsule")
    LiveData<List<Capsule>> capsuleList();
}

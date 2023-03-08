package com.erenalparslan.spacexapijava.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.erenalparslan.spacexapijava.model.Rocket;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface RocketDao {

    @Insert
    void insertRocket(Rocket rocket);
    @Delete
    void deleteRocket(Rocket rocket);
    @Query("Select * from Rocket")
    LiveData<List<Rocket>> rocketList();

}

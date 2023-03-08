package com.erenalparslan.spacexapijava.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;


import com.erenalparslan.spacexapijava.model.Ship;

import java.util.List;
@Dao
public interface ShipDao {

    @Insert
    void insertShip(Ship ship);

    @Delete
    void deleteShip(Ship ship);

    @Query("Select * from Ship")
    LiveData<List<Ship>> shipList();
}

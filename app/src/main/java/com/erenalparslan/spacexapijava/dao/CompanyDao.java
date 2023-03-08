package com.erenalparslan.spacexapijava.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;


import com.erenalparslan.spacexapijava.model.Company;

import java.util.List;
@Dao
public interface CompanyDao {


    @Insert
    void insertCompany(Company company);

    @Delete
    void deleteCompany(Company company);

    @Query("Select * from Company")
    LiveData<List<Company>> companyList();
}

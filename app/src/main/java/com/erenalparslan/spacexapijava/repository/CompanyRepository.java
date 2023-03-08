package com.erenalparslan.spacexapijava.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.erenalparslan.spacexapijava.dao.CompanyDao;
import com.erenalparslan.spacexapijava.database.SpacexDatabase;
import com.erenalparslan.spacexapijava.model.Company;


import java.util.List;

public class CompanyRepository {

    CompanyDao companyDao;

    public CompanyRepository(Context context) {
        SpacexDatabase myDatabase = SpacexDatabase.getDatabase(context);
        companyDao = myDatabase.companyDao();
    }

    public void insertCompany(Company company) {
        companyDao.insertCompany(company);
    }

    public void deleteCompany(Company company) {companyDao.deleteCompany(company);
    }

    public LiveData<List<Company>> getCompany() {
        return companyDao.companyList();
    }
}

package com.erenalparslan.spacexapijava;

import static com.google.common.truth.Truth.assertThat;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.erenalparslan.spacexapijava.dao.CompanyDao;
import com.erenalparslan.spacexapijava.database.SpacexDatabase;
import com.erenalparslan.spacexapijava.model.Company;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import kotlin.ExperimentalMultiplatform;

@ExperimentalMultiplatform
@RunWith(AndroidJUnit4.class)
public class CompanyDaoTest {

    SpacexDatabase spacexDatabase;
    CompanyDao companyDao;
    List<Company> companyList;
    LiveDataUtil liveDataUtil;

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void setup() {

        spacexDatabase = Room.inMemoryDatabaseBuilder(ApplicationProvider.getApplicationContext(), SpacexDatabase.class).allowMainThreadQueries().build();
        companyDao = spacexDatabase.companyDao();


    }


    @After
    public void teardown() {
        spacexDatabase.close();

    }


    @Test
    public void isEmptyDatabase() {

        assertThat(spacexDatabase).isNotNull();
    }

    @Test
    public void isEmptyDao() {

        assertThat(companyDao).isNotNull();
    }


    @Test
    public void insertcompanySuccess() throws InterruptedException {

        Company company = new Company("name",  "founder",  "founded",  "employees",  "ceo",  "coo",  "cto_propulsion",  "valuation");
        companyDao.insertCompany(company);
        companyDao.insertCompany(company);
        companyDao.insertCompany(company);
        companyDao.insertCompany(company);
        companyList = liveDataUtil.getOrAwaitValue(companyDao.companyList());

        assertThat(companyList.size()).isEqualTo(4);

    }

    @Test
    public void insertEmptycompany() throws InterruptedException {

        Company company = new Company("name",  "founder",  "founded",  "employees",  "ceo",  "coo",  "cto_propulsion",  "valuation");
        companyDao.insertCompany(company);
        companyList = liveDataUtil.getOrAwaitValue(companyDao.companyList());

        assertThat(companyList.size()).isGreaterThan(0);

    }


    @Test
    public void deletecompany() throws InterruptedException {

        Company company = new Company("name",  "founder",  "founded",  "employees",  "ceo",  "coo",  "cto_propulsion",  "valuation");
        companyDao.insertCompany(company);
        companyList = liveDataUtil.getOrAwaitValue(companyDao.companyList());
        companyDao.deleteCompany(companyList.get(0));
        companyList = liveDataUtil.getOrAwaitValue(companyDao.companyList());
        assertThat(companyList.size()).isEqualTo(0);

    }

    @Test
    public void companyListVerify() throws InterruptedException {

        Company company = new Company("name",  "founder",  "founded",  "employees",  "ceo",  "coo",  "cto_propulsion",  "valuation");
        companyDao.insertCompany(company);
        companyList = liveDataUtil.getOrAwaitValue(companyDao.companyList());

        assertThat(companyList).isNotNull();

    }
}

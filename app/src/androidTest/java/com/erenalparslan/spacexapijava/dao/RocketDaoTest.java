package com.erenalparslan.spacexapijava.dao;

import static com.google.common.truth.Truth.assertThat;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.erenalparslan.spacexapijava.LiveDataUtil;
import com.erenalparslan.spacexapijava.dao.RocketDao;
import com.erenalparslan.spacexapijava.database.SpacexDatabase;
import com.erenalparslan.spacexapijava.model.Rocket;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import kotlin.ExperimentalMultiplatform;

@ExperimentalMultiplatform
@RunWith(AndroidJUnit4.class)
public class RocketDaoTest {
    SpacexDatabase spacexDatabase;
    RocketDao rocketDao;
    List<Rocket> rocketList;
    LiveDataUtil liveDataUtil;

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule=new InstantTaskExecutorRule();

    @Before
    public void setup() {

        spacexDatabase = Room.inMemoryDatabaseBuilder(ApplicationProvider.getApplicationContext(),SpacexDatabase.class).allowMainThreadQueries().build();
        rocketDao = spacexDatabase.rocketDao();


    }


    @After
    public void teardown(){
        spacexDatabase.close();

    }


    @Test
    public void isEmptyDatabase(){

        assertThat(spacexDatabase).isNotNull();
    }

    @Test
    public void isEmptyDao(){

        assertThat(rocketDao).isNotNull();
    }




    @Test
    public void insertRocket() throws InterruptedException {

        Rocket rocket = new Rocket("a","a",false,"t");
        rocketDao.insertRocket(rocket);
        rocketDao.insertRocket(rocket);
        rocketDao.insertRocket(rocket);
        rocketDao.insertRocket(rocket);
       rocketList= liveDataUtil.getOrAwaitValue(rocketDao.rocketList());

        assertThat(rocketList.size()).isEqualTo(4);

    }

    @Test
    public void insertEmptyRocket() throws InterruptedException {

        Rocket rocket = new Rocket("","",false,"");
        rocketDao.insertRocket(rocket);
        rocketList= liveDataUtil.getOrAwaitValue(rocketDao.rocketList());

        assertThat(rocketList.size()).isGreaterThan(0);

    }


    @Test
    public void deleteRocket() throws InterruptedException{

        Rocket rocket = new Rocket("a","a",false,"t");
        rocketDao.insertRocket(rocket);
        rocketList= liveDataUtil.getOrAwaitValue(rocketDao.rocketList());
        rocketDao.deleteRocket(rocketList.get(0));
        rocketList= liveDataUtil.getOrAwaitValue(rocketDao.rocketList());
        assertThat(rocketList.size()).isEqualTo(0);

    }

    @Test
    public void rccketListVerify() throws InterruptedException {

        Rocket rocket = new Rocket("Rocket","falcon1",false,"USA");
        rocketDao.insertRocket(rocket);
        rocketList= liveDataUtil.getOrAwaitValue(rocketDao.rocketList());

        assertThat(rocketList).isNotNull();

    }





}

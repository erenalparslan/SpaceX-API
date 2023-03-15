package com.erenalparslan.spacexapijava;

import static com.google.common.truth.Truth.assertThat;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.erenalparslan.spacexapijava.dao.ShipDao;
import com.erenalparslan.spacexapijava.database.SpacexDatabase;
import com.erenalparslan.spacexapijava.model.Ship;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import kotlin.ExperimentalMultiplatform;


@ExperimentalMultiplatform
@RunWith(AndroidJUnit4.class)
public class ShipDaoTest {


    SpacexDatabase spacexDatabase;
    ShipDao shipDao;
    List<Ship> ShipList;
    LiveDataUtil liveDataUtil;

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void setup() {

        spacexDatabase = Room.inMemoryDatabaseBuilder(ApplicationProvider.getApplicationContext(), SpacexDatabase.class).allowMainThreadQueries().build();
        shipDao = spacexDatabase.shipDao();


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

        assertThat(shipDao).isNotNull();
    }


    @Test
    public void insertShipSuccess() throws InterruptedException {

        Ship Ship = new Ship("a", "a", "b", "t");
        shipDao.insertShip(Ship);
        shipDao.insertShip(Ship);
        shipDao.insertShip(Ship);
        shipDao.insertShip(Ship);
        ShipList = liveDataUtil.getOrAwaitValue(shipDao.shipList());

        assertThat(ShipList.size()).isEqualTo(4);

    }

    @Test
    public void insertEmptyShip() throws InterruptedException {

        Ship Ship = new Ship("", "a", "b", "t");
        shipDao.insertShip(Ship);
        ShipList = liveDataUtil.getOrAwaitValue(shipDao.shipList());

        assertThat(ShipList.size()).isGreaterThan(0);

    }


    @Test
    public void deleteShip() throws InterruptedException {

        Ship Ship = new Ship("", "a", "b", "t");
        shipDao.insertShip(Ship);
        ShipList = liveDataUtil.getOrAwaitValue(shipDao.shipList());
        shipDao.deleteShip(ShipList.get(0));
        ShipList = liveDataUtil.getOrAwaitValue(shipDao.shipList());
        assertThat(ShipList.size()).isEqualTo(0);

    }

    @Test
    public void ShipListVerify() throws InterruptedException {

        Ship Ship = new Ship("", "a", "b", "t");
        shipDao.insertShip(Ship);
        ShipList = liveDataUtil.getOrAwaitValue(shipDao.shipList());

        assertThat(ShipList).isNotNull();

    }
}

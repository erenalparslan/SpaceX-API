package com.erenalparslan.spacexapijava.dao;

import static com.google.common.truth.Truth.assertThat;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.erenalparslan.spacexapijava.LiveDataUtil;
import com.erenalparslan.spacexapijava.dao.CoreDao;
import com.erenalparslan.spacexapijava.dao.CoreDao;
import com.erenalparslan.spacexapijava.database.SpacexDatabase;
import com.erenalparslan.spacexapijava.model.Core;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import kotlin.ExperimentalMultiplatform;

@ExperimentalMultiplatform
@RunWith(AndroidJUnit4.class)
public class CoreDaoTest {

    SpacexDatabase spacexDatabase;
    CoreDao coreDao;
    List<Core> CoreList;
    LiveDataUtil liveDataUtil;

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void setup() {

        spacexDatabase = Room.inMemoryDatabaseBuilder(ApplicationProvider.getApplicationContext(), SpacexDatabase.class).allowMainThreadQueries().build();
        coreDao = spacexDatabase.coreDao();


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

        assertThat(coreDao).isNotNull();
    }


    @Test
    public void insertCoreSuccess() throws InterruptedException {

        Core Core = new Core("a", "a", "b" );
        coreDao.insertCore(Core);
        coreDao.insertCore(Core);
        coreDao.insertCore(Core);
        coreDao.insertCore(Core);
        CoreList = liveDataUtil.getOrAwaitValue(coreDao.coreList());

        assertThat(CoreList.size()).isEqualTo(4);

    }

    @Test
    public void insertEmptyCore() throws InterruptedException {

        Core Core = new Core("", "a", "b");
        coreDao.insertCore(Core);
        CoreList = liveDataUtil.getOrAwaitValue(coreDao.coreList());

        assertThat(CoreList.size()).isGreaterThan(0);

    }


    @Test
    public void deleteCore() throws InterruptedException {

        Core Core = new Core("", "a", "b");
        coreDao.insertCore(Core);
        CoreList = liveDataUtil.getOrAwaitValue(coreDao.coreList());
        coreDao.deleteCore(CoreList.get(0));
        CoreList = liveDataUtil.getOrAwaitValue(coreDao.coreList());
        assertThat(CoreList.size()).isEqualTo(0);

    }

    @Test
    public void CoreListVerify() throws InterruptedException {

        Core Core = new Core("", "a", "b");
        coreDao.insertCore(Core);
        CoreList = liveDataUtil.getOrAwaitValue(coreDao.coreList());

        assertThat(CoreList).isNotNull();

    }
}

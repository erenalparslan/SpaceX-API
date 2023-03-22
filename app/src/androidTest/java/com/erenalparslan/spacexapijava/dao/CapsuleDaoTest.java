package com.erenalparslan.spacexapijava.dao;

import static com.google.common.truth.Truth.assertThat;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.erenalparslan.spacexapijava.LiveDataUtil;
import com.erenalparslan.spacexapijava.dao.CapsuleDao;
import com.erenalparslan.spacexapijava.dao.RocketDao;
import com.erenalparslan.spacexapijava.database.SpacexDatabase;
import com.erenalparslan.spacexapijava.model.Capsule;
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
public class CapsuleDaoTest {

    SpacexDatabase spacexDatabase;
    CapsuleDao capsuleDao;
    List<Capsule> capsuleList;
    LiveDataUtil liveDataUtil;

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void setup() {

        spacexDatabase = Room.inMemoryDatabaseBuilder(ApplicationProvider.getApplicationContext(), SpacexDatabase.class).allowMainThreadQueries().build();
        capsuleDao = spacexDatabase.capsuleDao();


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

        assertThat(capsuleDao).isNotNull();
    }


    @Test
    public void insertCapsuleSuccess() throws InterruptedException {

        Capsule capsule = new Capsule("a", "a", "b", "t");
        capsuleDao.insertCapsule(capsule);
        capsuleDao.insertCapsule(capsule);
        capsuleDao.insertCapsule(capsule);
        capsuleDao.insertCapsule(capsule);
        capsuleList = liveDataUtil.getOrAwaitValue(capsuleDao.capsuleList());

        assertThat(capsuleList.size()).isEqualTo(4);

    }

    @Test
    public void insertEmptyCapsule() throws InterruptedException {

        Capsule capsule = new Capsule("", "a", "b", "t");
        capsuleDao.insertCapsule(capsule);
        capsuleList = liveDataUtil.getOrAwaitValue(capsuleDao.capsuleList());

        assertThat(capsuleList.size()).isGreaterThan(0);

    }


    @Test
    public void deleteCapsule() throws InterruptedException {

        Capsule capsule = new Capsule("", "a", "b", "t");
        capsuleDao.insertCapsule(capsule);
        capsuleList = liveDataUtil.getOrAwaitValue(capsuleDao.capsuleList());
        capsuleDao.deleteCapsule(capsuleList.get(0));
        capsuleList = liveDataUtil.getOrAwaitValue(capsuleDao.capsuleList());
        assertThat(capsuleList.size()).isEqualTo(0);

    }

    @Test
    public void capsuleListVerify() throws InterruptedException {

        Capsule capsule = new Capsule("", "a", "b", "t");
        capsuleDao.insertCapsule(capsule);
        capsuleList = liveDataUtil.getOrAwaitValue(capsuleDao.capsuleList());

        assertThat(capsuleList).isNotNull();

    }
}

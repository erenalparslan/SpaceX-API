package com.erenalparslan.spacexapijava;

import static com.google.common.truth.Truth.assertThat;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;

import com.erenalparslan.spacexapijava.dao.RocketDao;
import com.erenalparslan.spacexapijava.database.SpacexDatabase;
import com.erenalparslan.spacexapijava.model.Rocket;
import com.erenalparslan.spacexapijava.repository.RocketRepository;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import kotlin.ExperimentalMultiplatform;

@ExperimentalMultiplatform
public class RoomTest {
    SpacexDatabase spacexDatabase;
    Rocket rocket;
    RocketDao rocketDao;
    List<Rocket> rocketList;
    RocketRepository rocketRepository;
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
    public void insertRocket() {


        Rocket rocket = new Rocket();
        Rocket rocket1 = new Rocket();
        rocketDao.insertRocket(rocket);
        rocketDao.insertRocket(rocket1);

        LiveData<List<Rocket>> liveData = rocketDao.rocketList();
        liveData.observeForever(new Observer<List<Rocket>>() {
            @Override
            public void onChanged(List<Rocket> rockets) {
                assertThat(rockets.size()).isEqualTo(2);
            }
        });


    }

    @Test
    public void testDeleteRocket() throws InterruptedException {


        // 2. Veritabanına bir roket ekle ve sonra sil
        Rocket rocket = new Rocket();
        rocketDao.insertRocket(rocket);
        rocketDao.deleteRocket(rocket);

        // 3. LiveData'nın değerinin değişmesi bekleniyor
        CountDownLatch latch = new CountDownLatch(1);
        LiveData<List<Rocket>> liveData = rocketDao.rocketList();
        liveData.observeForever(new Observer<List<Rocket>>() {
            @Override
            public void onChanged(List<Rocket> rockets) {
                assertThat(rockets.size()).isEqualTo(0);
                latch.countDown();
                liveData.removeObserver(this);
            }
        });
        latch.await(2, TimeUnit.SECONDS);


    }

}

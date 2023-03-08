package com.erenalparslan.spacexapijava.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.erenalparslan.spacexapijava.dao.CapsuleDao;
import com.erenalparslan.spacexapijava.dao.CompanyDao;
import com.erenalparslan.spacexapijava.dao.CoreDao;
import com.erenalparslan.spacexapijava.dao.RocketDao;
import com.erenalparslan.spacexapijava.dao.ShipDao;
import com.erenalparslan.spacexapijava.model.Capsule;
import com.erenalparslan.spacexapijava.model.Company;
import com.erenalparslan.spacexapijava.model.Core;
import com.erenalparslan.spacexapijava.model.Rocket;
import com.erenalparslan.spacexapijava.model.Ship;

@Database(entities = {Rocket.class, Capsule.class, Ship.class, Core.class, Company.class},version = 1)
public abstract class SpacexDatabase extends RoomDatabase {

    public abstract RocketDao rocketDao();
    public abstract CapsuleDao capsuleDao();
    public abstract ShipDao shipDao();
    public abstract CoreDao coreDao();
    public abstract CompanyDao companyDao();


    private static SpacexDatabase INSTANCE;

    public static SpacexDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (SpacexDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    SpacexDatabase.class, "spacexdatabase")
                            // Wipes and rebuilds instead of migrating
                            // if no Migration object.
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }


}

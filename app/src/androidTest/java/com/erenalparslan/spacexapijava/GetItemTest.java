package com.erenalparslan.spacexapijava;

import static com.google.common.truth.Truth.assertThat;

import com.erenalparslan.spacexapijava.adapter.CapsuleAdapter;
import com.erenalparslan.spacexapijava.adapter.CompanyAdapter;
import com.erenalparslan.spacexapijava.adapter.CoreAdapter;
import com.erenalparslan.spacexapijava.adapter.RocketAdapter;
import com.erenalparslan.spacexapijava.adapter.ShipAdapter;
import com.erenalparslan.spacexapijava.model.Capsule;
import com.erenalparslan.spacexapijava.model.Company;
import com.erenalparslan.spacexapijava.model.Core;
import com.erenalparslan.spacexapijava.model.Rocket;
import com.erenalparslan.spacexapijava.model.Ship;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class GetItemTest {
    CompanyAdapter companyAdapter;
    CapsuleAdapter capsuleAdapter;
    RocketAdapter rocketAdapter;
    ShipAdapter shipAdapter;
    CoreAdapter coreAdapter;
    ArrayList<Capsule> capsules;
    ArrayList<Rocket> rockets;
    ArrayList<Core> cores;
    ArrayList<Ship> ships;
    Company company;
    @Before
    public void setup() {

        capsules = new ArrayList<Capsule>();
        rockets = new ArrayList<Rocket>();
        ships = new ArrayList<Ship>();
        cores = new ArrayList<Core>();
    }

    @Test
    public void CapsuleTestProductCountShouldBeTwoWhenTwoProductsAdded() {

        capsules.add(new Capsule());
        capsules.add(new Capsule());
        capsuleAdapter = new CapsuleAdapter(capsules);
        assertThat(capsuleAdapter.getItemCount()).isEqualTo(2);
    }

    @Test
    public void RocketTestProductCountShouldBeTwoWhenTwoProductsAdded() {

        rockets.add(new Rocket());
        rockets.add(new Rocket());
        rocketAdapter = new RocketAdapter(rockets);
        assertThat(rocketAdapter.getItemCount()).isEqualTo(2);
    }

    @Test
    public void ShipTestProductCountShouldBeTwoWhenTwoProductsAdded() {

        ships.add(new Ship());
        ships.add(new Ship());
        shipAdapter = new ShipAdapter(ships);
        assertThat(shipAdapter.getItemCount()).isEqualTo(2);
    }


    @Test
    public void CoreTestProductCountShouldBeTwoWhenTwoProductsAdded() {

        cores.add(new Core());
        cores.add(new Core());
        coreAdapter = new CoreAdapter(cores);
        assertThat(coreAdapter.getItemCount()).isEqualTo(2);
    }

    @Test
    public void CompanyTestProductCountShouldBeTwoWhenTwoProductsAdded() {


        companyAdapter = new CompanyAdapter(company);
        assertThat(companyAdapter.getItemCount()).isEqualTo(1);
    }
}
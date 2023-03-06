package com.erenalparslan.spacexapijava;

import static com.google.common.truth.Truth.assertThat;

import com.erenalparslan.spacexapijava.adapter.CapsuleAdapter;
import com.erenalparslan.spacexapijava.model.Capsule;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class GetItemTest {

    CapsuleAdapter capsuleAdapter;
    ArrayList<Capsule> capsules;

    @Before
    public void setup() {

        capsules = new ArrayList<Capsule>();

    }

    @Test
    public void testProductCountShouldBeTwoWhenTwoProductsAdded() {

        capsules.add(new Capsule());
        capsules.add(new Capsule());
        capsuleAdapter = new CapsuleAdapter(capsules);
        System.out.println(capsuleAdapter.getItemCount());
        assertThat(capsuleAdapter.getItemCount()).isEqualTo(2);
    }

}

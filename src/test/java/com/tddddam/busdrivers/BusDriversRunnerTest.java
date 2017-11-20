package com.tddddam.busdrivers;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class BusDriversRunnerTest {

    private BusDriversRunner pool;

    @Before
    public void setUpPool(){
        pool = new BusDriversRunner();
    }

    @Test
    public void pool_should_be_able_to_add_a_bus_drivers(){
        pool.addBusDriver(new BusDriver());
    }

    @Test
    public void should_be_able_to_remove_a_bus_driver(){
        BusDriver driver = new BusDriver();
        pool.addBusDriver(driver);
        assertTrue(pool.removeBusDriver(driver));
    }

    @Test
    public void should_be_able_to_drive_drivers(){
        BusDriver driver = new BusDriver();
        pool.addBusDriver(driver);
        pool.drive();
    }

    @Test
    public void should_be_able_to_drive_for_a_given_amount_of_time(){
        ArrayList<Integer> steps = new ArrayList<>();
        Collections.addAll(steps, 1,2,3,4,5);
        Integer times = 40;
        BusDriver driver = new BusDriver(steps);
        pool.addBusDriver(driver);
        pool.drive(times);
        assertEquals(times % steps.size(), driver.getStopPosition());
    }

    @Test
    public void drivers_in_pool_should_exchange_messages_when_on_same_stop_at_the_same_time(){
        ArrayList<Integer> steps1 = new ArrayList<>();
        Collections.addAll(steps1, 1,2,3,4,5);
        ArrayList<Integer> steps2 = new ArrayList<>();
        Collections.addAll(steps2, 2,4,1,3,2,1);
        ArrayList<Integer> steps3 = new ArrayList<>();
        Collections.addAll(steps3, 5,4,3,2,1);

        int times = 300;

        HashSet<Integer> gossips1 = new HashSet<Integer>();
        Collections.addAll(gossips1, 1,2,3,4);
        BusDriver driver1 = new BusDriver(steps1, gossips1);

        HashSet<Integer> gossips2 = new HashSet<Integer>();
        Collections.addAll(gossips2, 6,7,8,9,10);
        BusDriver driver2 = new BusDriver(steps2, gossips2);

        HashSet<Integer> gossips3 = new HashSet<Integer>();
        Collections.addAll(gossips3, 5,11,12);
        BusDriver driver3 = new BusDriver(steps3, gossips3);

        pool.addBusDriver(driver1);
        pool.addBusDriver(driver2);
        pool.addBusDriver(driver3);

        pool.drive(times);

        assertEquals(driver2.getGossips(), driver3.getGossips());
        assertEquals(driver1.getGossips(), driver2.getGossips());
    }
}

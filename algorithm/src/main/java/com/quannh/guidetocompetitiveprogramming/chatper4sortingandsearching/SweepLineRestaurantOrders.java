package com.quannh.guidetocompetitiveprogramming.chatper4sortingandsearching;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Input: Given a list of customer visits (entrance and exit, inclusively).
 * Output: Calculate the maximum concurrent number of customers to serve at peak time
 */
public class SweepLineRestaurantOrders {
    public static void main(String[] args) {
        List<CustomerVisit> customerVisits = new ArrayList<CustomerVisit>() {{
            add(new CustomerVisit(5, 9));
            add(new CustomerVisit(4, 6));
            add(new CustomerVisit(1, 7));
            add(new CustomerVisit(8, 10));
            add(new CustomerVisit(3, 10));
            add(new CustomerVisit(8, 10));
            add(new CustomerVisit(8, 9));
        }};

        List<CustomerEvent> customerEvents = new ArrayList<>();

        customerVisits.stream().forEach(customerVisit -> {
            customerEvents.add(new CustomerEvent(customerVisit.arrive, true));
            customerEvents.add(new CustomerEvent(customerVisit.leave, false));
        });
        Collections.sort(customerEvents);
        
        int concurrent = 0, maxConcurrent = 0, at = -1;
        for (CustomerEvent customerEvent : customerEvents) {
            if (customerEvent.isArrive) {
                concurrent++;
                if (maxConcurrent < concurrent) {
                    maxConcurrent = concurrent;
                    at = customerEvent.time;
                }
            } else {
                concurrent--;
            }
        }

        System.out.println("Max number of customers at at time: " + maxConcurrent + ", at: " + at);
    }
}


class CustomerVisit {
    public int arrive;
    public int leave;

    public CustomerVisit(int arrive, int leave) {
        this.arrive = arrive;
        this.leave = leave;
    }
}


class CustomerEvent implements Comparable<CustomerEvent> {
    public int time;
    public boolean isArrive;

    public CustomerEvent(int time, boolean isArrive) {
        this.time = time;
        this.isArrive = isArrive;
    }

    @Override
    public int compareTo(CustomerEvent o) {
        return this.time - o.time;
    }
    
}



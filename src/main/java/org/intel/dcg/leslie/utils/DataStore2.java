package org.intel.dcg.leslie.utils;

import java.util.*;

public class DataStore2 {
    //Data accessors
    public Map<String, Object> getCustomer(int id) {
        Map<String, Object> data = null;
        switch (id) {
            case 1:
                //updated.set(2012, 11, 11, 11, 11, 11);
                data = createCustomer(1, "Leslie");
                break;

            case 2:
                //updated.set(2013, 11, 11, 11, 11, 11);
                data = createCustomer(2, "Mango");
                break;

            default:
                break;
        }
        return data;
    }

    private Map<String, Object> createCustomer(int carId, String name) {
        Map<String, Object> data = new HashMap<String, Object>();

        data.put("Id", carId);
        data.put("Name", name);

        return data;
    }

    public List<Map<String, Object>> getCustomers() {
        List<Map<String, Object>> customers = new ArrayList<Map<String, Object>>();
        customers.add(getCustomer(1));
        customers.add(getCustomer(2));
        return customers;
    }


}

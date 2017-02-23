package com.sfeir.hashcode.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by bcornu on 2/23/17.
 */
public class Endpoint {

    private int id;
    private int datacenterLatency;
    private Map<Integer, Integer> caches = new HashMap<>();
    private Map<Integer, Integer> requests = new HashMap<>();

    public Endpoint(int id, int datacenterLatency) {
        this.id = id;
        this.datacenterLatency = datacenterLatency;
    }

    public int getDatacenterLatency() {
        return datacenterLatency;
    }

    public int getId() {
        return id;
    }

    public void addRequest(int video, int nb){
        requests.put(video, nb);
    }

    public Map<Integer, Integer> getCaches() {
        return caches;
    }
}

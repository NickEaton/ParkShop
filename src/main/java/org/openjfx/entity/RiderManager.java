package org.openjfx.entity;

import org.openjfx.util.Saveable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class RiderManager implements Saveable {

    private ArrayList<Rider> riderList;
    private ArrayList<Rider> shopRiderList;

    public RiderManager() {
        riderList = new ArrayList<Rider>();
        shopRiderList = new ArrayList<Rider>();
    }

    public ArrayList<Rider> getRiderList() { return this.riderList; }
    public ArrayList<Rider> getShopRiderList() { return this.shopRiderList; }

    public Rider genNewRider(String name, int level) {
        Rider temp = new Rider(20*(level+Math.random()), 20*(level+Math.random()), 20*(level+Math.random()), name,
                                20*(level+Math.random()), 20*(level+Math.random()));
        this.shopRiderList.add(temp);
        return temp;
    }

    @Override
    public void saveToFile() throws IOException {

    }
}

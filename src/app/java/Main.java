package app.java;

import app.java.bike.BikeManager;
import app.java.bike.BikeObj;
import app.java.components.Component;
import app.java.components.ComponentManager;
import app.java.entity.Rider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) throws IOException {
        /*
        BikeManager B = new BikeManager();
        ComponentManager C = new ComponentManager();
        Rider X = new Rider(100, 100, 100, "XD", 100, 100);

        int z = 0;
        for(ComponentManager.Part p : ComponentManager.Part.values()) {
            B.activePartList.put(p, C.getNewRandComponent(("c"+z), p));
            z++;
        }

        B.constructBike(X, B.activePartList);

        B.saveToFile();
        C.saveToFile();

        BikeManager B = new BikeManager("BikeManager");
        ComponentManager C = new ComponentManager("ComponentManager");

        System.out.println(B.toString());
        */
    }

}

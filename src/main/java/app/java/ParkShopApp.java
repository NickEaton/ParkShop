package app.java;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class ParkShopApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {

    }

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

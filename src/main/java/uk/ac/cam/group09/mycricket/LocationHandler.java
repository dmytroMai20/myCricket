package uk.ac.cam.group09.mycricket;

import java.util.ArrayList;

public class LocationHandler {
    private static ArrayList<Location> locations;

    public static ArrayList<Location> getLocations(){
        return locations;
    }

    public static void addLocation(Location newLocation){
        locations.add(newLocation);
    }
}

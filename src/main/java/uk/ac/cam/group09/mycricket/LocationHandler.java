package uk.ac.cam.group09.mycricket;

import java.util.ArrayList;
import java.util.LinkedList;

public class LocationHandler {
    private static LinkedList<Location> locations;

    public static LinkedList<Location> getLocations(){
        return locations;
    }

    public static void addLocation(Location newLocation){
        locations.add(newLocation);
    }
}

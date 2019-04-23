package net.ddns.bivor.group14_inclass12;

import java.util.ArrayList;

public class Trip {

    String tripName;
    Place destination;
    ArrayList<Place> places;

    public Trip() {
    }

    public Trip(String tripName, Place destination, ArrayList<Place> places) {
        this.tripName = tripName;
        this.destination = destination;
        this.places = places;
    }

}

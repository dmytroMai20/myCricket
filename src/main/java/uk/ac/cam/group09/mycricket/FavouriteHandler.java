package uk.ac.cam.group09.mycricket;

import java.util.LinkedList;
import java.util.List;

public class FavouriteHandler {
    private static final List<Favourite> favs = new LinkedList<>();

    public static List<Favourite> getFavs() {
        return favs;
    }

    public static void addFav(Favourite fav) {
        favs.add(fav);
    }
}

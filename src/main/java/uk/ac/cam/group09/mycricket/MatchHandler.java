package uk.ac.cam.group09.mycricket;

import java.util.ArrayList;

public class MatchHandler {
    private static ArrayList<Match> matches;

    public static ArrayList<Match> getMatches() {
        return matches;
    }

    public static void addMatch(Match newMatch) {
        matches.add(newMatch);
    }
}

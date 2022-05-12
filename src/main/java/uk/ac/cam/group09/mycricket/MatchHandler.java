package uk.ac.cam.group09.mycricket;

import java.util.ArrayList;
import java.util.LinkedList;

public class MatchHandler {
    private static LinkedList<Match> matches;

    public static LinkedList<Match> getMatches() {
        return matches;
    }

    public static void addMatch(Match newMatch) {
        matches.add(newMatch);
    }
}

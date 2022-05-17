package uk.ac.cam.group09.mycricket;

import java.util.ArrayList;
import java.util.LinkedList;

public class MatchHandler {
    private static LinkedList<Match> matches = new LinkedList<>();

    public static LinkedList<Match> getMatches() {
        return matches;
    }

    public static void refresh() {
        LinkedList<Match> update = new LinkedList<>();
        for (Match match : MatchHandler.matches) {
            match.update();
        }
    }
    public static void addMatch(Match newMatch) {
        matches.add(newMatch);
    }
}

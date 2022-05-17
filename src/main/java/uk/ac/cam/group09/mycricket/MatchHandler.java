package uk.ac.cam.group09.mycricket;

import java.util.ArrayList;
import java.util.LinkedList;

public class MatchHandler {
    private static LinkedList<Match> matches = new LinkedList<>();
    private static LinkedList<Match> highRiskMatches = new LinkedList<>();
    private static LinkedList<Match> lowRiskMatches = new LinkedList<>();

    public static LinkedList<Match> getMatches() {
        return matches;
    }

    public static void addMatch(Match newMatch) {
        matches.add(newMatch);
        Risk.RiskLevel matchRisk = newMatch.weather.goodToPlay().getRisk();
        switch (matchRisk){
            case NONE: lowRiskMatches.add(newMatch);
            case LOW: lowRiskMatches.add(newMatch);
            case MEDIUM: lowRiskMatches.add(newMatch);
            case HIGH: highRiskMatches.add(newMatch);
            case EXTREME: highRiskMatches.add(newMatch);

        }
    }
}

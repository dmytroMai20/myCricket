package uk.ac.cam.group09.mycricket;

public class Risk implements Comparable<Risk> {

    enum RiskLevel {
        NONE,
        LOW,
        MEDIUM,
        HIGH,
        EXTREME
    }

    private RiskLevel risk;
    private String message;

    Risk(RiskLevel risk) {
        this.risk = risk;
        this.message = "";
    }

    Risk(RiskLevel risk, String message) {
        this.risk = risk;
        this.message = message;
    }

    public RiskLevel getRisk() {
        return risk;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public int compareTo(Risk o) {
        return risk.compareTo(o.risk);
    }
}

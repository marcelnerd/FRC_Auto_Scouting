package com.example.cameron.sql_testing;

public class FRC2018Team {

    private int teamNum;
    private boolean climb;
    private int autoPoints;
    private boolean autoRun;
    private int teleopPoints;
    private int vaultPoints;

    public FRC2018Team() {
        climb = false;
        autoPoints = 999;
        autoRun = false;
        teamNum = 0;
        teleopPoints = 999;
        vaultPoints = 999;
    }

    public FRC2018Team(boolean c, int ap, boolean ar, int tn, int tp, int vp) {
        climb = c;
        autoPoints = ap;
        autoRun = ar;
        teamNum = tn;
        teleopPoints = tp;
        vaultPoints = vp;
    }


    public int getTeamNum() {
        return teamNum;
    }

    public void setTeamNum(int teamNum) {
        this.teamNum = teamNum;
    }

    public boolean isClimb() {
        return climb;
    }

    public void setClimb(boolean climb) {
        this.climb = climb;
    }

    public int getAutoPoints() {
        return autoPoints;
    }

    public void setAutoPoints(int autoPoints) {
        this.autoPoints = autoPoints;
    }

    public boolean isAutoRun() {
        return autoRun;
    }

    public void setAutoRun(boolean autoRun) {
        this.autoRun = autoRun;
    }

    public int getTeleopPoints() {
        return teleopPoints;
    }

    public void setTeleopPoints(int teleopPoints) {
        this.teleopPoints = teleopPoints;
    }

    public int getVaultPoints() {
        return vaultPoints;
    }

    public void setVaultPoints(int vaultPoints) {
        this.vaultPoints = vaultPoints;
    }
}

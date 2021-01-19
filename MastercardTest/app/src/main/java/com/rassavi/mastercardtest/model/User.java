package com.rassavi.mastercardtest.model;

public class User {

    private int enterCount=0;
    private int exitCount=0;
    private boolean stillHere=false;
    private int totalMessages=0;

    public void entered(){
        this.enterCount++;
        this.stillHere=true;
    }

    public void exited(){
        this.exitCount++;
        this.stillHere=false;
    }

    public void increaseTotalMessages(){
        this.totalMessages++;
    }

    public int getEnterCount() {
        return enterCount;
    }

    public int getExitCount() {
        return exitCount;
    }

    public boolean isStillHere() {
        return stillHere;
    }

    public int getTotalMessages() {
        return totalMessages;
    }
}

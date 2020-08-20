package com.senpai.lrv;

public class Scout {
    private String Name, Rank, KillCount;

    public Scout(String name, String rank, String killCount) {
        Name = name;
        Rank = rank;
        KillCount = killCount;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getRank() {
        return Rank;
    }

    public void setRank(String rank) {
        Rank = rank;
    }

    public String getKillCount() {
        return KillCount;
    }

    public void setKillCount(String killCount) {
        KillCount = killCount;
    }

}

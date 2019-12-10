package com.example.piskvorky;

public class Score {
    private String ID;
    private String name;
    private double score;

    public void setID(String ID)
    {
        this.ID = ID;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setScore(double score)
    {
        this.score = score;
    }


    public String getID()
    {
        return ID;
    }

    public String getName()
    {
        return name;
    }

    public double getScore()
    {
        return score;
    }


}

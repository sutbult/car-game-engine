package se.chalmers.tda367_4.app;

public interface ApplicationScore extends ApplicationText {
    int getScore();
    int getMultiplier();
    void addScore();
    void addMultiplier();
    void resetMultiplier();
}

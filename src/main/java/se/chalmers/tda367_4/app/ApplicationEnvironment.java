package se.chalmers.tda367_4.app;

public interface ApplicationEnvironment {
    ApplicationGraphics getGraphics();
    ApplicationInput getInput();
    void stop();
}

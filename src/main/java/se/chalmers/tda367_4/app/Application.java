package se.chalmers.tda367_4.app;

public interface Application{
    void init(ApplicationEnvironment appEnv);
    void update(float delta);
    void render();
}

package se.chalmers.tda367_4.app;

public enum ApplicationKey {
    UP(0),
    DOWN(1),
    LEFT(2),
    RIGHT(3);

    private int id;
    ApplicationKey(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }
}

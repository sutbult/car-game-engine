package se.chalmers.tda367_4.game.entities;

import se.chalmers.tda367_4.game.entities.power_ups.PowerUp;
import se.chalmers.tda367_4.geometry.triangle.GraphicalTriangle;
import se.chalmers.tda367_4.geometry.triangle.Triangle;

import java.util.ArrayList;
import java.util.List;

public class Environment implements SolidEntity, SingleColorEntity {

    private List<GraphicalTriangle> solidEnvironment;
    private List<GraphicalTriangle> nonSolidEnvironment;
    private List<PowerUp> powerUps = new ArrayList<PowerUp>();

    public Environment (List<GraphicalTriangle> solidEnvironment, List<GraphicalTriangle> nonSolidEnvironment) {
        this.solidEnvironment = new ArrayList<GraphicalTriangle>(solidEnvironment);
        this.nonSolidEnvironment = new ArrayList<GraphicalTriangle>(nonSolidEnvironment);
    }

    public Triangle[] getSolidTriangles() {
        Triangle[] triangles = new Triangle[solidEnvironment.size()];
        return solidEnvironment.toArray(triangles);
    }

    public GraphicalTriangle[] getGraphicalTriangles() {
        ArrayList<GraphicalTriangle> triangles = new ArrayList<GraphicalTriangle>();
        triangles.addAll(nonSolidEnvironment);
        triangles.addAll(solidEnvironment);
        GraphicalTriangle[] trianglesArray = new GraphicalTriangle[triangles.size()];
        return triangles.toArray(trianglesArray);
    }

    public List<PowerUp> getPowerUps() {
        return powerUps;
    }

    public void addPowerUp(PowerUp powerUp) {
        powerUps.add(powerUp);
    }
}

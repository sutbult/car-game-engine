package se.chalmers.tda367_4.game.entities;

import se.chalmers.tda367_4.game.entities.PowerUps.PowerUp;
import se.chalmers.tda367_4.geometry.*;

import java.util.ArrayList;
import java.util.List;

public class Environment implements SolidEntity, SingleColorEntity {

    private List<GraphicalTriangle> solidEnvironment;
    private List<GraphicalTriangle> nonSolidEnvironment;
    private List<PowerUp> powerUps = new ArrayList<PowerUp>();

    public Environment (List<GraphicalTriangle> solidEnvironment, List<GraphicalTriangle> nonSolidEnvironment) {
        this.solidEnvironment = new ArrayList<GraphicalTriangle>(solidEnvironment);
        this.nonSolidEnvironment = new ArrayList<GraphicalTriangle>(nonSolidEnvironment);



        PowerUp powerUp = new PowerUp(new Vector2(0, -4));
        PowerUp powerUp1 = new PowerUp(new Vector2(-5, -4));
        PowerUp powerUp2 = new PowerUp(new Vector2(-3, 4));
        powerUps.add(powerUp);
        powerUps.add(powerUp1);
        powerUps.add(powerUp2);

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

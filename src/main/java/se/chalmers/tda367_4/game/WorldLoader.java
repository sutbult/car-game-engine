package se.chalmers.tda367_4.game;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import se.chalmers.tda367_4.geometry.color.ApplicationColor;
import se.chalmers.tda367_4.game.entities.Environment;
import se.chalmers.tda367_4.geometry.triangle.GraphicalTriangle;
import se.chalmers.tda367_4.geometry.triangle.GraphicalTriangleImpl;
import se.chalmers.tda367_4.geometry.vector.Vector2;

public class WorldLoader {


    public static GameScene createWorld (String fileName){
        JSONObject jsonObject = new JSONObject();
        try {
            JSONParser parser = new JSONParser();
            jsonObject = (JSONObject) parser.parse(new FileReader("res/worlds/" + fileName + ".json"));
        } catch (Exception e) {
            System.out.println("Problem parsing JSON file");
        }
        return new GameScene(new Environment(
                getTriangles("solid-triangles", jsonObject),
                getTriangles("nonsolid-triangles", jsonObject)),
                getPolicePositions(jsonObject)
        );
    }

    public static List<GraphicalTriangle> getTriangles(String arrayName, JSONObject jsonObject) {

        List<GraphicalTriangle> triangles = new ArrayList<GraphicalTriangle>();
        JSONArray triangleList = (JSONArray) jsonObject.get(arrayName);

        Iterator<JSONObject> iterator = triangleList.iterator();
        while (iterator.hasNext()) {
            JSONObject object = iterator.next();
            JSONArray corners = (JSONArray) object.get("Corners");
            Vector2 vector = new Vector2(toFloat(corners.get(0)), toFloat(corners.get(1)));
            Vector2 vector1 = new Vector2(toFloat(corners.get(2)), toFloat(corners.get(3)));
            Vector2 vector2 = new Vector2(toFloat(corners.get(4)), toFloat(corners.get(5)));

            GraphicalTriangle triangle = new GraphicalTriangleImpl(vector, vector1, vector2,
                    new ApplicationColor(
                            toInt(object.get("R")),
                            toInt(object.get("G")),
                            toInt(object.get("B"))
                    ));
            triangles.add(triangle);
        }
        return triangles;
    }
    private static int toInt(Object a) {
        return Integer.parseInt(a.toString());
    }

    private static float toFloat(Object a) {
        return Float.parseFloat(a.toString());
    }

    public static List<Vector2> getPolicePositions(JSONObject jsonObject) {
        List<Vector2> policePositions = new ArrayList<Vector2>();

        JSONArray policeList = (JSONArray) jsonObject.get("Policecars");
        Iterator<JSONObject> iterator = policeList.iterator();
        while (iterator.hasNext()) {
            JSONObject object = iterator.next();
            JSONArray position = (JSONArray) object.get("Position");
            Vector2 vector = new Vector2(toFloat(position.get(0)), toFloat(position.get(1)));
            policePositions.add(vector);
        }
        return policePositions;
    }
}
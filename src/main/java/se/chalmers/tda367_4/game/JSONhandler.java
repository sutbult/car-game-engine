package se.chalmers.tda367_4.game;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ContentHandler;
import org.json.simple.parser.JSONParser;
import se.chalmers.tda367_4.geometry.GraphicalTriangle;
import se.chalmers.tda367_4.geometry.GraphicalTriangleImpl;
import se.chalmers.tda367_4.geometry.Vector2;

public class JSONhandler {
    public static void main(String[] args) throws IOException{

        JSONhandler a = new JSONhandler();
        a.writeJSON();
        a.readJSON();
    }

   public void writeJSON() throws IOException{
       JSONObject obj = new JSONObject();

       JSONArray triangles = new JSONArray();

        GraphicalTriangle triangle2 = new GraphicalTriangleImpl(new Vector2(4, 4), new Vector2(2, 2), new Vector2(2, 4),
                0.5f, 0.0f, 0.9f);
       System.out.println(triangle2.getCorners()[0].getClass());
       float a = (float) Math.tan(0.1f/0.3f);


       JSONArray corners = new JSONArray();
       JSONObject triangleObject = new JSONObject();
       triangleObject.put("R", "0.1f");
       triangleObject.put("G", "0.3f");
       triangleObject.put("B", "0.1f");
       corners.add(4.0);
       corners.add(4.0);
       corners.add(4.0);
       corners.add(2.0);
       corners.add(0.0);
       corners.add(4.0);
       triangleObject.put("Corners", corners);

       triangles.add(triangleObject);

       JSONObject triObject2 = new JSONObject();

       JSONArray corners2 = new JSONArray();
       triObject2.put("R", "0.5f");
       triObject2.put("G", "0.0f");
       triObject2.put("B", "0.9f");
       corners2.add(4.0);
       corners2.add(4.0);
       corners2.add(2.0);
       corners2.add(2.0);
       corners2.add(2.0);
       corners2.add(4.0);
       triObject2.put("Corners", corners2);

       triangles.add(triObject2);


       obj.put("Triangles", triangles);

       FileWriter file = new FileWriter("/home/john/Desktop/file.json");
       try {
           file.write(obj.toJSONString());
           System.out.println("Successfully Copied JSON Object to File...");
           System.out.println("\nJSON Object: " + obj);
       } catch (Exception e) {
           System.out.println("error");
       } finally {
           file.flush();
           file.close();
       }
   }

    public List<GraphicalTriangle> readJSON() {
        JSONParser parser = new JSONParser();

        List<GraphicalTriangle> triangles = new ArrayList<GraphicalTriangle>();
        try {

            Object obj = parser.parse(new FileReader("/home/john/Desktop/file.json"));

            JSONObject jsonObject = (JSONObject) obj;

            JSONArray triangleList = (JSONArray) jsonObject.get("Triangles");

            System.out.println("\nTriangles:");

            Iterator<JSONObject> iterator = triangleList.iterator();
            while (iterator.hasNext()) {
                JSONObject object = iterator.next();
                JSONArray corners = (JSONArray) object.get("Corners");

//                Vector2 vector2 = new Vector2(corners.get(0), corners.get(0));
                Vector2 vector = new Vector2(Float.parseFloat(corners.get(0).toString()),
                        Float.parseFloat(corners.get(1).toString()));
                Vector2 vector1 = new Vector2(Float.parseFloat(corners.get(2).toString()),
                        Float.parseFloat(corners.get(3).toString()));
                Vector2 vector2 = new Vector2(Float.parseFloat(corners.get(4).toString()),
                        Float.parseFloat(corners.get(5).toString()));
                GraphicalTriangle triangle = new GraphicalTriangleImpl(vector, vector1, vector2,
                        Float.parseFloat(object.get("R").toString()), Float.parseFloat(object.get("G").toString()),
                        Float.parseFloat(object.get("B").toString()));
                triangles.add(triangle);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return triangles;
    }
}
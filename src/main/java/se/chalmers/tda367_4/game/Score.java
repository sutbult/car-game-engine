package se.chalmers.tda367_4.game;

import java.io.*;

public class Score {
    private float score;
    private float multiplier;
    private File file = new File("res/scores.txt");

    public Score(float score, float multiplier){
        this.score = score;
        this.multiplier = multiplier;
    }

    public float getScore(){
        return score;
    }

    public float getMultiplier(){
        return multiplier;
    }

    public void addScore(float score){
        this.score += score;
    }

    public void setMultiplier(float multiplier){
        this.multiplier = multiplier;
    }

    public void resetMultiplier(){
        multiplier = 1;
    }

    public void update(float delta){
        addScore(delta * multiplier);
    }

    public void saveScore() throws IOException{
        FileWriter fw;
        BufferedWriter bw;
        PrintWriter out;

        try{
            if(!file.exists()){
                file.createNewFile();
            }

            fw = new FileWriter(file.getAbsoluteFile(), true);
            bw = new BufferedWriter(fw);
            out = new PrintWriter(bw);

            out.println(String.valueOf(Math.round(score)));
            out.close();

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}

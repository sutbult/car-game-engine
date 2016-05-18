package se.chalmers.tda367_4.game;

import java.io.*;
public class Score extends Multiplier {

    private float score;
    private float multiplier;
    private File file = new File("res/scores.txt");

    public Score(float score, float multiplier){
        super(multiplier);
        this.score = score;
    }

    public float getScore(){
        return score;
    }

    public void addScore(float score){
        this.score += score;
    }

    public void update(float delta){
        addScore(delta * getMultiplier());
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

    public float getHighScore(){
        float highScore = 0;

        try{
            BufferedReader br = new BufferedReader(new FileReader(file));

            for(String line = br.readLine(); line != null; line = br.readLine()){
                float tmp = Float.valueOf(line);
                if(tmp > highScore){
                    highScore = tmp;
                }
            }
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
        return highScore;
    }
}

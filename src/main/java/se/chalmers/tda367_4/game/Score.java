package se.chalmers.tda367_4.game;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
        String score = String.valueOf(Math.round(this.score));

        try{
            if(!file.exists()){
                file.createNewFile();
            }

            fw = new FileWriter(file.getAbsoluteFile(), true);
            bw = new BufferedWriter(fw);
            out = new PrintWriter(bw);

            out.println(score);
            out.close();

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public List<Integer> getHighScore(int nbrOfScores){
        List<Integer> list = createFileList();
        Collections.sort(list, Collections.<Integer>reverseOrder());
        List<Integer> highScores = createHighScoreList(list, nbrOfScores);
        return highScores;
    }

    private List<Integer> createFileList(){
        List<Integer> list = new ArrayList<Integer>();

        try{
            BufferedReader br = new BufferedReader(new FileReader(file));
            for(String line = br.readLine(); line != null; line = br.readLine()){
                list.add(Integer.parseInt(line));
            }
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }

        return list;
    }

    private List<Integer> createHighScoreList(List<Integer> list, int nbrOfScores){
        List<Integer> topScores = new ArrayList<Integer>();
        if(nbrOfScores < list.size()){
            for(int i = 0; i < nbrOfScores; i++){
                topScores.add(list.get(i));
            }return topScores;
        }else return list;
    }
}

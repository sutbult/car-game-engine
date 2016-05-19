package se.chalmers.tda367_4.menu;

import se.chalmers.tda367_4.app.ApplicationCamera;
import se.chalmers.tda367_4.game.GameScene;
import se.chalmers.tda367_4.game.GameText;
import se.chalmers.tda367_4.game.Score;
import se.chalmers.tda367_4.game.WorldLoader;
import se.chalmers.tda367_4.geometry.ApplicationColor;
import se.chalmers.tda367_4.app.ApplicationEnvironment;
import se.chalmers.tda367_4.app.ApplicationKey;
import se.chalmers.tda367_4.geometry.Vector2;
import se.chalmers.tda367_4.scenes.Scene;

import java.util.ArrayList;
import java.util.List;

public class EndMenuScene implements Scene {

    private ApplicationEnvironment appEnv;
    private List<GameText> gameTextList;

    private GameText gameOverText = new GameText("GAME OVER", "Sans-Serif", new Vector2(0, 3f), 1.6f, false,
            new ApplicationColor(0,0,0));
    private GameText scoreText = new GameText("Your score:", "Sans-Serif", new Vector2(0, 1.5f), 0.6f, false,
            new ApplicationColor(0,0,0));
    private GameText showScoreText;
    private GameText playText = new GameText("New game", "Sans-Serif", new Vector2(0, -1), 0.8f, false,
            new ApplicationColor(250,0,250));
    private GameText highscoresText = new GameText("Highscores", "Sans-Serif", new Vector2(0, -2), 0.8f, false,
            new ApplicationColor(0,0,0));
    private GameText settingsText = new GameText("Settings", "Sans-Serif", new Vector2(0, -3), 0.8f, false,
            new ApplicationColor(0,0,0));
    private GameText quitText = new GameText("Quit", "Sans-Serif", new Vector2(0, -4), 0.8f, false,
            new ApplicationColor(0,0,0));

    private boolean gameScene = false;
    private boolean quitScene = false;
    private int menuIndex = 0;
    private MenuCamera menuCamera;

    public EndMenuScene(Score score){
        menuCamera = new MenuCamera();
        gameTextList = new ArrayList<GameText>();
        showScoreText = new GameText(String.valueOf(Math.round(score.getScore())), "Sans_Serif",
                new Vector2(0,0.5f),
                1.4f,
                false,
                new ApplicationColor(0,100,0));

        gameTextList.add(playText);
        gameTextList.add(highscoresText);
        gameTextList.add(settingsText);
        gameTextList.add(quitText);
    }


    public void init(ApplicationEnvironment appEnv) {
        this.appEnv = appEnv;
    }

    public void update(float delta){
        if (appEnv.getInput().isKeyPressed(ApplicationKey.DOWN)){
            gameTextList.get(Math.abs(menuIndex)).setColor(0, 0, 0);
            changeIndexUp();
            gameTextList.get(Math.abs(menuIndex)).setColor(250, 0, 250);
        }

        if (appEnv.getInput().isKeyPressed(ApplicationKey.UP)){
            gameTextList.get(Math.abs(menuIndex)).setColor(0, 0, 0);
            changeIndexDown();
            gameTextList.get(Math.abs(menuIndex)).setColor(250, 0, 250);
        }

        if(appEnv.getInput().isKeyPressed(ApplicationKey.SPACE)){
            if(Math.abs(menuIndex) == 0){
                gameScene = true;
            }else if(Math.abs(menuIndex) == 3){
                quitScene = true;
            }
        }
    }

    public void render() {
        appEnv.getGraphics().setCamera(menuCamera);
        appEnv.getGraphics().renderText(gameOverText);
        appEnv.getGraphics().renderText(scoreText);
        appEnv.getGraphics().renderText(showScoreText);
        for(GameText text: gameTextList){
            appEnv.getGraphics().renderText(text);
        }
    }

    public Scene newScene() {
        if(gameScene){
            WorldLoader worldLoader = new WorldLoader();
            GameScene endScene = worldLoader.createWorld("world1");
            return endScene;
        }
        if(quitScene){
            System.exit(0);
        }
        return null;
    }

    private void changeIndexUp(){
        if(menuIndex <= 0){
            menuIndex = (menuIndex - 1) % gameTextList.size();
        }else menuIndex = (menuIndex + 1) % gameTextList.size();
    }
    private void changeIndexDown(){
        if(menuIndex <= 0){
            menuIndex = (menuIndex - 3) % gameTextList.size();
        }else menuIndex = (menuIndex - 1) % gameTextList.size();
    }

    private class MenuCamera implements ApplicationCamera{

        public Vector2 getPosition() {
            return new Vector2(0, 0);
        }

        public float getHeight() {
            return 10;
        }
    }


}

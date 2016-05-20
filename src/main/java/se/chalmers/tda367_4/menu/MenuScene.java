package se.chalmers.tda367_4.menu;

import se.chalmers.tda367_4.app.ApplicationCamera;
import se.chalmers.tda367_4.app.ApplicationEnvironment;
import se.chalmers.tda367_4.app.ApplicationKey;
import se.chalmers.tda367_4.game.GameScene;
import se.chalmers.tda367_4.game.GameText;
import se.chalmers.tda367_4.game.Score;
import se.chalmers.tda367_4.game.WorldLoader;
import se.chalmers.tda367_4.geometry.color.ApplicationColor;
import se.chalmers.tda367_4.geometry.vector.Vector2;
import se.chalmers.tda367_4.scenes.Scene;

import java.util.ArrayList;
import java.util.List;

public class MenuScene implements Scene {

    private ApplicationEnvironment appEnv;

    private List<GameText> gameTextList;
    private MenuCamera menuCamera;
    private GameScene gameScene;
    private int menuIndex = 0;
    private int score;
    private boolean endScene;
    private boolean showHighscores;

    private GameText pressSpaceText = new GameText("(Press space to interact)", "Sans-serif", new Vector2(0, 4.2f), 0.4f, false,
            new ApplicationColor(120, 120, 120));

    // Start text
    private GameText gtfaText = new GameText("GTFA", "Sans-Serif", new Vector2(0, 3), 2f, false,
            new ApplicationColor(0,0,0));
    private GameText underLineText1 = new GameText("________", "Sans-Serif", new Vector2(0, 2.8f), 1.5f, false,
            new ApplicationColor(0,0,0));

    // Pause/Game-over text
    private GameText gameOverText = new GameText("GAME OVER", "Sans-Serif", new Vector2(0, 3f), 1.6f, false,
            new ApplicationColor(0,0,0));
    private GameText underLineText2 = new GameText("_____________", "Sans-Serif", new Vector2(0, 2.8f), 1.5f, false,
            new ApplicationColor(0,0,0));
    private GameText scoreText = new GameText("Your score:", "Sans-Serif", new Vector2(0, 1.3f), 0.6f, false,
            new ApplicationColor(0,0,0));
    private GameText showScoreText;

    // Highscore text
    private GameText bigHighscoresText = new GameText("HIGHSCORES", "Sans-Serif", new Vector2(0, 3f), 1.6f, false,
            new ApplicationColor(0,0,0));

    // Menu-options
    private GameText playText = new GameText("Start game", "Sans-Serif", new Vector2(0, -1), 0.8f, false,
            new ApplicationColor(250,0,250));
    private GameText highscoresText = new GameText("Highscores", "Sans-Serif", new Vector2(0, -2), 0.8f, false,
            new ApplicationColor(0,0,0));
    private GameText settingsText = new GameText("Settings", "Sans-Serif", new Vector2(0, -3), 0.8f, false,
            new ApplicationColor(0,0,0));
    private GameText quitText = new GameText("Quit", "Sans-Serif", new Vector2(0, -4), 0.8f, false,
            new ApplicationColor(0,0,0));
    private GameText backText = new GameText("Back", "Sans-Serif", new Vector2(0, -4), 0.8f, false,
            new ApplicationColor(250,0,250));


    public MenuScene(){
        menuCamera = new MenuCamera();
        gameTextList = new ArrayList<GameText>();
        gameTextList.add(playText);
        gameTextList.add(highscoresText);
        gameTextList.add(settingsText);
        gameTextList.add(quitText);
    }

    public MenuScene(Score score, MenuScene menuScene){
        menuCamera = menuScene.menuCamera;
        gameTextList = new ArrayList<GameText>();
        gameTextList.add(menuScene.playText);
        gameTextList.add(menuScene.highscoresText);
        gameTextList.add(menuScene.settingsText);
        gameTextList.add(menuScene.quitText);
        this.gameScene = menuScene.gameScene;
        
        this.score = Integer.valueOf(String.valueOf(Math.round(score.getScore())));
        showScoreText = new GameText(String.valueOf(this.score), "Sans_Serif",
                new Vector2(0,0.3f),
                1.4f,
                false,
                new ApplicationColor(0,100,0));
    }

    public void init(ApplicationEnvironment appEnv) {
        this.appEnv = appEnv;
    }

    public void update(float delta){
        if (appEnv.getInput().isKeyPressed(ApplicationKey.DOWN)){
            gameTextList.get(Math.abs(menuIndex)).setColor(new ApplicationColor(0, 0, 0));
            changeIndexUp();
            gameTextList.get(Math.abs(menuIndex)).setColor(new ApplicationColor(250, 0, 250));
        }

        if (appEnv.getInput().isKeyPressed(ApplicationKey.UP)){
            gameTextList.get(Math.abs(menuIndex)).setColor(new ApplicationColor(0, 0, 0));
            changeIndexDown();
            gameTextList.get(Math.abs(menuIndex)).setColor(new ApplicationColor(250, 0, 250));
        }

        if(appEnv.getInput().isKeyPressed(ApplicationKey.SPACE)){
            if(Math.abs(menuIndex) == 0){
                endScene = true;
            }else if(Math.abs(menuIndex) == 1){
                showHighscores = true;
            }else if(Math.abs(menuIndex) == 3){
                System.exit(0);
            }
        }
    }

    public void render() {
        appEnv.getGraphics().setCamera(menuCamera);
        appEnv.getGraphics().renderText(pressSpaceText);
        for(GameText text: gameTextList){
            appEnv.getGraphics().renderText(text);
        }

        if(showHighscores){
            appEnv.getGraphics().renderText(bigHighscoresText);
            appEnv.getGraphics().renderText(underLineText2);
        }else{
            if(gameScene == null){
                appEnv.getGraphics().renderText(gtfaText);
                appEnv.getGraphics().renderText(underLineText1);
            }else {
                appEnv.getGraphics().renderText(gameOverText);
                appEnv.getGraphics().renderText(underLineText2);
                appEnv.getGraphics().renderText(scoreText);
                appEnv.getGraphics().renderText(showScoreText);
            }
        }
    }

    public Scene newScene() {
        if(endScene){
            WorldLoader loader = new WorldLoader();
            gameScene = loader.createWorld("world1", this);
            return gameScene;
        }else return null;
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

    private class MenuCamera implements ApplicationCamera {

        public Vector2 getPosition() {
            return new Vector2(0, 0);
        }

        public float getHeight() {
            return 10;
        }
    }
}



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
    private int menuIndex = 0;
    private Scene endScene;
    private GameScene gameScene;
    private int score = 0;
    private boolean showHighscores;
    private boolean showHowToPlay;

    private GameText pressSpaceText = new GameText("(Press space to interact)", "Sans-serif",
            new Vector2(0, 4.2f), 0.4f, false,
            new ApplicationColor(120, 120, 120));

    // Start text
    private GameText gtfaText = new GameText("GTFA", "Sans-Serif", new Vector2(0, 3), 1.4f, false,
            new ApplicationColor(0,0,0));

    // Pause/Game-over text
    private GameText gameOverText = new GameText("GAME OVER", "Sans-Serif", new Vector2(0, 3f), 1.4f, false,
            new ApplicationColor(0,0,0));
    private GameText underLineText2 = new GameText("_____________", "Sans-Serif", new Vector2(0, 2.8f), 1.3f, false,
            new ApplicationColor(0,0,0));
    private GameText scoreText = new GameText("Your score:", "Sans-Serif", new Vector2(0, 1.3f), 0.4f, false,
            new ApplicationColor(0,0,0));
    private GameText showScoreText;

    // Highscore text
    private GameText bigHighscoresText = new GameText("HIGHSCORES", "Sans-Serif", new Vector2(0, 3f), 1.4f, false,
            new ApplicationColor(0,0,0));
    private GameText topFiveScores = new GameText("Top 5", "Sans-Serif", new Vector2(0, 1.8f), 0.6f, false,
            new ApplicationColor(100,100,100));
    private List<Integer> highscoreList;

    // How to play text
    private GameText bigHowToPlayText = new GameText("HOW TO PLAY", "Sans-Serif", new Vector2(0, 3f), 1.6f, false,
            new ApplicationColor(0,0,0));

    // Movement
    private GameText controls = new GameText("Controls:", "Sans-Serif", new Vector2(-2.5f, 1.5f), 0.8f, false,
            new ApplicationColor(60,60,60));
    private GameText controls1 = new GameText("Use the arrowkeys to", "Sans-Serif", new Vector2(-2.5f, 0.9f),
            0.4f, false,
            new ApplicationColor(0,0,0));
    private GameText controls2 = new GameText("control your vehicle.", "Sans-Serif", new Vector2(-2.5f, 0.4f),
            0.4f, false,
            new ApplicationColor(0,0,0));
    private GameText controls3 = new GameText("Press ESC to pause game.", "Sans-Serif", new Vector2(-2.5f, -0.1f),
            0.4f, false,
            new ApplicationColor(0,0,0));

    // Rules
    private GameText rules = new GameText("Rules:", "Sans-Serif", new Vector2(2.5f, 1.5f), 0.8f, false,
            new ApplicationColor(60,60,60));
    private GameText rules1 = new GameText("Avoid the police cars", "Sans-Serif", new Vector2(2.5f, 0.9f),
            0.4f, false,
            new ApplicationColor(0,0,0));
    private GameText rules2 = new GameText("at any cost!", "Sans-Serif", new Vector2(2.5f, 0.4f),
            0.4f, false,
            new ApplicationColor(0,0,0));

    // Menu-options
    private GameText playText = new GameText("Start game", "Sans-Serif", new Vector2(0, -1.6f), 0.6f, false,
            new ApplicationColor(0,0,250));
    private GameText highscoresText = new GameText("Highscores", "Sans-Serif", new Vector2(0, -2.4f), 0.6f, false,
            new ApplicationColor(0,0,0));
    private GameText howToPlayText = new GameText("How to play", "Sans-Serif", new Vector2(0, -3.2f), 0.6f, false,
            new ApplicationColor(0,0,0));
    private GameText quitText = new GameText("Quit", "Sans-Serif", new Vector2(0, -4f), 0.6f, false,
            new ApplicationColor(0,0,0));

    public MenuScene(){
        menuCamera = new MenuCamera();
        gameTextList = new ArrayList<GameText>();
        gameTextList.add(playText);
        gameTextList.add(highscoresText);
        gameTextList.add(howToPlayText);
        gameTextList.add(quitText);
    }

    public MenuScene(GameScene gameScene){
        this();
        this.gameScene = gameScene;
    }

    public void init(ApplicationEnvironment appEnv) {
        this.appEnv = appEnv;
        appEnv.getGraphics().setBackgroundColor(new ApplicationColor(255, 255, 255));
    }

    public void update(float delta){
        if (appEnv.getInput().isKeyPressed(ApplicationKey.DOWN)){
            gameTextList.get(Math.abs(menuIndex)).setColor(new ApplicationColor(0, 0, 0));
            changeIndexUp();
            gameTextList.get(Math.abs(menuIndex)).setColor(new ApplicationColor(0, 0, 250));
        }

        if (appEnv.getInput().isKeyPressed(ApplicationKey.UP)){
            gameTextList.get(Math.abs(menuIndex)).setColor(new ApplicationColor(0, 0, 0));
            changeIndexDown();
            gameTextList.get(Math.abs(menuIndex)).setColor(new ApplicationColor(0, 0, 250));
        }

        if(appEnv.getInput().isKeyPressed(ApplicationKey.SPACE)){
            if(Math.abs(menuIndex) == 0){
                setReplacementScene();
            }else if(Math.abs(menuIndex) == 1){
                showHowToPlay = false;
                highscoreList = Score.getHighScore(5);
                showHighscores = true;
            } else if(Math.abs(menuIndex) == 2){
                showHighscores = false;
                showHowToPlay = true;
            }else if(Math.abs(menuIndex) == 3){
                System.exit(0);
            }
        }
        if(showScoreText == null && gameScene != null) {
            this.score = Integer.valueOf(Math.round(gameScene.getScore()));
            showScoreText = new GameText(String.valueOf(this.score), "Sans_Serif",
                    new Vector2(0,0.3f),
                    1.4f,
                    false,
                    new ApplicationColor(0,100,0));
        }
    }

    public void render() {
        appEnv.getGraphics().setCamera(menuCamera);
        appEnv.getGraphics().renderText(pressSpaceText);
        appEnv.getGraphics().renderText(underLineText2);

        for(GameText text: gameTextList){
            appEnv.getGraphics().renderText(text);
        }

        if(showHighscores){
            appEnv.getGraphics().renderText(bigHighscoresText);
            appEnv.getGraphics().renderText(topFiveScores);
            Float yAxis = 1.2f;
            for(Integer list : highscoreList){
                appEnv.getGraphics().renderText(new GameText(list.toString(), "Sans-Serif",
                        new Vector2(0, yAxis), 0.4f, false,
                        new ApplicationColor(0,0,0)));
                yAxis -= 0.5f;
            }
        }else if(showHowToPlay) {
            appEnv.getGraphics().renderText(bigHowToPlayText);
            appEnv.getGraphics().renderText(controls);
            appEnv.getGraphics().renderText(controls1);
            appEnv.getGraphics().renderText(controls2);
            appEnv.getGraphics().renderText(controls3);

            appEnv.getGraphics().renderText(rules);
            appEnv.getGraphics().renderText(rules1);
            appEnv.getGraphics().renderText(rules2);

        }else{
            if(score == 0){
                appEnv.getGraphics().renderText(gtfaText);
            }else {
                appEnv.getGraphics().renderText(gameOverText);
                appEnv.getGraphics().renderText(underLineText2);
                appEnv.getGraphics().renderText(scoreText);
                appEnv.getGraphics().renderText(showScoreText);
            }
        }
    }

    public Scene newScene() {
        return endScene;
    }

    private void setReplacementScene(){
        WorldLoader loader = new WorldLoader();
        gameScene = loader.createWorld("world1");
        gameScene.setReplacementScene(new MenuScene(gameScene));
        endScene = gameScene;
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



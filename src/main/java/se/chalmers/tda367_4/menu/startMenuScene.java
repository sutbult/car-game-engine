package se.chalmers.tda367_4.menu;

import se.chalmers.tda367_4.app.ApplicationCamera;
import se.chalmers.tda367_4.app.ApplicationSprite;
import se.chalmers.tda367_4.game.GameScene;
import se.chalmers.tda367_4.game.GameText;
import se.chalmers.tda367_4.game.WorldLoader;
import se.chalmers.tda367_4.geometry.ApplicationColor;
import se.chalmers.tda367_4.app.ApplicationEnvironment;
import se.chalmers.tda367_4.app.ApplicationKey;
import se.chalmers.tda367_4.geometry.Vector2;
import se.chalmers.tda367_4.scenes.Scene;

import java.util.ArrayList;
import java.util.List;

public class startMenuScene implements Scene {

    private ApplicationEnvironment appEnv;

    private List<GameText> gameTextList;
    private int menuIndex = 0;

    private GameText gtfaText = new GameText("GTFA", "Sans-Serif", new Vector2(0, 3), 2f, false,
            new ApplicationColor(0,0,0));
    private GameText playText = new GameText("Start game", "Sans-Serif", new Vector2(0, 1), 0.8f, false,
            new ApplicationColor(250,0,0));
    private GameText highscoresText = new GameText("Highscores", "Sans-Serif", new Vector2(0, 0), 0.8f, false,
            new ApplicationColor(0,0,0));
    private GameText settingsText = new GameText("Settings", "Sans-Serif", new Vector2(0, -1), 0.8f, false,
            new ApplicationColor(0,0,0));
    private GameText quitText = new GameText("Quit", "Sans-Serif", new Vector2(0, -2), 0.8f, false,
            new ApplicationColor(0,0,0));

    private boolean changeScene = false;
    private MenuCamera menuCamera;

    public startMenuScene(){
        menuCamera = new MenuCamera();
        gameTextList = new ArrayList<GameText>();
        gameTextList.add(playText);
        gameTextList.add(highscoresText);
        gameTextList.add(settingsText);
        gameTextList.add(quitText);
        System.out.println(menuIndex);
    }


    public void init(ApplicationEnvironment appEnv) {
        this.appEnv = appEnv;
    }

    public void update(float delta){
        if (appEnv.getInput().isKeyPressed(ApplicationKey.DOWN)){
            menuIndex = (menuIndex + 1) % gameTextList.size();
            gameTextList.get(menuIndex).
        }

        if (appEnv.getInput().isKeyPressed(ApplicationKey.UP)){
            menuIndex = (menuIndex - 1) % gameTextList.size();
            System.out.println(menuIndex);
        }


    }

    public void render() {
        appEnv.getGraphics().setCamera(menuCamera);
        appEnv.getGraphics().renderText(gtfaText);
        for(GameText text: gameTextList){
            appEnv.getGraphics().renderText(text);
        }
    }

    public Scene newScene() {
        if(changeScene){
            WorldLoader worldLoader = new WorldLoader();
            GameScene endScene = worldLoader.createWorld("world1");
            return endScene;
        }else return null;
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

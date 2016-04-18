package se.chalmers.tda367_4.scenes;

import org.junit.Test;
import se.chalmers.tda367_4.app.ApplicationEnvironment;
import static org.junit.Assert.assertTrue;

/**
 * Created by Marcus on 2016-04-18.
 */
public class SceneManagerTest {

    @Test
    public void gameScene() throws Exception{
        SceneManager scene = new SceneManager(new TestClassA());
        scene.render();
        assertTrue(scene.getScene() instanceof TestClassB);
    }

    @Test
    public void menuScene() throws Exception{
        SceneManager scene = new SceneManager(new TestClassB());
        scene.render();
        assertTrue(scene.getScene() instanceof TestClassA);
    }

    private class TestClassA implements Scene{

        public Scene newScene() { return new TestClassB();}

        public void init(ApplicationEnvironment appEnv) {}

        public void update(float delta) {}

        public void render() {}
    }

    private class TestClassB implements Scene{

        public Scene newScene(){ return new TestClassA();}

        public void init(ApplicationEnvironment appEnv) {}

        public void update(float delta) {}

        public void render() {}
    }
}
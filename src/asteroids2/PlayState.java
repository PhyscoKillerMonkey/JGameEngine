package asteroids2;

import core.GameContainer;
import core.Renderer;
import core.componenets.ObjectManager;
import core.componenets.State;

public class PlayState implements State {
  
  private ObjectManager manager;

  public PlayState() {
    manager = new ObjectManager();
    manager.addObject(new Asteroid(300, 300, "/sprites/meteors/meteorBrown_big1.png"));
    manager.addObject(new Player(0, 0, "/sprites/playerShip3_red.png"));
  }
  
  @Override
  public void update(GameContainer gc, double dt) {
    manager.updateObjects(gc, dt);
  }

  @Override
  public void render(GameContainer gc, Renderer r) {
    manager.renderObjects(gc, r);
  }

  @Override
  public void dispose() {
    manager.disposeObjects();
  }
}

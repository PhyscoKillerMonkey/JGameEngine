package asteroids2;

import core.GameContainer;
import core.componenets.Collider;
import core.componenets.GameObject;

public class Asteroid extends Entity {
  
  public Asteroid(double x, double y, String url) {
    super(x, y, 0, url);
    setTag("Asteroid");
    ((Collider) (findComponent("collider"))).setType("circle");
  }

  @Override
  public void update(GameContainer gc, double dt) {
    updateComponents(gc, dt);
  }

  @Override
  public void componentEvent(String name, GameObject obj) {
  }

}

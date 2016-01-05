package asteroids2;

import core.GameContainer;
import core.componenets.GameObject;

public class Laser extends Entity {

  public Laser(double x, double y, double rotation, String url) {
    super(x, y, rotation, url);
    setX(getX() - getWidth()/2);
    setY(getY() - getHeight()/2);
    setTag("laser");
  }

  @Override
  public void update(GameContainer gc, double dt) {
    move(gc, dt);
    
    PlayState p = (PlayState) gc.getGame().peek();
    if (x < -p.getStageW()/2 || x > p.getStageW()/2 || 
        y < -p.getStageH()/2 || y > p.getStageH()/2) {
      setDead(true);
      return;
    }
    
    updateComponents(gc, dt);
  }

  @Override
  public void componentEvent(String name, GameObject obj) {
    if (name.equalsIgnoreCase("collider") && obj.getTag().equals("asteroid")) {
      setDead(true);
    }
  }
}
package asteroids2;

import core.GameContainer;
import core.componenets.Collider;
import core.componenets.GameObject;

public class Asteroid extends Entity {
  
  private int size;
  private boolean split;
  
  public Asteroid(double x, double y, String url) {
    super(x, y, 0, url);
    setTag("asteroid");
    ((Collider) (findComponent("collider"))).setType("circle");
    setSize(3);
    split = false;
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
    
    if (split) {
      split(gc);
    }
    updateComponents(gc, dt);
  }
  
  public void setSize(int size) {
    this.size = size;
    switch (size) {
      case 0:
        setImg("/sprites/meteors/meteorBrown_small1.png");
        break;
      case 1:
        setImg("/sprites/meteors/meteorBrown_med1.png");
        break;
      case 2:
        setImg("/sprites/meteors/meteorBrown_big1.png");
        break;
      case 3:
        setImg("/sprites/meteors/meteorBrown_huge1.png");
        break;
    }
  }
  
  public void split(GameContainer gc) {
    split = false;
    if (size > 0) {
      setSize(size-1);
      for (int i = 0; i < 1; i++) {
        Asteroid a = new Asteroid(x, y, "/sprites/meteors/meteorBrown_huge1.png");
        a.setSize(size);
        a.setRotation(Math.random() * Math.PI * 2);
        a.giveImpulse(50);
        gc.getGame().peek().addObject(a);
      }
    } else {
      setDead(true);
    }
  }
  
  private boolean onscreen(GameContainer gc) {
    if (x > gc.getScreenOffX() && x < gc.getScreenOffX() + gc.getWidth() &&
        y > gc.getScreenOffY() && y < gc.getScreenOffY() + gc.getHeight()) {
      return true;
    }
    return false;
  }

  @Override
  public void componentEvent(GameContainer gc, String name, GameObject obj) {
    if (name.equals("collider") && obj.getTag().equals("laser") && onscreen(gc)) {
      split = true;
    }
  }

}

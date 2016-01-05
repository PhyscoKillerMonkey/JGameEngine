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
    if (split) {
      split(gc);
    }
    move(gc, dt);
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
    setSize(size--);
    for (int i = 0; i < 3; i++) {
      Asteroid a = new Asteroid(x, y, "/sprites/meteors/meteorBrown_huge1.png");
      a.setSize(size);
      a.giveImpulse(100);
      gc.getGame().peek().addObject(a);
    }
  }

  @Override
  public void componentEvent(String name, GameObject obj) {
    if (name.equals("collider") && obj.getTag().equals("laser")) {
      //split = true;
    }
  }

}

package core.componenets;

import core.GameContainer;
import core.Renderer;

public abstract class State {

  public abstract void update(GameContainer gc, double dt);
  public abstract void render(GameContainer gc, Renderer r);
  public abstract void dispose();
  public abstract void addObject(GameObject object);
}

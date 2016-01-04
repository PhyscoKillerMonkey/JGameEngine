package core.componenets;

import core.GameContainer;
import core.Renderer;

public interface State {

  public void update(GameContainer gc, double dt);
  public void render(GameContainer gc, Renderer r);
  public void dispose();
}

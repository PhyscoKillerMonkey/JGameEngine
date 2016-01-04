package core.componenets;

import core.GameContainer;
import core.Renderer;

public abstract class Component {

  protected String tag = "null";
  public abstract void update(GameContainer gc, GameObject obj, double dt);
  public abstract void render(GameContainer gc, Renderer r);
  
  public String getTag() {
    return tag;
  }
  public void setTag(String tag) {
    this.tag = tag;
  }
}

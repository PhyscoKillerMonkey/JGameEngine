package core.componenets;

import java.util.ArrayList;

import core.GameContainer;
import core.Renderer;

public abstract class GameObject {
  
  protected double x, y;
  protected int width, height;

  protected String tag = "null";
  protected boolean dead = false;
  protected ArrayList<Component> components = new ArrayList<>();
  
  public abstract void update(GameContainer gc, double dt);
  public abstract void render(GameContainer gc, Renderer r);
  public abstract void componentEvent(String name, GameObject obj);
  public abstract void dispose();
  
  public void updateComponents(GameContainer gc, double dt) {
    components.forEach(c -> c.update(gc, this, dt));
  }
  
  public void renderComponents(GameContainer gc, Renderer r) {
    components.forEach(c -> c.render(gc, r));
  }
  
  public void addComponent(Component c) {
    components.add(c);
  }
  
  public void removeComponent(String tag) {
    for (int i = components.size(); i >= 0; i--) {
      if (components.get(i).getTag().equalsIgnoreCase(tag)) {
        components.remove(i);
      }
    }
  }
  
  public Component findComponent(String tag) {
    for (Component c : components) {
      if (c.getTag().equalsIgnoreCase(tag)) {
        return c;
      }
    }
    return null;
  }
  
  public String getTag() {
    return tag;
  }
  public void setTag(String tag) {
    this.tag = tag;
  }
  public boolean isDead() {
    return dead;
  }
  public void setDead(boolean dead) {
    this.dead = dead;
  }
  public double getX() {
    return x;
  }
  public void setX(double x) {
    this.x = x;
  }
  public double getY() {
    return y;
  }
  public void setY(double y) {
    this.y = y;
  }
  public int getWidth() {
    return width;
  }
  public void setWidth(int width) {
    this.width = width;
  }
  public int getHeight() {
    return height;
  }
  public void setHeight(int height) {
    this.height = height;
  }
}

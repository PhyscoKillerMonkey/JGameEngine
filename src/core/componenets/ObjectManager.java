package core.componenets;

import java.util.ArrayList;

import core.GameContainer;
import core.Renderer;

public class ObjectManager {
  
  private ArrayList<GameObject> objects = new ArrayList<>();
  
  public void addObject(GameObject object) {
    objects.add(object);
  }
  
  public GameObject findObject(String tag) {
    for (GameObject obj : objects) {
      if (obj.getTag().equalsIgnoreCase(tag)) {
        return obj;
      }
    }
    return null;
  }
  
  public int numObjects() {
    return objects.size();
  }
  
  public GameObject get(int i) {
    return objects.get(i);
  }
  
  public void updateObjects(GameContainer gc, double dt) {
    for (int i = objects.size() - 1; i >= 0; i--) {
      objects.get(i).update(gc, dt);
      
      if (objects.get(i).isDead()) {
        objects.remove(i);
      }
    }
  }
  
  public void renderObjects(GameContainer gc, Renderer r) {
    for (int i = objects.size() - 1; i >= 0; i--) {
      objects.get(i).render(gc, r);
    }
  }
  
  public void disposeObjects() {
    objects.forEach(obj -> obj.dispose());
  }
}

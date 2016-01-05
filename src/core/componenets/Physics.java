package core.componenets;

import java.util.ArrayList;

import core.GameContainer;

public class Physics {

  private ArrayList<Collider> colliders = new ArrayList<>();
  
  public void update(GameContainer gc) {
    for (int i = 0; i < colliders.size(); i++) {
      for (int j = i + 1; j < colliders.size(); j++) {
        Collider c0 = colliders.get(i);
        Collider c1 = colliders.get(j);
        
        double x1 = c0.getX();
        double y1 = c0.getY();
        double r1 = c0.getRadius();
        
        double x2 = c1.getX();
        double y2 = c1.getY();
        double r2 = c1.getRadius();
        
        double dx = Math.abs(x1 - x2);
        double dy = Math.abs(y1 - y2);
        
        if (c0.getType() == "point" && c1.getType() == "circle" &&
            dx * dx + dy * dy < r2 * r2) {
          c0.collision(gc, c1.getObject());
          c1.collision(gc, c0.getObject());
        } else if (c0.getType() == "circle" && c1.getType() == "point" &&
            dx * dx + dy * dy < r1 * r1) {
          c0.collision(gc, c1.getObject());
          c1.collision(gc, c0.getObject());
        } else if (c0.getType() == "circle" && c1.getType() == "circle" &&
            dx * dx + dy * dy < (r1+r2) * (r1+r2)) {
          c0.collision(gc, c1.getObject());
          c1.collision(gc, c0.getObject());
        }
      }
    }
    colliders.clear();
  }
  
  public void addCollider(Collider c) {
    colliders.add(c);
  }
}

package core.componenets;

import java.awt.Color;

import core.GameContainer;
import core.Renderer;

public class Collider extends Component {

  private GameObject obj;
  private double x, y, radius;
  private String type;
  
  public Collider(String type) {
    setTag("collider");
    this.type = type;
  }
  
  @Override
  public void update(GameContainer gc, GameObject obj, double dt) {
    if (this.obj == null) {
      this.obj = obj;
    }
    
    x = obj.getX() + obj.getWidth() / 2;
    y = obj.getY() + obj.getHeight() / 2;
    
    if (type == "circle") {
      radius = Math.min(obj.getWidth(), obj.getHeight()) / 2;
      //System.out.println(obj.getTag() + " " + radius + " " + obj.getHeight());
    } else if (!(type == "point" || type == "circle")) {
      try {
        throw (new Throwable("Invalid bounding type: " + type));
      } catch (Throwable e) {
        e.printStackTrace();
      }
    }
    
    gc.getPhysics().addCollider(this);
  }

  @Override
  public void render(GameContainer gc, Renderer r) {
    if (gc.getDebug() && type == "circle") {
      r.drawEllipse(x-radius, y-radius, radius*2, radius*2, new Color(100, 255, 100));
    } else if (gc.getDebug() && type == "point") {
      double s = Math.max(obj.getWidth(), obj.getHeight()) * 0.75;
      r.drawCross(x-s/2, y-s/2, s, s, new Color(100, 255, 100));
    }
  }
  
  public void collision(GameObject obj) {
    this.obj.componentEvent(tag, obj);
  }

  public double getX() {
    return x;
  }

  public double getY() {
    return y;
  }

  public double getRadius() {
    return radius;
  }

  public String getType() {
    return type;
  }
  
  public void setType(String type) {
    if (type == "circle" || type == "point") {
      this.type = type;
    } else {
      try {
        throw (new Throwable("Invalid bounding type: " + type));
      } catch (Throwable e) {
        e.printStackTrace();
      }
    }
  }

  public GameObject getObject() {
    return obj;
  }

}

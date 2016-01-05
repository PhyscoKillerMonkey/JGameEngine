package asteroids2;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import core.GameContainer;
import core.Renderer;
import core.componenets.Collider;
import core.componenets.GameObject;

public abstract class Entity extends GameObject {
  
  private BufferedImage img;
  protected double vx, vy, rotation;
  protected double thrust;
  
  public Entity(double x, double y, double rotation, String url) {
    setTag("Entity");
    
    this.x = x; this.y = y;
    this.rotation = rotation;
    vx = 0; vy = 0;
    
    try {
      img = ImageIO.read(Player.class.getResourceAsStream(url));
    } catch (IOException e) {
      e.printStackTrace();
    }
    
    width = img.getWidth();
    height = img.getHeight();
    
    addComponent(new Collider("point"));
  }

  public abstract void update(GameContainer gc, double dt);

  @Override
  public void render(GameContainer gc, Renderer r) {
    r.drawImage(gc, x - gc.getScreenOffX(), y - gc.getScreenOffY(), rotation, img);
    renderComponents(gc, r);
  }

  @Override
  public abstract void componentEvent(String name, GameObject obj);

  @Override
  public void dispose() {
  }
  
  public void giveImpulse(double impulse) {
    vx += impulse * Math.sin(rotation);
    vy -= impulse * Math.cos(rotation);
  }
  
  public void move(GameContainer gc, double dt) {
    vx += thrust * Math.sin(rotation);
    vy -= thrust * Math.cos(rotation);
    
    x += vx * dt;
    y += vy * dt;
  }

  public double getVX() {
    return vx;
  }

  public void setVX(double vx) {
    this.vx = vx;
  }

  public double getVY() {
    return vy;
  }

  public void setVY(double vy) {
    this.vy = vy;
  }

  public double getRotation() {
    return rotation;
  }

  public void setRotation(double rotation) {
    this.rotation = rotation;
  }
  
  public void changeRotation(double rotation) {
    this.rotation += rotation;
  }

  public double getThrust() {
    return thrust;
  }

  public void setThrust(double thrust) {
    this.thrust = thrust;
  }

  public BufferedImage getImg() {
    return img;
  }

  public void setImg(String url) {
    try {
      img = ImageIO.read(Player.class.getResourceAsStream(url));
    } catch (IOException e) {
      e.printStackTrace();
    }
    
    width = img.getWidth();
    height = img.getHeight();
  }

  public double getSpeed() {
    return Math.abs(Math.sqrt(vx*vx + vy*vy));
  }
}

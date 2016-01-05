package asteroids2;

import java.awt.event.KeyEvent;

import core.GameContainer;
import core.Renderer;
import core.componenets.Collider;
import core.componenets.GameObject;

public class Player extends Entity {
  
  private int life;
  private boolean invunerable;
  private long invunerableStart; // ms
  private final int invunerableLength; // ms
  private final int flickerLength; // ms
  private long flickerLast; // ms
  private boolean render;
  
  private int fireCooldown; // ms
  private long fireLast; // ms

  public Player(double x, double y, String url) {
    super(x, y, 0, url);
    setTag("player");
    ((Collider) (findComponent("collider"))).setType("circle");
    
    life = 3;
    invunerable = true;
    invunerableStart = System.currentTimeMillis();
    invunerableLength = 1500;
    flickerLength = 150;
    flickerLast = 0;
    render = true;
    fireCooldown = 200;
    fireLast = 0;
  }
  
  @Override
  public void update(GameContainer gc, double dt) {
    double rSpeed = 0.1;
    
    if (gc.getInput().isKeyDown(KeyEvent.VK_UP)) {
      thrust = 20;
    } else {
      thrust = 0;
      vx -= vx * 0.04;
      vy -= vy * 0.04;
    }
    
    if (gc.getInput().isKeyDown(KeyEvent.VK_RIGHT)) { rotation += rSpeed; }
    if (gc.getInput().isKeyDown(KeyEvent.VK_LEFT)) { rotation -= rSpeed; }
    
    move(gc, dt);
    
    if (gc.getInput().isKeyDown(KeyEvent.VK_SPACE)
        && System.currentTimeMillis() - fireLast > fireCooldown) {
      Laser l = new Laser(x+width/2, y+height/2, rotation, "/sprites/laserBlue07.png");
      l.giveImpulse(getSpeed() + 500);
      gc.getGame().peek().addObject(l);
      fireLast = System.currentTimeMillis();
    }
    
    if (System.currentTimeMillis() - invunerableStart > invunerableLength) {
      invunerable = false;
      render = true;
    }
    
    gc.setScreenOffX(-gc.getWidth() / 2 + x + width / 2);
    gc.setScreenOffY(-gc.getHeight() / 2 + y + height / 2);
    
    updateComponents(gc, dt);
  }

  @Override
  public void render(GameContainer gc, Renderer r) {
    if (invunerable && System.currentTimeMillis() - flickerLast > flickerLength) {
      flickerLast = System.currentTimeMillis();
      render = !render;
    }
    if (render) {
      r.drawImage(gc, x - gc.getScreenOffX(), y - gc.getScreenOffY(), rotation, getImg());
    }
    
    renderComponents(gc, r);
  }

  @Override
  public void componentEvent(String name, GameObject obj) {
    if (name.equalsIgnoreCase("collider") && obj.getTag().equals("asteroid")) {
      if (System.currentTimeMillis() - invunerableStart > invunerableLength) {
        life--;
        System.out.println(life);
        invunerableStart = System.currentTimeMillis();
        invunerable = true;
      }
    }
  }

  @Override
  public void dispose() {
  }

}

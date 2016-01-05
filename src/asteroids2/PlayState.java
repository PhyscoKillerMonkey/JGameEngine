package asteroids2;

import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import core.GameContainer;
import core.Renderer;
import core.componenets.GameObject;
import core.componenets.ObjectManager;
import core.componenets.State;

public class PlayState extends State {
  
  private ObjectManager manager;
  private BufferedImage bg;
  private int stageW, stageH;
  private long lastSpawn;

  public PlayState(GameContainer gc) {
    manager = new ObjectManager();
    
    gc.setWidth(960);
    gc.setHeight(720);
    
    manager.addObject(new HUD(gc, "/sprites/playerLife3_red.png"));
    
    Player p = new Player(0, 0, "/sprites/playerShip3_red.png");
    p.setX(-p.getWidth() / 2);
    p.setY(-p.getHeight() / 2);
    manager.addObject(p);
    
    manager.addObject(new Asteroid(300, 300, "/sprites/meteors/meteorBrown_big1.png"));
    
    // Load the background image
    try {
      bg = ImageIO.read(Player.class.getResourceAsStream("/sprites/darkPurple.png"));
    } catch (IOException e) {
      e.printStackTrace();
    }
    stageW = stageH = 10000;
    gc.setScreenOffX(-gc.getWidth() / 2);
    gc.setScreenOffY(-gc.getHeight() / 2);
    
    gc.setDebug(true);
    
    lastSpawn = 0;
  }
  
  @Override
  public void addObject(GameObject object) {
    manager.addObject(object);
  }
  
  @Override
  public void update(GameContainer gc, double dt) {
    manager.updateObjects(gc, dt);
    
    int asteroids = 0;
    for (int i = 0; i < manager.numObjects() - 1; i++) {
      if (manager.get(i).getTag().equals("asteroidH") &&
          manager.get(i).onscreen(gc)) {
        asteroids++;
      }
    }
    if (asteroids < 1 && System.currentTimeMillis() - lastSpawn > 3000) {
      double angle = Math.random() * 2 * Math.PI;
      Player p = (Player) manager.findObject("player");
      double dist = Math.max(gc.getWidth(), gc.getHeight());
      double x = p.getX() + p.getWidth() / 2 + dist * Math.sin(angle);
      double y = p.getY() + p.getHeight() / 2 - dist * Math.cos(angle);
      Asteroid a = new Asteroid(x, y, "/sprites/meteors/meteorBrown_big1.png");
      a.setRotation(angle + Math.PI);
      a.giveImpulse(Math.random() * 200 + 100);
      manager.addObject(a);
      System.out.println("Adding asteroid");
      lastSpawn = System.currentTimeMillis();
    }
  }

  @Override
  public void render(GameContainer gc, Renderer r) {
    // Render the background
    for (int x = -stageW / 2; x < stageW / 2; x += bg.getWidth()) {
      for (int y = -stageH / 2; y < stageH / 2; y += bg.getHeight()) {
        r.drawImage(gc, x - gc.getScreenOffX(), y - gc.getScreenOffY(), bg);
      }
    }
    
    manager.renderObjects(gc, r);
    
    // Render debug stuff
    if (gc.getDebug()) {
      Font f = new Font("Arial", Font.BOLD, 15);
      Color c = new Color(255, 255, 255);
      Player p = (Player) manager.findObject("player");
      int sx = 5;
      int sy = 20;
      r.drawString("x: " + (int)p.getX() + " y: " + (int)p.getY(), sx, sy, f, c);
      r.drawString("sX: " + (int)gc.getScreenOffX() + " sY: " 
          + (int)gc.getScreenOffY(), sx, sy + 15, f, c);
    }
  }

  @Override
  public void dispose() {
    manager.disposeObjects();
  }

  public int getStageW() {
    return stageW;
  }

  public int getStageH() {
    return stageH;
  }

  public ObjectManager getManager() {
    return manager;
  }
}

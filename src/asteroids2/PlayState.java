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

  public PlayState(GameContainer gc) {
    manager = new ObjectManager();
    
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
  }
  
  @Override
  public void addObject(GameObject object) {
    manager.addObject(object);
  }
  
  @Override
  public void update(GameContainer gc, double dt) {
    manager.updateObjects(gc, dt);
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
      Color c = new Color(200, 200, 200);
      Player p = (Player) manager.findObject("player");
      int sx = gc.getWindow().getInsets().left;
      int sy = 25;
      r.drawString("x: " + (int)p.getX() + " y: " + (int)p.getY(), sx, sy, f, c);
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
}

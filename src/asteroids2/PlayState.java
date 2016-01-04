package asteroids2;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import core.GameContainer;
import core.Renderer;
import core.componenets.ObjectManager;
import core.componenets.State;

public class PlayState implements State {
  
  private ObjectManager manager;
  private BufferedImage bg;
  private int stageW, stageH;

  public PlayState(GameContainer gc) {
    manager = new ObjectManager();
    manager.addObject(new Asteroid(300, 300, "/sprites/meteors/meteorBrown_big1.png"));
    Player p = new Player(0, 0, "/sprites/playerShip3_red.png");
    p.setX(-p.getWidth() / 2);
    p.setY(-p.getHeight() / 2);
    manager.addObject(p);
    
    // Load the background image
    try {
      bg = ImageIO.read(Player.class.getResourceAsStream("/sprites/darkPurple.png"));
    } catch (IOException e) {
      e.printStackTrace();
    }
    stageW = stageH = 10000;
    gc.setScreenOffX(-gc.getWidth() / 2);
    gc.setScreenOffY(-gc.getHeight() / 2);
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
        r.drawImage(gc, x, y, bg);
      }
    }
    
    manager.renderObjects(gc, r);
  }

  @Override
  public void dispose() {
    manager.disposeObjects();
  }
}

package tests;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import core.AbstractGame;
import core.GameContainer;
import core.Renderer;

public class EngineTest extends AbstractGame {
  
  private BufferedImage img;
  private double x = 100;
  private double y = 100;

  public static void main(String[] args) {
    GameContainer gc = new GameContainer(new EngineTest());
    gc.start();
  }

  @Override
  public void init(GameContainer gc) {
    try {
      img = ImageIO.read(getClass().getResourceAsStream("/sprites/enemyBlack3.png"));
    } catch (IOException e) {
      System.out.println("Something went wrong");
      e.printStackTrace();
    }
  }

  @Override
  public void update(GameContainer gc, double dt) {
    x += 500*dt;
    if (x > gc.getWidth()) {
      x = -img.getWidth();
    }
  }

  @Override
  public void render(GameContainer gc, Renderer r) {
    r.drawImage(gc, x, y, 2, img);
  }
}

package tests;

import java.awt.Color;
import java.awt.Font;

import core.AbstractGame;
import core.GameContainer;
import core.Renderer;

public class EngineTest extends AbstractGame {

  public static void main(String[] args) {
    GameContainer gc = new GameContainer(new EngineTest());
    gc.start();
  }

  @Override
  public void init(GameContainer gc) {
    
  }

  @Override
  public void update(GameContainer gc, double dt) {
    
  }

  @Override
  public void render(GameContainer gc, Renderer r) {
    Font f = new Font("Arial", Font.BOLD, 15);
    Color c = new Color(255, 0, 0);
    r.drawString("HI", 0, 0, f, c);
    r.drawString("Hello", 0, 15, f, c);
  }
}

package asteroids2;

import core.AbstractGame;
import core.GameContainer;
import core.Renderer;

public class AsteroidsGM extends AbstractGame {
  
  public static void main(String[] args) {
    GameContainer gc = new GameContainer(new AsteroidsGM());
    gc.start();
  }
  
  @Override
  public void init(GameContainer gc) {
    push(new PlayState());
  }

  @Override
  public void update(GameContainer gc, double dt) {
    peek().update(gc, dt);
  }

  @Override
  public void render(GameContainer gc, Renderer r) {
    peek().render(gc, r);
  }
}

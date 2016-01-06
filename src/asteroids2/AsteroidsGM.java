package asteroids2;

import java.awt.event.KeyEvent;

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
    push(new PlayState(gc));
    push(new StartState(gc));
  }

  @Override
  public void update(GameContainer gc, double dt) {
    if ((peek() instanceof StartState || peek() instanceof DeadState)
        && gc.getInput().isKeyPressed(KeyEvent.VK_ENTER)) {
      pop();
    }
    if (peek() instanceof PlayState && gc.getInput().isKeyPressed(KeyEvent.VK_P)) {
      push(new PausedState(gc));
    }
    if (peek() instanceof PausedState && gc.getInput().isKeyPressed(KeyEvent.VK_ENTER)) {
      pop();
    }
    
    peek().update(gc, dt);
  }

  @Override
  public void render(GameContainer gc, Renderer r) {
    peek().render(gc, r);
  }
}

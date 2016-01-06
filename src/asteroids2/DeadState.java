package asteroids2;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.InputStream;

import core.GameContainer;
import core.Renderer;
import core.componenets.GameObject;
import core.componenets.State;

public class DeadState extends State {
  
  Font f;
  Color c;
  
  public DeadState() {
    // Load the font
    InputStream iStream = getClass().getResourceAsStream("/kenvector_future.ttf");
    try {
      f = Font.createFont(Font.TRUETYPE_FONT, iStream);
    } catch (FontFormatException | IOException e) {
      e.printStackTrace();
    }
    
    c = new Color(200, 200, 200);
  }

  @Override
  public void update(GameContainer gc, double dt) {
    if (gc.getInput().isKeyPressed(KeyEvent.VK_SPACE)) {
      gc.getGame().pop();
    }
  }

  @Override
  public void render(GameContainer gc, Renderer r) {
    int cX = gc.getWidth() / 2;
    int cY = gc.getHeight() / 2;
    f = f.deriveFont(Font.PLAIN, 50);
    r.drawString("Game Over!", cX - 190, cY-100, f, c);
    f = f.deriveFont(Font.PLAIN, 30);
    r.drawString("Press SPACE to restart", cX - 240, cY + 50, f, c);
  }

  @Override
  public void dispose() {
  }

  @Override
  public void addObject(GameObject object) {
  }

}

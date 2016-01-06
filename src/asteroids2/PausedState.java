package asteroids2;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;

import core.GameContainer;
import core.Renderer;
import core.componenets.GameObject;
import core.componenets.State;

public class PausedState extends State {
  
  private Font f;
  private Color c;
  private boolean drawn;
  private int width, height;
  
  public PausedState(GameContainer gc) {
    // Load the font
    InputStream iStream = getClass().getResourceAsStream("/kenvector_future.ttf");
    try {
      f = Font.createFont(Font.TRUETYPE_FONT, iStream);
    } catch (FontFormatException | IOException e) {
      e.printStackTrace();
    }
    
    c = new Color(200, 200, 200);
    
    drawn = false;
    width = gc.getWidth();
    height = gc.getHeight();
  }

  @Override
  public void update(GameContainer gc, double dt) {
  }

  @Override
  public void render(GameContainer gc, Renderer r) {
    if (!drawn) {
      r.fillRect(gc, 0, 0, width, height, new Color(0, 0, 0, 100));
      
      f = f.deriveFont(Font.PLAIN, 60);
      r.drawStringCentered("Game Paused", width/2, height/2 - 60, f, c);
      f = f.deriveFont(Font.PLAIN, 40);
      r.drawStringCentered("Press enter to resume", width/2, height / 2, f, c);
      
      drawn = true;
    }
  }

  @Override
  public void dispose() {
  }

  @Override
  public void addObject(GameObject object) {
  }

}

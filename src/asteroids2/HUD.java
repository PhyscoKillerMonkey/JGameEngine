package asteroids2;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import core.GameContainer;
import core.Renderer;
import core.componenets.GameObject;

public class HUD extends GameObject {
  
  private BufferedImage lifeImg;
  private PlayState ps;
  private Player p;
  private Font f;
  private Color c;
  
  public HUD(GameContainer gc, String url) {
    try {
      lifeImg = ImageIO.read(Player.class.getResourceAsStream(url));
    } catch (IOException e) {
      e.printStackTrace();
    }
    width = gc.getWidth();
    height = gc.getHeight();

    // Load the font
    InputStream iStream = getClass().getResourceAsStream("/kenvector_future.ttf");
    try {
      f = Font.createFont(Font.TRUETYPE_FONT, iStream);
    } catch (FontFormatException | IOException e) {
      e.printStackTrace();
    }
    f = f.deriveFont(Font.PLAIN, 20);
    
    c = new Color(200, 200, 200);
  }

  @Override
  public void update(GameContainer gc, double dt) {
    ps = (PlayState) gc.getGame().peek();
    p = (Player) ps.getManager().findObject("player");
  }

  @Override
  public void render(GameContainer gc, Renderer r) {
    int spacing = lifeImg.getWidth() + 5;
    int sx = gc.getWidth() - spacing - 15;
    int sy = 15;
    
    for (int i = 0; i < spacing * p.getLife(); i += spacing) {
      r.drawImage(gc, sx - i, sy, lifeImg);
    }
    
    r.drawString("Lives: ", gc.getWidth() - spacing*3 - 110, 17, f, c);
    
    r.drawString("Score: " + p.getScore(), gc.getWidth() - spacing*3 - 110, 50, f, c);
  }

  @Override
  public void componentEvent(GameContainer gc, String name, GameObject obj) {
  }

  @Override
  public void dispose() {
  }

}

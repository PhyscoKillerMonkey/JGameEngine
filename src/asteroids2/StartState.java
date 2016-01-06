package asteroids2;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import core.GameContainer;
import core.Renderer;
import core.componenets.GameObject;
import core.componenets.State;

public class StartState extends State {
  
  private ArrayList<Asteroid> asteroids;
  private BufferedImage bg;
  private int width, height;
  
  // Font stuffs
  Font f;
  Color c;
  
  public StartState(GameContainer gc) {
    asteroids = new ArrayList<>();
    
    width = gc.getWidth();
    height = gc.getHeight();
    
    for (int i = 0; i < 15; i++) {
      Asteroid a = new Asteroid(Math.random() * width, 
          Math.random() * height, "/sprites/meteors/meteorBrown_big1.png");
      a.setSize((int) Math.floor(Math.random() * 4));
      a.setRotation(Math.random() * 2 * Math.PI);
      a.giveImpulse(Math.random() * 200 + 100);
      asteroids.add(a);
    }
    
    gc.setScreenOffX(0);
    gc.setScreenOffY(0);
    
    // Load the background image
    try {
      bg = ImageIO.read(Player.class.getResourceAsStream("/sprites/darkPurple.png"));
    } catch (IOException e) {
      e.printStackTrace();
    }
    
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
    asteroids.forEach(a -> {
      a.update(gc, dt);
      if (a.getX() < -a.getWidth()) {
        a.setX(width);
      } else if (a.getX() > width) {
        a.setX(-a.getWidth());
      } else if (a.getY() < -a.getHeight()) {
        a.setY(height);
      } else if (a.getY() > height) {
        a.setY(-a.getHeight());
      }
    });
  }

  @Override
  public void render(GameContainer gc, Renderer r) {
    // Render the background
    for (int x = 0; x < width; x += bg.getWidth()) {
      for (int y = 0; y < height; y += bg.getHeight()) {
        r.drawImage(gc, x, y, bg);
      }
    }
    
    asteroids.forEach(a -> a.render(gc, r));
    
    r.fillRect(gc, 0, 0, width, height, new Color(0, 0, 0, 100));
    
    f = f.deriveFont(Font.PLAIN, 60);
    r.drawStringCentered("Asteroids V2", width/2, height/2 - 160, f, c);
    
    f = f.deriveFont(Font.PLAIN, 40);
    r.drawStringCentered("Controls", width/2, height/2-60, f, c);
    
    f = f.deriveFont(Font.PLAIN, 20);
    r.drawStringCentered("Left Right : Rotate ship", width/2, height/2 - 20, f, c);
    r.drawStringCentered("Up : Thrust forward", width/2, height/2, f, c);
    r.drawStringCentered("Space : Fire", width/2, height/2 + 20, f, c);
    r.drawStringCentered("P : Pause", width/2, height/2 + 40, f, c);
    
    f = f.deriveFont(Font.PLAIN, 40);
    r.drawStringCentered("Press enter to start", width/2, height/2 + 90, f, c);
  }

  @Override
  public void dispose() {
  }

  @Override
  public void addObject(GameObject object) {
  }

}

package core;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class Renderer {
  
  private int width, height;
  private BufferedImage image;
  private Graphics2D g;
  
  public Renderer(GameContainer gc) {
    width = gc.getWidth();
    height = gc.getHeight();
    image = gc.getWindow().getImage();
    g = (Graphics2D) image.getGraphics();

    RenderingHints rh = new RenderingHints(null);
    rh.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
    g.setRenderingHints(rh);
  }
  
  public void fillRect(double x, double y, int width, int height, Color color) {
    g.setColor(color);
    g.fill(new Rectangle2D.Double(x, y, width, height));
  }
  
  public void drawImage(double x, double y, double rotation, Image img) {
    AffineTransform t = new AffineTransform();
    t.translate(x, y);
    t.rotate(rotation, img.getWidth(null)/2, img.getHeight(null)/2);
    g.drawImage(img, t, null);
  }
  
  public void drawImage(double x, double y, Image img) {
    drawImage(x, y, 0, img);
  }

  public void clear() {
    g.clearRect(0, 0, width, height);
  }
}

package core;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class Renderer {
  
  private int width, height;
  private BufferedImage backBuffer;
  private Graphics2D g;
  
  public Renderer(GameContainer gc) {
    width = gc.getWidth();
    height = gc.getHeight();
    backBuffer = gc.getWindow().getBuffer();
    g = (Graphics2D) backBuffer.getGraphics();

    RenderingHints rh = new RenderingHints(null);
    rh.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
    g.setRenderingHints(rh);
  }
  
  public void fillRect(GameContainer gc, double x, double y, int width, int height, Color color) {
    g.setColor(color);
    g.fill(new Rectangle2D.Double(x, y, width, height));
  }
  
  public void drawImage(GameContainer gc, double x, double y, double rotation,
      double cx, double cy, Image img) {
    AffineTransform t = new AffineTransform();
    t.translate(x, y);
    t.rotate(rotation, cx, cy);
    g.drawImage(img, t, null);
  }
  
  public void drawImage(GameContainer gc, double x, double y, double rotation, Image img) {
    drawImage(gc, x, y, rotation, img.getWidth(null)/2, img.getHeight(null)/2, img);
  }
  
  public void drawImage(GameContainer gc, double x, double y, Image img) {
    drawImage(gc, x, y, 0, img);
  }
  
  public void drawEllipse(GameContainer gc, double x, double y, double width, double height, Color color) {
    g.setColor(color);
    g.draw(new Ellipse2D.Double(x, y, width, height));
  }
  
  public void drawCross(GameContainer gc, double x, double y, double width, double height, Color color) {
    g.setColor(color);
    g.draw(new Line2D.Double(x, y, 
        x+width, y+height));
    g.draw(new Line2D.Double(x, y+height, 
        x+width, y));
  }
  
  public void drawString(String string, int x, int y, Font font, Color color) {
    int ascent = g.getFontMetrics(font).getAscent();
    g.setFont(font);
    g.setColor(color);
    g.drawString(string, x, ascent+y);
  }
  
  public void drawStringCentered(String string, int x, int y, Font font, Color color) {
    int w = g.getFontMetrics(font).stringWidth(string);
    drawString(string, x - w/2, y, font, color);
  }

  public void clear() {
    g.clearRect(0, 0, width, height);
  }

  public void setWidth(GameContainer gc, int width) {
    this.width = width;
    backBuffer = gc.getWindow().getBuffer();
    g = (Graphics2D) backBuffer.getGraphics();
    RenderingHints rh = new RenderingHints(null);
    rh.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
    g.setRenderingHints(rh);
  }

  public void setHeight(GameContainer gc, int height) {
    this.height = height;
    backBuffer = gc.getWindow().getBuffer();
    g = (Graphics2D) backBuffer.getGraphics();
    RenderingHints rh = new RenderingHints(null);
    rh.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
    g.setRenderingHints(rh);
  }
}

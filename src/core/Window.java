package core;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Window {

  private JFrame frame;
  private Canvas canvas;
  private BufferedImage image;
  private Graphics2D g;
  private BufferStrategy bs;
  
  public Window(GameContainer gc) {
    image = new BufferedImage(gc.getWidth(), gc.getHeight(), BufferedImage.TYPE_INT_RGB);
    
    canvas = new Canvas();
    canvas.setPreferredSize(new Dimension(gc.getWidth(), gc.getHeight()));
    
    frame = new JFrame(gc.getTitle());
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.add(canvas);
    frame.pack();
    frame.setVisible(true);
    
    canvas.createBufferStrategy(2);
    bs = canvas.getBufferStrategy();
    g = (Graphics2D) bs.getDrawGraphics();
  }
  
  public void update() {
    g.drawImage(image, 0, 0, canvas.getWidth(), canvas.getHeight(), null);
    bs.show();
  }
  
  public void cleanUp() {
    g.dispose();
    bs.dispose();
    image.flush();
    frame.dispose();
  }

  public Canvas getCanvas() {
    return canvas;
  }

  public BufferedImage getImage() {
    return image;
  }
}

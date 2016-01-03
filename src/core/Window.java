package core;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Window {

  private JFrame frame;
  private Insets insets;
  private BufferedImage backBuffer;
  private Graphics2D sg;
  private BufferStrategy bs;
  private int width; private int height;
  
  public Window(GameContainer gc) {
    backBuffer = new BufferedImage(gc.getWidth(), gc.getHeight(), BufferedImage.TYPE_INT_RGB);
    
    frame = new JFrame(gc.getTitle());
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    // Add insets to get the correct frame size
    insets = frame.getInsets();
    width = insets.left + gc.getWidth() + insets.right;
    height = insets.top + gc.getHeight() + insets.bottom;
    frame.setSize(new Dimension(width, height));
    
    frame.setVisible(true);
    
    sg = (Graphics2D) frame.getGraphics();
  }
  
  public void update() {
    sg.drawImage(backBuffer, 0, 0, width, height, null);
  }
  
  public void cleanUp() {
    sg.dispose();
    bs.dispose();
    backBuffer.flush();
    frame.dispose();
  }

  public BufferedImage getBuffer() {
    return backBuffer;
  }
  
  public JFrame getFrame() {
    return frame;
  }
}

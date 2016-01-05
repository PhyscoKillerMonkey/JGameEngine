package core;

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
    frame.setResizable(false);
    
    // Add insets to get the correct frame size
    frame.pack();
    insets = frame.getInsets();
    width = gc.getWidth();
    height = gc.getHeight();
    frame.setSize(insets.left + width + insets.right, 
        insets.top + height + insets.bottom);
    
    frame.setVisible(true);
    
    sg = (Graphics2D) frame.getGraphics();
  }
  
  public void update() {
    sg.drawImage(backBuffer, insets.left, insets.top, width, height, null);
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

  public Insets getInsets() {
    return insets;
  }

  public void setWidth(int width) {
    this.width = width;
    frame.setSize(insets.left + width + insets.right, 
        insets.top + height + insets.bottom);
    backBuffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
  }

  public void setHeight(int height) {
    this.height = height;
    frame.setSize(insets.left + width + insets.right, 
        insets.top + height + insets.bottom);
    backBuffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
  }
}

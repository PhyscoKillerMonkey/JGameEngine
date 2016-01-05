package core;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Input implements KeyListener {

  private GameContainer gc;
  
  private boolean[] keys = new boolean[256];
  private boolean[] keysLast = new boolean[256];
  
  public Input(GameContainer gc) {
    this.gc = gc;
    this.gc.getWindow().getFrame().addKeyListener(this);
  }
  
  public void update() {
    keysLast = keys.clone();
  }
  
  public boolean isKeyDown(int keyCode) {
    return keys[keyCode];
  }
  
  public boolean isKeyPressed(int keyCode) {
    return (keys[keyCode] && !keysLast[keyCode]);
  }
  
  public boolean isKeyReleased(int keyCode) {
    return (!keys[keyCode] && keysLast[keyCode]);
  }
  
  @Override
  public void keyPressed(KeyEvent e) {
    keys[e.getKeyCode()] = true;
  }

  @Override
  public void keyReleased(KeyEvent e) {
    keys[e.getKeyCode()] = false;
  }

  @Override
  public void keyTyped(KeyEvent e) {
  }
}

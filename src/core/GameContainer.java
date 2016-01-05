package core;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.io.PrintStream;

import core.componenets.Physics;

public class GameContainer implements Runnable {

  private Thread thread;
  private AbstractGame game;
  private Window window;
  private Renderer renderer;
  private Input input;
  private Physics physics;
  
  private int width = 640, height = 480;
  private double screenOffX = 0, screenOffY = 0;
  private double scale = 1;
  private String title = "JGameEngine";
  
  private boolean isRunning = false;
  private double frameLength = 1.0 / 60.0;
  private boolean debug = false;
  
  public GameContainer(AbstractGame game) {
    this.game = game;
  }
  
  public void start() {
    if (!isRunning) {
      isRunning = true;
      
      window = new Window(this);
      renderer = new Renderer(this);
      input = new Input(this);
      physics = new Physics();
      
      // Custom System.out.println
      PrintStream stream = new PrintStream(System.out) {
        public void println(String s) {
          String fullClassName = Thread.currentThread().getStackTrace()[2].getClassName();
          String className = fullClassName.substring(fullClassName.lastIndexOf(".")+1);
          String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
          int lineNumber = Thread.currentThread().getStackTrace()[2].getLineNumber();
          super.println(className + "-" + methodName + " @ " + lineNumber + ": " + s);
        }
      };
      System.setOut(stream);
      
      System.out.print("Hello there");
      
      thread = new Thread(this);
      thread.run();
    }
  }
  
  public void stop() {
    isRunning = false;
  }
  
  public void run() {
    isRunning = true;
    
    boolean lockFPS = true;
    
    // Times are in seconds
    double firstTime = 0;
    double lastTime = System.nanoTime() / 1000000000.0;
    double passedTime = 0;
    double timeLeft = 0;
    
    // FPS Display Stuff
    double frameTime = 0;
    int frames = 0;
    int fps = 0;
    int ticks = 0;
    int tps = 0;
    
    game.init(this);
    
    while(isRunning) {
      boolean shouldRender = !lockFPS;
      
      firstTime = System.nanoTime() / 1000000000.0;
      passedTime = firstTime - lastTime;
      lastTime = firstTime;
      
      timeLeft += passedTime;
      frameTime += passedTime;
      
      while (timeLeft >= frameLength) {
        if (input.isKeyPressed(KeyEvent.VK_1)) {
          System.out.println("(Un)locking framerate");
          lockFPS = !lockFPS;
        }
        if (input.isKeyPressed(KeyEvent.VK_2)) {
          System.out.println("Enabling debug mode");
          debug = !debug;
        }
        
        game.update(this, timeLeft);
        input.update();
        physics.update();
        
        timeLeft -= frameLength;
        shouldRender = true;
        ticks++;
      }
      
      if (shouldRender) {
        renderer.clear();
        
        game.render(this, renderer);
        
        if (debug) {
          Font f = new Font("Arial", Font.BOLD, 15);
          Color c = new Color(255, 255, 255);
          renderer.drawString("FPS: " + fps + " TPS: " + tps, 5, 5, f, c);
        }
        
        window.update();
        frames++;
      } else {
        try {
          Thread.sleep(1);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      
      if (frameTime >= 1) {
        frameTime = 0;
        fps = frames;
        tps = ticks;
        frames = 0;
        ticks = 0;
      }
    }
  }
  
  public void cleanUp() {
    window.cleanUp();
  }

  public int getWidth() {
    return width;
  }

  public void setWidth(int width) {
    this.width = width;
  }

  public int getHeight() {
    return height;
  }

  public void setHeight(int height) {
    this.height = height;
  }

  public double getScale() {
    return scale;
  }

  public void setScale(double scale) {
    this.scale = scale;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Window getWindow() {
    return window;
  }

  public AbstractGame getGame() {
    return game;
  }

  public Input getInput() {
    return input;
  }

  public Physics getPhysics() {
    return physics;
  }
  
  public boolean getDebug() {
    return debug;
  }
  
  public void setDebug(boolean b) {
    debug = b;
  }

  public double getScreenOffX() {
    return screenOffX;
  }

  public void setScreenOffX(double screenOffX) {
    this.screenOffX = screenOffX;
  }

  public double getScreenOffY() {
    return screenOffY;
  }

  public void setScreenOffY(double screenOffY) {
    this.screenOffY = screenOffY;
  }
}

package core;

public class GameContainer implements Runnable {

  private Thread thread;
  private AbstractGame game;
  private Window window;
  private Renderer renderer;
  private Input input;
  
  private int width = 640, height = 480;
  private double scale = 1;
  private String title = "JGameEngine";
  
  private boolean isRunning = false;
  private double frameLength = 1.0 / 60.0;
  //The number of draws that can be skipped in a row
  private int maxSkips = 5; 
  
  public GameContainer(AbstractGame game) {
    this.game = game;
  }
  
  public void start() {
    if (!isRunning) {
      isRunning = true;
      
      window = new Window(this);
      renderer = new Renderer(this);
      input = new Input(this);
      
      thread = new Thread(this);
      thread.run();
    }
  }
  
  public void stop() {
    isRunning = false;
  }
  
  public void run() {
    isRunning = true;
    
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
      // If true only renders if there has been an update
      // If false render FPS is unlocked
      boolean shouldRender = true;
      
      firstTime = System.nanoTime() / 1000000000.0;
      frameTime += firstTime - lastTime;
      lastTime = firstTime;
      
      input.update();
      game.update(this, frameLength);
      ticks++;
      
      renderer.clear();
      game.render(this, renderer);
      window.update();
      frames++;
      
      passedTime = (System.nanoTime() / 1000000000.0) - firstTime;
      // The time remaining in the frame
      timeLeft = frameLength - passedTime;
      if (timeLeft * 1000 > 16) {
        System.out.println(timeLeft * 1000);
      }
      
      //System.out.println((int)(firstTime*1000) + " " + passedTime + " " + unprocessedTime);
      //System.out.println(unprocessedTime - frameLength);
      //System.out.println(frameTime);
      
      // If we are ahead then sleep
      if (timeLeft > 0) {
        try {
          Thread.sleep((long) (timeLeft * 1000));
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      // If we are behind, do extra updates till we are good again
      int skippedDraws = 0;
      while (timeLeft < 0 && skippedDraws < maxSkips) {
        input.update();
        game.update(this, frameLength);
        
        skippedDraws++;
        ticks++;
        
        timeLeft += frameLength;
      }
      
      // If 1 seconds has passed since the last FPS update
      if (frameTime >= 1) {
        frameTime = 0;
        fps = frames;
        frames = 0;
        tps = ticks;
        ticks = 0;
        System.out.println("FPS: " + fps + " TPS: " + tps);
      }
      
      /*// We are into the next frame, do updates and stuff
      while (timeLeft >= frameLength) {
        //System.out.println("Update");
        
        input.update();
        game.update(this, frameLength);
        
        shouldRender = true;
        timeLeft -= frameLength;
        ticks++;
        
        if (frameTime >= 1) {
          frameTime = 0;
          fps = frames;
          frames = 0;
          tps = ticks;
          ticks = 0;
          System.out.println("FPS: " + fps + " TPS: " + tps);
        }
      }
      
      if (shouldRender) {
        //System.out.println("Render");
        
        renderer.clear();
        game.render(this, renderer);
        window.update();
        
        frames++;
      } else {
        // We are in the same frame, sleep until we are finished
        //System.out.println("Sleeping");
        try {
          Thread.sleep((long)(timeLeft * 1000));
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }*/
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
}

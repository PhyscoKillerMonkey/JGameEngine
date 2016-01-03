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
    double unprocessedTime = 0;
    
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
      boolean shouldRender = false;
      
      firstTime = System.nanoTime() / 1000000000.0;
      passedTime = firstTime - lastTime;
      lastTime = firstTime;
      
      unprocessedTime += passedTime;
      frameTime += passedTime;
      
      //System.out.println((int)(firstTime*1000) + " " + passedTime + " " + unprocessedTime);
      //System.out.println(unprocessedTime - frameLength);
      //System.out.println(frameTime);
      
      // We are into the next frame, do updates and stuff
      while (unprocessedTime >= frameLength) {
        //System.out.println("Update");
        
        input.update();
        game.update(this, frameLength);
        
        shouldRender = true;
        unprocessedTime -= frameLength;
        ticks++;
        
        if (frameTime >= 1) {
          frameTime = 0;
          fps = frames;
          frames = 0;
          tps = ticks;
          ticks = 0;
          //System.out.println("FPS: " + fps + " TPS: " + tps);
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
          Thread.sleep((long)(unprocessedTime * 1000));
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
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
}

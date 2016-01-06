package core;

import java.util.Stack;

import core.componenets.State;

public abstract class AbstractGame {

  private Stack<State> states = new Stack<>();

  public abstract void init(GameContainer gc);
  public abstract void update(GameContainer gc, double dt);
  public abstract void render(GameContainer gc, Renderer r);
  
  public State peek() {
    return states.peek();
  }
  
  public State get(int i) {
    return states.get(i);
  }
  
  public State getPrevious() {
    return states.get(states.size() - 2);
  }
  
  public void push(State s) {
    states.push(s);
  }
  
  public void pop() {
    states.peek().dispose();
    states.pop();
  }
  
  public void setState(State s) {
    states.pop();
    states.push(s);
  }
}

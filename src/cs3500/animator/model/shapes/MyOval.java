package cs3500.animator.model.shapes;

import cs3500.animator.model.misc.MyColor;
import cs3500.animator.model.misc.Posn;

/**
 * Represents an oval in the animator.
 */
public class MyOval extends Shape {

  /**
   * Constructs a MyOval without a given amount of sides because it must be 1.
   *
   * @param name        This MyOval's name
   * @param location    The location of this MyOval's center
   * @param dimensions  The width and height of this MyOval
   * @param color       This MyOval's color
   * @param lifetime    The times that this MyOval appears and disappears
   */
  public MyOval(String name, Posn location, Posn dimensions, MyColor color, Posn lifetime) {
    super(name, location, dimensions, color, lifetime);
    this.type = ShapeType.OVAL;
  }

  @Override
  public String[] getWLTypes() {
    String[] arr = {"X radius", "Y radius"};
    return arr;
  }
}

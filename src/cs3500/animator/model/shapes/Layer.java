package cs3500.animator.model.shapes;

import cs3500.animator.model.misc.MyColor;
import cs3500.animator.model.misc.Posn;

/**
 * Just here so that the dropdown menu can be broken up by layers.
 */
public class Layer extends Shape {

  /**
   * Represents a generic shape.
   *
   * @param name       This shape's name
   * @param location   The xy coorinates of this shape
   * @param dimensions The dimensions of this shape
   * @param color      This shape's color
   * @param lifetime   The times that this shape appears and disappears
   */
  public Layer(String name, Posn location, Posn dimensions, MyColor color, Posn lifetime) {
    super(name, location, dimensions, color, lifetime);
    this.type = ShapeType.LAYER;
  }

  @Override
  public String toString() {
    return type.toString() + " " + name + ":";
  }
}

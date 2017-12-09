package cs3500.animator.model.misc;

import java.awt.geom.Point2D;

/**
 * Represents a position (x,y).
 * Can also be used to represent other pairs of numbers such as shape dimensions and time durations.
 */
public class Posn extends Point2D.Double {

  /**
   * Constructs a cs3500.animator.model.misc.Posn.
   *
   * @param x The cs3500.animator.model.misc.Posn's x-coordinate (or first number)
   * @param y The cs3500.animator.model.misc.Posn's y-coordinate (or second number)
   */
  public Posn(double x, double y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Changes the coordinates of this Posn based on the factors in the given Posn.
   *
   * @param changeInLocation  The change in x and y that the move will apply
   */
  public void move(Posn changeInLocation) {
    this.x += changeInLocation.getX();
    this.y += changeInLocation.getY();
  }

  @Override
  public String toString() {
    return "(" + this.x + "," + this.y + ")";
  }

  /**
   * Returns this Posn's first number as an int.
   *
   * @return  The number
   */
  public int getIntX() {
    return (int) this.x;
  }

  /**
   * Returns this Posn's second number as an int.
   *
   * @return  The number
   */
  public int getIntY() {
    return (int) this.y;
  }
}

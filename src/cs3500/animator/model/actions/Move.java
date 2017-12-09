package cs3500.animator.model.actions;

import cs3500.animator.model.shapes.IShape;
import cs3500.animator.model.misc.Posn;

/**
 * Represents a move applied to a shape in the animator.
 */
public class Move implements IAction {

  private IShape s;
  private Posn oldLocation;
  private Posn newLocation;
  private Posn duration;
  private final ActionType type;

  /**
   * Constructs a cs3500.animator.model.actions.Move object.
   *
   * @param s            The shape to be moved
   * @param oldLocation  The shape's location before the move
   * @param newLocation  The shape's location after the move
   * @param duration     The duration of this move
   */
  public Move(IShape s, Posn oldLocation, Posn newLocation, Posn duration) {
    this.s = s;
    this.oldLocation = oldLocation;
    this.newLocation = newLocation;
    type = ActionType.MOVE;

    if (s.getAppear() <= duration.getX() && s.getDisappear() >= duration.getY()) {
      this.duration = duration;
    }
    else {
      throw new IllegalArgumentException("The duration of this move must be within the shape's "
              + "lifetime.");
    }
  }

  @Override
  public void execute(double t) {
    double ta = duration.getX();
    double tb = duration.getY();

    double a = oldLocation.getX();
    double b = newLocation.getX();
    int ftX = (int) (a * ((tb - t) / (tb - ta)) + b * ((t - ta) / (tb - ta)));

    a = oldLocation.getY();
    b = newLocation.getY();
    int ftY = (int) (a * ((tb - t) / (tb - ta)) + b * ((t - ta) / (tb - ta)));

    s.setLocation(new Posn(ftX, ftY));
  }

  @Override
  public Posn getDuration() {
    return this.duration;
  }

  @Override
  public ActionType getType() {
    return this.type;
  }

  @Override
  public String getShapeName() {
    return this.s.getName();
  }

  @Override
  public String getDescription() {
    String str = "";
    str += "moves from " + oldLocation + " to " + newLocation + " ";
    return str;
  }

  @Override
  public String getSVGDescription(double speed) {
    String str = "";
    if (this.oldLocation.getIntX() != this.newLocation.getIntX()) {
      str += "    <animate attributeType=\"xml\" begin=\""
              + (int) ((this.duration.getX() / speed) * 1000) + "ms\" dur=\""
              + (int) (((this.duration.getY() - this.duration.getX()) / speed) * 1000)
              + "ms\" attributeName=\"x\" from=\"" + this.oldLocation.getIntX() + "\" to=\""
              + this.newLocation.getIntX() + "\" fill=\"freeze\" />\n";
    }
    if (this.oldLocation.getIntY() != this.newLocation.getIntY()) {
      str += "    <animate attributeType=\"xml\" begin=\""
              + (int) ((this.duration.getX() / speed) * 1000) + "ms\" dur=\""
              + (int) (((this.duration.getY() - this.duration.getX()) / speed) * 1000)
              + "ms\" attributeName=\"y\" from=\"" + this.oldLocation.getIntY() + "\" to=\""
              + this.newLocation.getIntY() + "\" fill=\"freeze\" />\n";
    }

    return str;
  }
}

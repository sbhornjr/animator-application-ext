package cs3500.animator.model.actions;

import cs3500.animator.model.misc.Posn;

/**
 * Details the methods that any action class must implement.
 */
public interface IAction {

  /**
   * Executes this action.
   *
   * @param time  The current time in the animation
   */
  void execute(double time);

  /**
   * Returns a description of this action.
   *
   * @return  The description as a String
   */
  String toString();

  /**
   * Gets this action's duration.
   *
   * @return  The duration
   */
  Posn getDuration();

  /**
   * Gets this actions' type.
   *
   * @return  The type
   */
  ActionType getType();

  /**
   * Gets the name of this action's shape.
   *
   * @return  The name
   */
  String getShapeName();

  /**
   * Creates the part of the description that most
   * heavily relies on the type of Action this is.
   *
   * @return  The description.
   */
  String getDescription();

  /**
   * Creates the description needed to be compatible with svg files.
   *
   * @param   speed The speed of the animation.
   * @return  The SVG description.
   */
  String getSVGDescription(double speed);
}

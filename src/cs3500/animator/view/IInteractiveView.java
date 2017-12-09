package cs3500.animator.view;

import cs3500.animator.model.shapes.IShape;

import java.awt.event.ActionListener;

/**
 * The interface that dictates what methods a view for an interactive animation application must
 * implement.
 */
public interface IInteractiveView {

  /**
   * Runs an animation.
   */
  void run();

  /**
   * Displays an animation in SVG text format.
   */
  void display();

  /**
   * Sets this view's action listener to the given one.
   *
   * @param listener  The action listener to be set
   */
  void setActionListener(ActionListener listener);

  /**
   * Adds the newly set listeners to each button.
   */
  void addActionListeners();

  /**
   * Toggles whether or not the animation is paused.
   */
  void togglePause();

  /**
   * Toggles whether or not the animation will loop back to the start when it finishes.
   */
  void toggleLoop();

  /**
   * Sets the animation's speed to the given new speed.
   *
   * @param newSpeed  The new speed
   */
  void setSpeed(int newSpeed);

  /**
   * Outputs the animation as a SVG animation to the specified output file.
   *
   * @param ofile   The output file as a string.
   */
  void export(String ofile);

  /**
   * Toggles whether or not the given shape is visible in the animation.
   *
   * @param s The shape
   */
  void toggleShape(IShape s);

  /**
   * Changes the background color's red component to the given red value.
   *
   * @param newRed  The new red value as an int.
   */
  void changeRed(int newRed);

  /**
   * Changes the background color's green component to the given green value.
   *
   * @param newGreen  The new green value as an int.
   */
  void changeGreen(int newGreen);

  /**
   * Changes the background color's blue component to the given blue value.
   *
   * @param newBlue  The new blue value as an int.
   */
  void changeBlue(int newBlue);
}

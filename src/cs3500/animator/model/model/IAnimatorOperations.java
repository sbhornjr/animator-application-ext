package cs3500.animator.model.model;

import java.util.ArrayList;

import cs3500.animator.model.actions.IAction;
import cs3500.animator.model.misc.MyColor;
import cs3500.animator.model.misc.Posn;
import cs3500.animator.model.shapes.IShape;
import cs3500.animator.model.shapes.ShapeType;
import cs3500.animator.model.actions.ActionType;
import cs3500.animator.view.TweenModelBuilder;

/**
 * Details the operations that any Animator model implementing this interface must provide.
 */
public interface IAnimatorOperations {

  /**
   * Creates a new shape and adds it to the model.
   *
   * @param st          The type of shape to be created
   * @param name        The name of the shape
   * @param location    The location of the shape's center
   * @param dimensions  The dimensions of the test
   * @param sides       The amount of sides that the shape has
   * @param color       The color of the shape
   * @param lifetime    The times that the shape appears and disappears
   */
  void createShape(ShapeType st, String name, Posn location, Posn dimensions, int sides,
                          MyColor color, Posn lifetime);

  /**
   * Creates a new action and adds it to the model.
   *
   * @param at        The type of action to be created
   * @param shapeName The name of the shape that the action will be applied to
   * @param oldTrait  The old trait that the shape has before the action
   * @param newTrait  The new trait that the shape will have after the action
   * @param duration  The duration of the action
   */
  void createAction(ActionType at, String shapeName, Object oldTrait, Object newTrait,
                           Posn duration);

  /**
   * Executes the action at the given index.
   *
   * @param actionIndex The index of the desired action in the order of all actions
   * @param time        The current time in the animation
   */
  void executeAction(int actionIndex, double time);

  /**
   * Returns a description of the animation.
   *
   * @return  The description
   */
  String getDescription();

  /**
   * Returns the shapes in this model.
   *
   * @return  An ArrayList of the shapes
   */
  ArrayList<IShape> getShapes();

  /**
   * Returns the actions in this model.
   *
   * @return  An ArrayList of the models
   */
  ArrayList<IAction> getActions();

  /**
   * Returns the time that the animation ends.
   *
   * @return  The time
   */
  double getEndTime();

  /**
   * Sets the animation's initial background color to the given color.
   *
   * @param color   The background color.
   */
  void setBackColor(MyColor color);

  /**
   * Returns the animation's initial background color.
   * @return
   */
  MyColor getBackColor();

  /**
   * Constructs a builder for configuring and then creating an Animation model
   * instance.
   *
   * @return The new builder
   */
  TweenModelBuilder<IAnimatorOperations> builder();
}

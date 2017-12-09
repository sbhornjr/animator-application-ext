package cs3500.animator.view;

import cs3500.animator.model.shapes.IShape;

/**
 * Describes the methods that an animation view must implement.
 */
public interface IAnimationView {

  /**
   * Displays an animation in a certain text format.
   */
  void display();

  /**
   * Runs an animation.
   */
  void run();

  /**
   * Returns this view's type.
   *
   * @return  The type
   */
  ViewType getViewType();
}

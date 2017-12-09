package cs3500.animator.controller;

import cs3500.animator.view.IAnimationView;
import cs3500.animator.view.ViewType;

/**
 * The controller for the animation application:
 * transmits inputs between the user and the view.
 */
public class AnimationController implements IAnimationController {

  private IAnimationView view;

  /**
   * Constructor for the AnimationController.
   * @param view  The view.
   */
  public AnimationController(IAnimationView view) {
    this.view = view;
  }

  @Override
  public void start() {
    if (view.getViewType() == ViewType.VISUAL) {
      view.run();
    }
    else {
      view.display();
    }
  }
}

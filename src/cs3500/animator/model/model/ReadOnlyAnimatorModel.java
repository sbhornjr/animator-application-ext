package cs3500.animator.model.model;

import cs3500.animator.model.actions.ActionType;
import cs3500.animator.model.actions.IAction;
import cs3500.animator.model.misc.MyColor;
import cs3500.animator.model.misc.Posn;
import cs3500.animator.model.shapes.IShape;
import cs3500.animator.model.shapes.MyRectangle;
import cs3500.animator.model.shapes.MyOval;
import cs3500.animator.model.shapes.ShapeType;
import cs3500.animator.model.shapes.ReadOnlyRectangle;
import cs3500.animator.model.shapes.ReadOnlyOval;
import cs3500.animator.view.TweenModelBuilder;

import java.util.ArrayList;

/**
 * A read-only version of the AnimatorModel that cannot be mutated by any view.
 */
public class ReadOnlyAnimatorModel implements IAnimatorOperations {
  private IAnimatorOperations model;
  private ArrayList<IAction> actions;
  private ArrayList<IShape> shapes;

  /**
   * Constructs a ReadOnlyAnimatorModel from a given AnimatorModel.
   *
   * @param model The AnimatorModel
   */
  public ReadOnlyAnimatorModel(IAnimatorOperations model) {
    this.model = model;
    this.actions = model.getActions();
    this.shapes = new ArrayList<>();

    ArrayList<IShape> oldShapes = model.getShapes();

    for (IShape sh : oldShapes) {
      if (sh instanceof MyRectangle) {
        this.shapes.add(new ReadOnlyRectangle(sh));
      }
      else if (sh instanceof MyOval) {
        this.shapes.add(new ReadOnlyOval(sh));
      }
    }
  }

  @Override
  public void createShape(ShapeType st, String name, Posn location, Posn dimensions, int sides,
                          MyColor color, Posn lifetime) {
    throw new UnsupportedOperationException("You can't mutate the read-only model.");
  }

  @Override
  public void createAction(ActionType at, String shapeName, Object oldTrait, Object newTrait,
                           Posn duration) {
    throw new UnsupportedOperationException("You can't mutate the read-only model.");
  }

  @Override
  public void executeAction(int actionIndex, double time) {
    IAction a = getActions().get(actionIndex);
    if (time >= a.getDuration().getX() && time <= a.getDuration().getY()) {
      a.execute(time);
    }
  }

  @Override
  public String getDescription() {
    return model.getDescription();
  }

  @Override
  public ArrayList<IShape> getShapes() {
    return this.shapes;
  }

  @Override
  public ArrayList<IAction> getActions() {
    return this.actions;
  }

  @Override
  public double getEndTime() {
    return model.getEndTime();
  }

  @Override
  public void setBackColor(MyColor color) {
    throw new UnsupportedOperationException("You can't mutate the read-only model.");
  }

  @Override
  public MyColor getBackColor() {
    return model.getBackColor();
  }

  @Override
  public TweenModelBuilder<IAnimatorOperations> builder() {
    return model.builder();
  }
}

package cs3500.animator.model.shapes;

import cs3500.animator.model.actions.IAction;
import cs3500.animator.model.misc.MyColor;
import cs3500.animator.model.misc.Posn;

import java.util.ArrayList;

/**
 * A read-only version of the Shape class that cannot be mutated by the view.
 */
public abstract class ReadOnlyShape implements IShape {

  protected IShape shape;

  /**
   * Represents a generic read-only shape.
   *
   * @param sh  The regular shape that should be represented in a read-only way here.
   */
  public ReadOnlyShape(IShape sh) {
    this.shape = sh;
  }

  @Override
  public String getName() {
    return shape.getName();
  }

  @Override
  public int getX() {
    return shape.getX();
  }

  @Override
  public int getY() {
    return shape.getY();
  }

  @Override
  public MyColor getColor() {
    return shape.getColor();
  }

  @Override
  public int getWidth() {
    return shape.getWidth();
  }

  @Override
  public int getHeight() {
    return shape.getHeight();
  }

  @Override
  public double getAppear() {
    return shape.getAppear();
  }

  @Override
  public double getDisappear() {
    return shape.getDisappear();
  }

  @Override
  public ShapeType getType() {
    return shape.getType();
  }

  @Override
  public String getPosLocation() {
    return shape.getPosLocation();
  }

  @Override
  public String[] getWLTypes() {
    return shape.getWLTypes();
  }

  @Override
  public void setLocation(Posn newLocation) {
    throw new UnsupportedOperationException("Can't mutate shapes in a read-only model.");
  }

  @Override
  public void setDimensions(Posn newDimensions) {
    throw new UnsupportedOperationException("Can't mutate shapes in a read-only model.");
  }

  @Override
  public void setColor(MyColor newColor) {
    throw new UnsupportedOperationException("Can't mutate shapes in a read-only model.");
  }

  @Override
  public void addAction(IAction a) {
    throw new UnsupportedOperationException("Can't mutate shapes in a read-only model.");
  }

  @Override
  public ArrayList<IAction> getActions() {
    return shape.getActions();
  }

  @Override
  public String getColorAsInt() {
    return shape.getColorAsInt();
  }

  @Override
  public void setDefault() {
    shape.setDefault();
  }

  @Override
  public boolean isVisible() {
    return shape.isVisible();
  }

  @Override
  public void setVisible() {
    shape.setVisible();
  }

  @Override
  public String toString() {
    return shape.toString();
  }

  @Override
  public boolean equals(Object o) {
    return shape.equals(o);
  }

  @Override
  public int hashCode() {
    return shape.hashCode();
  }
}

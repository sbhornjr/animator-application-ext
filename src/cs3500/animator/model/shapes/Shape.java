package cs3500.animator.model.shapes;

import java.util.ArrayList;

import cs3500.animator.model.actions.IAction;
import cs3500.animator.model.misc.MyColor;
import cs3500.animator.model.misc.Posn;

/**
 * Represents a polygon in an animation.
 */
public abstract class Shape implements IShape {

  protected String name;
  protected Posn location;
  protected Posn dimensions;
  protected MyColor color;
  protected Posn lifetime;
  protected ShapeType type;
  protected ArrayList<IAction> actions;
  protected Posn initLocation;
  protected Posn initDimensions;
  protected MyColor initColor;
  protected boolean isVisible;

  /**
   * Represents a generic shape.
   *
   * @param name        This shape's name
   * @param location    The xy coorinates of this shape
   * @param dimensions  The dimensions of this shape
   * @param color       This shape's color
   * @param lifetime    The times that this shape appears and disappears
   */
  public Shape(String name, Posn location, Posn dimensions, MyColor color, Posn lifetime) {
    this.name = name;
    this.dimensions = dimensions;
    this.color = color;
    this.lifetime = lifetime;
    this.location = location;
    this.actions = new ArrayList<>();
    this.initDimensions = dimensions;
    this.initLocation = location;
    this.initColor = color;
    this.isVisible = true;
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public int getX() {
    return this.location.getIntX();
  }

  @Override
  public int getY() {
    return this.location.getIntY();
  }

  @Override
  public MyColor getColor() {
    return this.color;
  }

  @Override
  public int getWidth() {
    return this.dimensions.getIntX();
  }

  @Override
  public int getHeight() {
    return this.dimensions.getIntY();
  }

  @Override
  public double getAppear() {
    return this.lifetime.getX();
  }

  @Override
  public double getDisappear() {
    return this.lifetime.getY();
  }

  @Override
  public ShapeType getType() {
    return this.type;
  }

  @Override
  public String getPosLocation() {
    return "Center";
  }

  @Override
  public String[] getWLTypes() {
    String[] arr = {"Width", "Height"};
    return arr;
  }

  @Override
  public void setLocation(Posn newLocation) {
    location = newLocation;
  }

  @Override
  public void setDimensions(Posn newDimensions) {
    dimensions = newDimensions;
  }

  @Override
  public void setColor(MyColor newColor) {
    color = newColor;
  }

  @Override
  public void addAction(IAction a) {
    this.actions.add(a);
  }

  @Override
  public ArrayList<IAction> getActions() {
    return this.actions;
  }

  @Override
  public String getColorAsInt() {
    return this.color.asInt();
  }

  @Override
  public void setDefault() {
    this.dimensions = initDimensions;
    this.location = initLocation;
    this.color = initColor;
  }

  @Override
  public boolean isVisible() {
    return this.isVisible;
  }

  @Override
  public void setVisible() {
    isVisible = !isVisible;
  }

  @Override
  public String toString() {
    String shapeType = type.toString();
    shapeType = shapeType.charAt(0) + shapeType.substring(1).toLowerCase();
    String s = shapeType + " " + name;

    if (isVisible) {
      return "✓" + s;
    }
    else {
      return s;
    }
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof Shape) {
      return this.name.equals(((Shape) o).name)
              && this.type == ((Shape) o).type;
    }
    else {
      return false;
    }
  }

  @Override
  public int hashCode() {
    int result = 0;
    if (this.type == ShapeType.RECTANGLE) {
      result += 1000;
    }
    return result + this.getHeight() + this.getWidth() + this.getX() + this.getY()
            + this.getColor().getRGB();
  }
}

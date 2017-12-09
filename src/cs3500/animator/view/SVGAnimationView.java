package cs3500.animator.view;

import java.io.IOException;
import java.util.ArrayList;

import cs3500.animator.model.actions.IAction;
import cs3500.animator.model.model.IAnimatorOperations;
import cs3500.animator.model.shapes.IShape;
import cs3500.animator.model.shapes.ShapeType;

public class SVGAnimationView implements IAnimationView {
  private Appendable ap;
  private IAnimatorOperations model;
  private double speed;
  private ViewType type = ViewType.SVG;

  private static int SVG_WIDTH = 1000;
  private static int SVG_HEIGHT = 1000;

  /**
   * Constructs a svg animation view.
   * @param ap    Where the view sends its output.
   * @param model The model where the necessary data is stored.
   */
  public SVGAnimationView(Appendable ap, IAnimatorOperations model, double speed) {
    this.ap = ap;
    this.model = model;
    this.speed = speed;
  }

  /**
   * Displays an animation an formatted SVG text.
   */
  public void display() {
    ArrayList<IShape> shapes = model.getShapes();
    String s = "";

    s += "<svg width=\"" + SVG_WIDTH + "\" height=\"" + SVG_HEIGHT
            + "\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\">\n\n";

    for (IShape sh : shapes) {
      if (sh.getType() == ShapeType.RECTANGLE) {
        s += "<rect id=\"" + sh.getName() + "\" x=\"" + sh.getX() + "\" y=\"" + sh.getY()
                + "\" width=\"" + sh.getWidth() + "\" height=\"" + sh.getHeight()
                + "\" fill=\"rgb" + sh.getColorAsInt() + "\" visibility=\"visible\" >\n";
        s += this.actionsAsString(sh);
        s += "</rect>\n\n";
      }
      else {
        s += "<ellipse id=\"" + sh.getName() + "\" cx=\"" + sh.getX() + "\" cy=\"" + sh.getY()
                + "\" rx=\"" + sh.getWidth() + "\" ry=\"" + sh.getHeight()
                + "\" fill=\"rgb" + sh.getColorAsInt() + "\" visibility=\"visible\" >\n";
        String act = this.actionsAsString(sh);
        act = act.replace("\"x\"", "\"cx\"");
        act = act.replace("\"y\"", "\"cy\"");
        act = act.replace("\"width\"", "\"rx\"");
        act = act.replace("\"height\"", "\"ry\"");
        s += act;
        s += "</ellipse>\n\n";
      }
    }
    s += "</svg>";

    try {
      ap.append(s);
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }

  @Override
  public void run() {
    throw new UnsupportedOperationException("The SVG View does not run animations.");
  }

  @Override
  public ViewType getViewType() {
    return this.type;
  }

  /**
   * Creates svg descriptions for all of the actions that affect the given shape.
   *
   * @param sh  The shape.
   * @return    All of the shape's actions as an svg-compatible string.
   */
  private String actionsAsString(IShape sh) {
    String s = "";
    for (IAction a : sh.getActions()) {
      s += a.getSVGDescription(speed);
    }
    return s;
  }
}


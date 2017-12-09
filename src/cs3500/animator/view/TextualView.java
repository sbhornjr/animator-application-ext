package cs3500.animator.view;

import java.io.IOException;
import java.util.ArrayList;

import cs3500.animator.model.actions.IAction;
import cs3500.animator.model.model.IAnimatorOperations;
import cs3500.animator.model.shapes.IShape;

/**
 * Displays an animation as a textual description.
 */
public class TextualView implements IAnimationView {
  private Appendable ap;
  private IAnimatorOperations model;
  private int speed;
  private ViewType type = ViewType.TEXT;

  /**
   * Constructs a TextualView.
   *
   * @param ap    Where the view sends its output
   * @param model The model where the necessary data is stored
   * @param speed The speed at which the animation plays
   */
  public TextualView(Appendable ap, IAnimatorOperations model, int speed) {
    this.ap = ap;
    this.model = model;
    this.speed = speed;
  }

  /**
   * Displays an animation as a text description.
   */
  public void display() {
    ArrayList<IShape> shapes = model.getShapes();
    ArrayList<IAction> actions = model.getActions();
    String s = "";
    s += "Shapes:\n";
    for (int i = 0; i < shapes.size(); i++) {
      if (i != 0) {
        s += "\n";
      }
      IShape curr = shapes.get(i);
      s += "Name: " + curr.getName() + "\n";
      s += "Type: " + curr.getType().toString().toLowerCase() + "\n";
      s += curr.getPosLocation() + ": " + "(" + (double) curr.getX()
              + "," + (double) curr.getY() + ")" + ", ";
      String[] wl = curr.getWLTypes();
      s += wl[0] + ": " + (double) curr.getWidth() + ", "
              + wl[1] + ": " + (double) curr.getHeight() + ", ";
      s += "Color: " + curr.getColor() + "\n";
      s += "Appears at t=" + curr.getAppear() / speed + "s\n";
      s += "Disappears at t=" + curr.getDisappear() / speed + "s\n";
    }

    for (int i = 0; i < actions.size(); i++) {
      s += "\n";
      IAction curr = actions.get(i);
      s += "Shape " + curr.getShapeName() + " ";
      String str = curr.getDescription();
      s += str;
      s += "from t=" + curr.getDuration().getX() / speed + "s to t="
              + curr.getDuration().getY() / speed + "s";
    }
    try {
      ap.append(s);
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }

  @Override
  public void run() {
    throw new UnsupportedOperationException("The Textual View does not run animations.");
  }

  @Override
  public ViewType getViewType() {
    return this.type;
  }
}

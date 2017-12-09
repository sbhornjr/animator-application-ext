package cs3500.animator.model.actions;

import cs3500.animator.model.shapes.IShape;
import cs3500.animator.model.misc.MyColor;
import cs3500.animator.model.misc.Posn;

/**
 * Represents a color change applied to a shape in the animator.
 */
public class ColorChange implements IAction {

  private IShape s;
  private MyColor oldColor;
  private MyColor newColor;
  private Posn duration;
  private final ActionType type;

  /**
   * Constructs a ColorChange object.
   *
   * @param s         The shape whose color will change
   * @param oldColor  The shape's old color before the change
   * @param newColor  The shape's new color after the change
   * @param duration  The duration of this color change
   */
  public ColorChange(IShape s, MyColor oldColor, MyColor newColor, Posn duration) {
    this.s = s;
    this.oldColor = oldColor;
    this.newColor = newColor;
    type = ActionType.COLOR_CHANGE;

    if (s.getAppear() <= duration.getX() && s.getDisappear() >= duration.getY()) {
      this.duration = duration;
    }
    else {
      throw new IllegalArgumentException("The duration of this move must be within the shape's "
              + "lifetime.");
    }
  }

  @Override
  public void execute(double time) {
    float ta = (float) duration.getX();
    float tb = (float) duration.getY();
    float t = (float) time;
    float[] oldRGB = new float[3];
    float[] newRGB = new float[3];

    oldColor.getRGBColorComponents(oldRGB);
    newColor.getRGBColorComponents(newRGB);

    float a = oldRGB[0];
    float b = newRGB[0];
    float ftRed = (a * ((tb - t) / (tb - ta)) + b * ((t - ta) / (tb - ta)));

    a = oldRGB[1];
    b = newRGB[1];
    float ftGreen = (a * ((tb - t) / (tb - ta)) + b * ((t - ta) / (tb - ta)));

    a = oldRGB[2];
    b = newRGB[2];
    float ftBlue = (a * ((tb - t) / (tb - ta)) + b * ((t - ta) / (tb - ta)));

    s.setColor(new MyColor(ftRed, ftGreen, ftBlue));
  }

  @Override
  public Posn getDuration() {
    return this.duration;
  }

  @Override
  public ActionType getType() {
    return this.type;
  }

  @Override
  public String getShapeName() {
    return this.s.getName();
  }

  @Override
  public String getDescription() {
    String str = "";
    str += "changes color from " + oldColor + " to " + newColor + " ";
    return str;
  }

  @Override
  public String getSVGDescription(double speed) {
    String str = "";
    str += "    <animate attributeType=\"xml\" begin=\""
            + (int) ((this.duration.getX() / speed) * 1000) + "ms\" dur=\""
            + (int) (((this.duration.getY() - this.duration.getX()) / speed) * 1000)
            + "ms\" attributeName=\"fill\" from=\"rgb" + this.oldColor.asInt() + "\" to=\"rgb"
            + this.newColor.asInt() + "\" fill=\"freeze\" />\n";
    return str;
  }
}

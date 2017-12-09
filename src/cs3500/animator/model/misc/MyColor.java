package cs3500.animator.model.misc;

import java.awt.Color;

/**
 * A version of Java's Color that prints better.
 */
public class MyColor extends Color {

  /**
   * Constructs a cs3500.animator.model.misc.MyColor object.
   *
   * @param r The amount of red in the color
   * @param g The amount of green in the color
   * @param b The amount of blue in the color
   */
  public MyColor(float r, float g, float b) {
    super(r, g, b);
  }

  @Override
  public String toString() {
    float[] rgb = this.getRGBColorComponents(new float[3]);
    return "(" + rgb[0] + "," + rgb[1] + "," + rgb[2] + ")";
  }

  /**
   * Converts this color's rgb values to ints instead of floats and returns it as a string.
   *
   * @return  The color as a string.
   */
  public String asInt() {
    float[] rgb = this.getRGBColorComponents(new float[3]);
    return "(" + (int)(rgb[0] * 255) + ","
            + (int)(rgb[1] * 255) + "," + (int)(rgb[2] * 255) + ")";
  }
}

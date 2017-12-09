package cs3500.animator.model.shapes;

/**
 * A read-only version of the MyRectangle class that cannot be mutated by the view.
 */
public class ReadOnlyRectangle extends ReadOnlyShape {

  /**
   * Constructs a ReadOnlyRectangle.
   *
   * @param sh  The Rectangle to be represented in a read-only way.
   */
  public ReadOnlyRectangle(IShape sh) {
    super(sh);
  }

  @Override
  public String getPosLocation() {
    return "Lower-left corner";
  }
}

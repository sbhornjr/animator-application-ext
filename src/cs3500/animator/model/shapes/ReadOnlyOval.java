package cs3500.animator.model.shapes;

/**
 * A read-only version of the MyOval class that cannot be mutated by the view.
 */
public class ReadOnlyOval extends ReadOnlyShape {

  /**
   * Constructs a ReadOnlyOval.
   *
   * @param sh  The oval that should be represented in a read-only way.
   */
  public ReadOnlyOval(IShape sh) {
    super(sh);
  }

  @Override
  public String[] getWLTypes() {
    return shape.getWLTypes();
  }
}

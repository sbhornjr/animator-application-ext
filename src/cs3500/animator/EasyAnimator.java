package cs3500.animator;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.WindowConstants;
import javax.swing.JOptionPane;

import cs3500.animator.controller.AnimationController;
import cs3500.animator.controller.IAnimationController;
import cs3500.animator.controller.InteractiveAnimationController;
import cs3500.animator.model.model.AnimatorModel;
import cs3500.animator.model.model.IAnimatorOperations;
import cs3500.animator.model.model.ReadOnlyAnimatorModel;
import cs3500.animator.view.AnimationFileReader;
import cs3500.animator.view.IAnimationView;
import cs3500.animator.view.IInteractiveView;
import cs3500.animator.view.InteractiveView;
import cs3500.animator.view.SVGAnimationView;
import cs3500.animator.view.TextualView;
import cs3500.animator.view.ViewType;
import cs3500.animator.view.VisualAnimationView;

/**
 * The class that facilitates running an animation using input from the command line.
 */
public final class EasyAnimator {

  /**
   * Runs an animation using arguments passed on the command line.
   *
   * @param args  The arguments
   */
  public static void main(String[] args) {
    String inputFile = "";
    String viewType = "";
    int speed = 1;
    String errorMsg = "";

    // reads the command line input
    for (int i = 0; i < args.length; i += 2) {
      String identifier = args[i];
      String param = args[i + 1];

      switch (identifier) {
        case "-if":
          inputFile = param;
          break;
        case "-iv":
          viewType = param;
          break;
        case "-speed":
          speed = Integer.parseInt(param);
          break;
        default:
      }
      showError(errorMsg);
    }

    if (inputFile.equals("")) {
      showError("No input file provided.");
    }
    if (viewType.equals("")) {
      showError("No view type provided.");
    }

    // creates the model
    AnimationFileReader afr = new AnimationFileReader();
    IAnimatorOperations model = new AnimatorModel();
    try {
      model = afr.readFile(inputFile, model.builder());
    } catch (FileNotFoundException e) {
      errorMsg = e.getMessage();
    }
    showError(errorMsg);

    // creates the appendable object for the view if it needs one.
    String output;
    if (Arrays.asList(args).contains("-o")) {
      int i;
      for (i = 0; i < args.length - 1; i++) {
        if (args[i].equals("-o")) {
          break;
        }
      }
      output = args[i + 1];
    }
    else {
      output = "out";
    }

    // creates the view
    FileWriter writer = null;
    IAnimationView view = null;
    if (output.equals("out")) {
      view = viewFactory(viewType, System.out, model, speed);
    }
    else {
      try {
        writer = new FileWriter(output);
        view = viewFactory(viewType, writer, model, speed);
      } catch (IOException e) {
        errorMsg = e.getMessage();
      }
      showError(errorMsg);
    }

    // creates the controller and starts the animation
    IAnimationController controller;
    if (view.getViewType() == ViewType.INTERACTIVE) {
      controller = new InteractiveAnimationController((IInteractiveView) view);
    }
    else {
      controller = new AnimationController(view);
      controller.start();
    }

    if (writer != null) {
      try {
        writer.close();
      } catch (IOException e) {
        errorMsg = e.getMessage();
      }
      showError(errorMsg);
    }
  }

  /**
   * Shows a pop-up error message.
   *
   * @param errorMsg  The message to be displayed
   */
  private static void showError(String errorMsg) {
    if (!errorMsg.equals("")) {
      JFrame error = new JFrame("Error");
      error.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
      JOptionPane.showMessageDialog(error, errorMsg);
    }
  }

  /**
   * Generates a view base on the given type string.
   *
   * @param type  The type of view to create
   * @param out   The appendable that the view may need
   * @param model The model whose data the view will use
   * @param speed The speed at which the animation will run
   * @return      The view
   */
  private static IAnimationView viewFactory(String type, Appendable out, IAnimatorOperations model,
                                     int speed) {
    String errorMsg;
    switch (type) {
      case "text":
        return new TextualView(out, new ReadOnlyAnimatorModel(model), speed);
      case "visual":
        return new VisualAnimationView(new ReadOnlyAnimatorModel(model), speed);
      case "svg":
        return new SVGAnimationView(out, new ReadOnlyAnimatorModel(model), speed);
      case "interactive":
        return new InteractiveView(new ReadOnlyAnimatorModel(model), speed);
      default:
        errorMsg = "Invalid view type provided";
    }
    showError(errorMsg);
    return null;
  }
}

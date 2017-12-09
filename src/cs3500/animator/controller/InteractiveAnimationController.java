package cs3500.animator.controller;

import cs3500.animator.model.shapes.IShape;
import cs3500.animator.view.IInteractiveView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The controller for the interactive animation:
 * transmits inputs between the user and the view.
 */
public class InteractiveAnimationController implements IAnimationController,
        IInteractiveAnimationController, ActionListener {

  private IInteractiveView view;

  /**
   * Constructor for the InteractiveAnimationController.
   * @param view  The view.
   */
  public InteractiveAnimationController(IInteractiveView view) {
    this.view = view;

    view.setActionListener(this);
    view.addActionListeners();
  }

  @Override
  public void start() {
    view.run();
  }

  @Override
  public void pauseToggled() {
    view.togglePause();
  }

  @Override
  public void loopToggled() {
    view.toggleLoop();
  }

  @Override
  public void export(String ofile) {
    view.export(ofile);
  }

  @Override
  public void speedChanged(int newSpeed) {
    view.setSpeed(newSpeed);
  }

  @Override
  public void shapeToggled(IShape s) {
    view.toggleShape(s);
  }

  @Override
  public void redChanged(int newRed) {
    view.changeRed(newRed);
  }

  @Override
  public void greenChanged(int newGreen) {
    view.changeGreen(newGreen);
  }

  @Override
  public void blueChanged(int newBlue) {
    view.changeBlue(newBlue);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    String ac = e.getActionCommand();
    switch (ac) {
      case "START":
        start();
        break;
      case "TOGGLE PAUSE":
        pauseToggled();
        break;
      case "TOGGLE LOOP":
        loopToggled();
        break;
      case "EXPORT":
        export(((JTextField)e.getSource()).getText() + ".svg");
        break;
      case "SPEED CHANGE":
        speedChanged(Integer.parseInt(((JTextField)e.getSource()).getText()));
        break;
      case "SHAPE REMOVED":
        shapeToggled((IShape) ((JComboBox)e.getSource()).getSelectedItem());
        break;
      case "RED":
        redChanged(Integer.parseInt(((JTextField)e.getSource()).getText()));
        break;
      case "GREEN":
        greenChanged(Integer.parseInt(((JTextField)e.getSource()).getText()));
        break;
      case "BLUE":
        blueChanged(Integer.parseInt(((JTextField)e.getSource()).getText()));
        break;
    }
  }
}

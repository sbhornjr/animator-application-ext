package cs3500.animator.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.WindowConstants;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import cs3500.animator.model.model.IAnimatorOperations;

/**
 * Displays an animation visually inside a new window.
 */
public class VisualAnimationView extends JFrame implements IAnimationView, ActionListener {
  private IAnimatorOperations model;
  private double time;
  private int speed;
  private Timer timer;
  private ViewType type;

  private static int FRAME_WIDTH = 1000;
  private static int FRAME_HEIGHT = 800;

  /**
   * Constructs a VisualAnimationView.
   *
   * @param model The model where the necessary data is stored
   * @param speed The speed at which the animation plays
   */
  public VisualAnimationView(IAnimatorOperations model, int speed) {
    super();
    this.model = model;
    this.speed = speed;
    time = 0;
    type = ViewType.VISUAL;

    this.setTitle("Easy Animator Application");
    this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    this.setLayout(new BorderLayout());
    JPanel animationPanel = new AnimationPanel(this.model.getShapes());
    animationPanel.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
    JScrollPane scrollPane = new JScrollPane(animationPanel);
    this.add(scrollPane, BorderLayout.CENTER);

    int delay = 1000 / speed;
    timer = new Timer(delay, this);
    timer.setInitialDelay(0);

    this.pack();
    this.setVisible(true);
  }

  @Override
  public void display() {
    throw new UnsupportedOperationException(
            "The visual view does not output any text descriptions.");
  }

  @Override
  public void run() {
    timer.start();
  }

  @Override
  public ViewType getViewType() {
    return this.type;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    for (int i = 0; i < model.getActions().size(); i++) {
      model.executeAction(i, time * speed);
    }
    this.repaint();
    time += 1 / (double) speed;
  }
}

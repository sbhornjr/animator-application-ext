package cs3500.animator.view;

import cs3500.animator.model.actions.IAction;
import cs3500.animator.model.misc.MyColor;
import cs3500.animator.model.misc.Posn;
import cs3500.animator.model.model.IAnimatorOperations;
import cs3500.animator.model.shapes.IShape;
import cs3500.animator.model.shapes.Layer;
import cs3500.animator.model.shapes.MyRectangle;
import cs3500.animator.model.shapes.ShapeType;

import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Displays an animation with an interface that allows a user to edit the playback of the animation.
 */
public class InteractiveView extends JFrame implements IAnimationView, IInteractiveView,
        ActionListener {
  private IAnimatorOperations model;
  private int speed;
  private int tick;
  private ViewType type;
  private boolean paused;
  private boolean looping;
  private Timer timer;
  private FileWriter fw;
  private ActionListener listener;

  private JButton startButton;
  private JButton pauseButton;
  private JButton loopButton;
  private JTextField export;
  private JTextField speedChanger;
  private JComboBox<IShape> shapeRemover;
  private JTextArea stats;

  private int redInt;
  private int greenInt;
  private int blueInt;
  private JTextField red;
  private JTextField green;
  private JTextField blue;
  private IShape background;

  private static int FRAME_WIDTH = 1000;
  private static int FRAME_HEIGHT = 700;

  /**
   * Constructs an InteractiveView.
   *
   * @param model The model containing the data for the animation
   * @param speed The speed at which the animation will start running
   */
  public InteractiveView(IAnimatorOperations model, int speed) {
    this.model = model;
    this.speed = speed;
    this.type = ViewType.INTERACTIVE;
    this.paused = true;
    this.looping = false;

    MyColor initBackColor = model.getBackColor();
    this.redInt = initBackColor.getRed();
    this.greenInt = initBackColor.getGreen();
    this.blueInt = initBackColor.getBlue();
    this.background = new MyRectangle("background", new Posn(0, 0),
            new Posn(FRAME_WIDTH, FRAME_HEIGHT), initBackColor,
            new Posn(0, model.getEndTime()));

    int delay = 1000 / speed;
    timer = new Timer(delay, this);
    tick = 0;

    this.setTitle("Easy Animator Application");
    this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    this.setLayout(new BorderLayout());
    JPanel animationPanel = new AnimationPanel();
    animationPanel.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
    JScrollPane scrollPane = new JScrollPane(animationPanel);
    this.add(scrollPane, BorderLayout.CENTER);

    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new FlowLayout());
    this.add(buttonPanel, BorderLayout.SOUTH);

    startButton = new JButton("Start");
    pauseButton = new JButton("Pause");
    loopButton = new JButton("Toggle Looping");
    export = new JTextField("Enter SVG file name", 11);
    speedChanger = new JTextField("Enter new speed", 9);

    startButton.setActionCommand("START");
    pauseButton.setActionCommand("TOGGLE PAUSE");
    loopButton.setActionCommand("TOGGLE LOOP");
    export.setActionCommand("EXPORT");
    speedChanger.setActionCommand("SPEED CHANGE");

    buttonPanel.add(startButton);
    buttonPanel.add(pauseButton);
    buttonPanel.add(loopButton);
    buttonPanel.add(speedChanger);
    buttonPanel.add(export);
    buttonPanel.add(new JTextArea(".svg"));

    JPanel dropDownPanel = new JPanel();
    dropDownPanel.setLayout(new FlowLayout());
    this.add(dropDownPanel, BorderLayout.EAST);

    IShape[] shapeArray;

    if (model.getShapes().size() == 1) {
      shapeArray = new IShape[this.model.getNumShapes()];
    }
    else {
      shapeArray = new IShape[this.model.getNumShapes() + model.getShapes().size()];
    }

    int n = 0;
    for (int i = 0; i < this.model.getShapes().size(); i++) {
      if (this.model.getShapes().size() > 1) {
        shapeArray[n] = new Layer(Integer.toString(i + 1), new Posn(0, 0),
                new Posn(0, 0), new MyColor(0, 0, 0), new Posn(0, model.getEndTime()));
        n++;
      }
      for (int j = 0; j < this.model.getShapes().get(i).size(); j++) {
        shapeArray[n] = this.model.getShapes().get(i).get(j);
        n++;
      }
    }

    shapeRemover = new JComboBox<>(shapeArray);
    shapeRemover.setEditable(true);
    shapeRemover.setActionCommand("SHAPE REMOVED");
    dropDownPanel.add(shapeRemover);

    JTextField back = new JTextField("Background color (red green blue):", 18);
    red = new JTextField(Integer.toString(redInt), 4);
    green = new JTextField(Integer.toString(greenInt), 4);
    blue = new JTextField(Integer.toString(blueInt), 4);

    red.setActionCommand("RED");
    green.setActionCommand("GREEN");
    blue.setActionCommand("BLUE");

    buttonPanel.add(back);
    buttonPanel.add(red);
    buttonPanel.add(green);
    buttonPanel.add(blue);

    JPanel statsPanel = new JPanel();
    this.add(statsPanel, BorderLayout.NORTH);
    stats = new JTextArea(this.writeStats());
    stats.setEditable(false);
    statsPanel.add(stats);

    this.pack();
    this.setVisible(true);
  }

  /**
   * Returns a description of certain characteristics of the animation that the user may have
   * altered.
   *
   * @return  The description
   */
  private String writeStats() {
    String s = "Animation is";
    if (paused) {
      s += " not currently running.";
    }
    else {
      s += " currently running at a speed of " + this.speed + ".";
    }
    s += " Looping is";
    if (looping) {
      s += " on.";
    }
    else {
      s += " off.";
    }
    return s;
  }

  /**
   * Returns each shape in the model to their initial location, position, and color values.
   */
  private void initShapes() {
    for (int i = 0; i < model.getShapes().size(); i++) {
      for (IShape s : model.getShapes().get(i)) {
        IShape sh = s;
        sh.setDefault();
      }
    }
  }

  @Override
  public void display() {
    ArrayList<ArrayList<IShape>> shapes = model.getShapes();
    String s = "";

    s += "<svg width=\"" + FRAME_WIDTH + "\" height=\"" + FRAME_HEIGHT
            + "\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\">\n\n";

    s += "<rect id=\"background\" x=\"0\" y=\"0\" width=\"" + FRAME_WIDTH
            + "\" height=\"" + FRAME_HEIGHT + "\" fill=\"rgb" + background.getColorAsInt()
            + "\" visibility=\"visible\" >\n";
    s += "</rect>\n\n";

    for (ArrayList<IShape> al : shapes) {
      for (IShape sh : al) {
        if (sh.getType() == ShapeType.RECTANGLE) {
          s += "<rect id=\"" + sh.getName() + "\" x=\"" + sh.getX() + "\" y=\"" + sh.getY()
                  + "\" width=\"" + sh.getWidth() + "\" height=\"" + sh.getHeight()
                  + "\" fill=\"rgb" + sh.getColorAsInt() + "\" visibility=\"";
          if (sh.isVisible()) {
            s += "visible\" >\n";
          } else {
            s += "hidden\" >\n";
          }
          s += this.actionsAsString(sh);
          s += "</rect>\n\n";
        } else {
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
    }
    s += "</svg>";

    try {
      fw.append(s);
      fw.close();
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
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

  @Override
  public void setActionListener(ActionListener listener) {
    this.listener = listener;
  }

  @Override
  public void addActionListeners() {
    this.startButton.addActionListener(listener);
    this.pauseButton.addActionListener(listener);
    this.loopButton.addActionListener(listener);
    this.export.addActionListener(listener);
    this.speedChanger.addActionListener(listener);
    this.shapeRemover.addActionListener(listener);
    this.red.addActionListener(listener);
    this.green.addActionListener(listener);
    this.blue.addActionListener(listener);
  }

  @Override
  public void run() {
    if (timer.isRunning()) {
      timer.stop();
    }
    else {
      startButton.setText("Restart");
    }
    this.initShapes();
    tick = 0;
    this.paused = false;
    timer.start();
  }

  @Override
  public ViewType getViewType() {
    return this.type;
  }

  @Override
  public void togglePause() {
    paused = !paused;
    if (paused) {
      pauseButton.setText("Play");
    }
    else {
      pauseButton.setText("Pause");
    }
  }

  @Override
  public void toggleLoop() {
    looping = !looping;
  }

  @Override
  public void setSpeed(int newSpeed) {
    speed = newSpeed;
    int delay = 1000 / speed;
    timer.setDelay(delay);
  }

  @Override
  public void export(String ofile) {
    try {
      fw = new FileWriter(ofile);
    } catch (IOException e) {
      System.out.println("ERROR: " + e.getMessage());
    }
    this.display();
  }

  @Override
  public void toggleShape(IShape s) {
    s.setVisible();
  }

  @Override
  public void changeRed(int newRed) {
    this.redInt = newRed;
  }

  @Override
  public void changeGreen(int newGreen) {
    this.greenInt = newGreen;
  }

  @Override
  public void changeBlue(int newBlue) {
    this.blueInt = newBlue;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    stats.replaceRange(this.writeStats(), 0, stats.getText().length());
    if (!paused) {
      for (int i = 0; i < model.getActions().size(); i++) {
        model.executeAction(i, tick);
      }
      this.repaint();
      tick++;
      if (tick >= model.getEndTime() && looping) {
        this.run();
      }
      else if (tick >= model.getEndTime()) {
        timer.stop();
      }
    }
  }

  /**
   * A JPanel with all of the shapes in the animation drawn on it.
   */
  private class AnimationPanel extends JPanel {

    @Override
    public void paintComponent(Graphics g) {
      super.paintComponent(g);

      background.setColor(new MyColor((float)redInt/255, (float)greenInt/255, (float)blueInt/255));

      g.setColor(background.getColor());
      g.fillRect(background.getX(), background.getY(), background.getWidth(), background.getHeight());

      for (int i = model.getShapes().size() - 1; i >= 0; i--) {
        for (IShape s : model.getShapes().get(i)) {
          if (s.isVisible()) {
            if (s.getType() == ShapeType.RECTANGLE) {
              g.setColor(s.getColor());
              g.fillRect(s.getX(), s.getY(), s.getWidth(), s.getHeight());
            } else {
              g.setColor(s.getColor());
              g.fillOval(s.getX(), s.getY(), s.getWidth(), s.getHeight());
            }
          }
        }
      }
    }
  }
}

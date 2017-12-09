DESIGN CHANGES FROM ASSIGNMENT 6:
 - Refactored the view interface to have a more comprehensive set of methods and implemented these
   methods as necessary.
 - Added a ViewType enum that represents each type of view.
    - Added a ViewType field to each view.
 - Re-wrote the visual view so it uses a Timer to run the animation.
 - Created a read-only model that is used by the views.
 - Added fields to the Shape class so shapes can keep track of their initial state and reset to it.

 DESIGN FOR ASSIGNMENT 7:
 - We have one controller for the interactive view and one controller for every other view with
   interfaces for each.
   - The interface for the interactive controller extends the interface for a generic controller.
 - In the view, running the run method starts a Timer whose delay is based on the current speed.
   After each delay period, actionPerformed is called and it handles one tick of the animation.
   - This allows for certain aspects of the animation (speed, looping, etc) to be changed between
     ticks as the Timer ticks down in the background.
 - The start, pause, and looping functions are all handled as buttons.
   - Once the start button is pushed for the first time, it turns into a restart button.
   - When the animation is playing, the pause button reads "pause".
     When it is paused, it reads "play".
   - The looping button simply reads "Toggle Looping", and clicking it
     changes whether looping is turned on or off (looping is a boolean in our view class).
 - The speed changing and export functions are handled as text fields.
   - When a new speed is typed in and entered in the speed change field, the animation speed is
     changed to the given speed.
   - When a file name is typed in and entered in the export field (not including ".svg"), the
     animation is exported to the given file in SVG format.
 - The shape visibility changing function is handled as a combo box.
   - When you drop down the combo box, you will see a list of all
     shapes in the animation in a "ShapeType ShapeName" format.
   - If a shape is currently visible, a checkmark (✓) will appear next
     to its name. If it is currently invisible, there will be no checkmark.
   - Click on a shape to toggle its visibility.
 - At all times, there is a text area at the top of the UI that details:
   - Whether the animation is currently running or not.
   - If it is running, what the current speed is.
   - Whether looping is currently on or off.
 - We designed three separate listener classes: one for buttons, one for text fields,
   and one for combo boxes. Each swing element's listener is the one that matches its type.
   - Each listener is created by the controller and takes in the controller as a field.
   - After creating the listeners, the controller calls methods in
     the interactive view that adds the listeners to their respective elements.
   - When an action is performed, the listener calls the corresponding method in
     its controller, which then calls the corresponding method in its view.
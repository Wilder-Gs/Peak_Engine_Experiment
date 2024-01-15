package santaJam.inputs;

import com.studiohartman.jamepad.ControllerButton;

public enum Controllerbind { 
  UP        ("up",        0, ControllerButton.DPAD_UP),
  DOWN      ("down",      1, ControllerButton.DPAD_DOWN),
  LEFT      ("left",      2, ControllerButton.DPAD_LEFT),
  RIGHT     ("right",     3, ControllerButton.DPAD_RIGHT),
  JUMP      ("jump",      4, ControllerButton.B),
  GRAPPLE   ("grapple",   5, ControllerButton.A),
  SAVE_STATE("save state",6, ControllerButton.GUIDE),
  RESET     ("reset",     7, ControllerButton.LEFTSTICK),
  FULL_RESET("hard reset",8, ControllerButton.RIGHTSTICK),
  ENTER     ("enter",     9, ControllerButton.A),
  PAUSE     ("pause",     10, ControllerButton.START),
  ;

  public String name;
  public int index;
  public ControllerButton bind;

  Controllerbind(String n, int i, ControllerButton b) {
    this.name = n;
    this.index = i;
    this.bind = b;
  }
}

package santaJam.inputs;

import java.awt.event.KeyEvent;

public enum Keybind {
  UP        ("up",        0, false,true, 38),
  DOWN      ("down",      1, false,true, 40),
  LEFT      ("left",      2, false,true, 37),
  RIGHT     ("right",     3, false,true, 39),
  JUMP      ("jump",      4, false,true, 32),
  GRAPPLE   ("grapple",   5, false,true, 88),
  SAVE_STATE("save state",6, true, true, 59),
  RESET     ("reset",     7, false,true, 82),
  FULL_RESET("hard reset",8, true, true, 127),
  ENTER     ("enter",     9, false,false,10),
  PAUSE     ("pause",     10,false,false, KeyEvent.VK_ESCAPE),
  DEBUG     ("debug",     11,true, false, KeyEvent.getExtendedKeyCodeForChar('`')),
  FRAME_ADV ("",          12,true, false, KeyEvent.getExtendedKeyCodeForChar('=')),
  FA_PLAY   ("",          13,true, false, KeyEvent.getExtendedKeyCodeForChar('-')),
  W         ("w",         14, false,true, 87),
  A         ("a",         15, false,true, 65),
  S         ("s",         16, false,true, 83),
  D         ("d",         17, false,true, 68),
  Z         ("z",         18, false,false, 90),
  ;

  public String name;
  public int index;
  public boolean speedrun;
  public boolean bindable;
  public int default_bind;

  Keybind(String n, int i, boolean s, boolean b, int db) {
    this.name = n;
    this.index = i;
    this.speedrun = s;
    this.bindable = b;
    this.default_bind = db;
  }
}

package santaJam.inputs;

import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.List;

import com.studiohartman.jamepad.ControllerButton;

public enum Keybind {
  UP        ("up",        0, false,true, Arrays.asList(38, 87)),
  DOWN      ("down",      1, false,true, Arrays.asList(40, 83)),
  LEFT      ("left",      2, false,true, Arrays.asList(37, 65)),
  RIGHT     ("right",     3, false,true, Arrays.asList(39, 68)),
  JUMP      ("jump",      4, false,true, Arrays.asList(67, 32)),
  GRAPPLE   ("grapple",   5, false,true, Arrays.asList(88, 81)),
  SAVE_STATE("save state",6, true, true, Arrays.asList(59, -1)),
  RESET     ("reset",     7, false,true, Arrays.asList(82, -1)),
  FULL_RESET("hard reset",8, true, true, Arrays.asList(127, -1)),
  ENTER     ("enter",     9, false,false, Arrays.asList(10, -1)),
  PAUSE     ("pause",     10,false,false, Arrays.asList(KeyEvent.VK_ESCAPE, -1)),
  DEBUG     ("debug",     11,true, false, Arrays.asList(KeyEvent.getExtendedKeyCodeForChar('`'), -1)),
  FRAME_ADV ("",          12,true, false, Arrays.asList(KeyEvent.getExtendedKeyCodeForChar('='), -1)),
  FA_PLAY   ("",          13,true, false, Arrays.asList(KeyEvent.getExtendedKeyCodeForChar('-'), -1)),
  Z         ("z",         14, false,false, Arrays.asList(90, -1))
  ;

  public String name;
  public int index;
  public boolean speedrun;
  public boolean bindable;
  public List<Integer> binds;
  public int default_bind;
  public int alternate_bind;

  Keybind(String n, int i, boolean s, boolean b, List<Integer> bs) {
    this.name = n;
    this.index = i;
    this.speedrun = s;
    this.bindable = b;
    this.binds = bs;
    if(bs.size() != 2){
      System.out.println("Keybindings not set up correctly: size is " + bs.size() + " instead of 2!!!");
      System.exit(0);
    }
    this.default_bind = bs.get(0);
    this.alternate_bind = bs.get(1);
  }
}

package santaJam.inputs;

import com.studiohartman.jamepad.ControllerButton;

public class GamepadButton {
	boolean held=false, pressed=false, released=false, inputUsed=false;
	int holdLength=0;
	ControllerButton button;
	
	public GamepadButton(ControllerButton button) {
		this.button = button;
	}
	
	public void update(boolean isHeld){
		if(isHeld) {
			if(!held) {
				pressed = true;
				inputUsed=false;
			} else {
				pressed = false;
			}
			released = false;
			holdLength++;
		} else {
			if(held) {
				released = true;
			} else {
				released = false;
			}
			pressed = false;
			holdLength=0;
		}
		held = isHeld;
	}
	
	public boolean isPressed() {
		return pressed;
	}
	public boolean isHeld() {
		return held;
	}
	public boolean isReleased() {
		return released;
	}
	public int getHoldLength() {
		return holdLength;
	}
	public String getKey() {
		return button.name();
	}
	
	public void useInput() {
		inputUsed=true;
	}
	public boolean isInputUsed() {
		return inputUsed;
	}
	
	

}

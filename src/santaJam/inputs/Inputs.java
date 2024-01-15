package santaJam.inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.studiohartman.jamepad.ControllerIndex;
import com.studiohartman.jamepad.ControllerManager;
import com.studiohartman.jamepad.ControllerState;
import com.studiohartman.jamepad.ControllerUnpluggedException;

import santaJam.SantaJam;

public class Inputs implements KeyListener {

	private static ArrayList<List<Integer>> keyCodes = new ArrayList<List<Integer>>(Keybind.values().length);
	private static boolean[] keyStates = new boolean[Keybind.values().length];
	private static InputButton[] inputButtons = new InputButton[Keybind.values().length];

	//private static ControllerButton[] buttonVals = new ControllerButton[Controllerbind.values().length];
	// ^^Eventually for button mapping^^
	private static GamepadButton[] controllerButtons = new GamepadButton[Controllerbind.values().length];

	private static boolean keyPressed = false;
	private static int lastKeyCode = -1;
	private static InputButton anyKey = new InputButton(0);

	public static ControllerManager controllers;
	public static ControllerState con;
	public static ControllerIndex conIDX;

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		keyPressed = true;
		lastKeyCode = e.getKeyCode();
		for (Keybind keybind : Keybind.values()) {
			if (keyCodes.get(keybind.index).contains(e.getKeyCode())) {
				keyStates[keybind.index] = true;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keyPressed = false;
		for (Keybind keybind : Keybind.values()) {
			if (keyCodes.get(keybind.index).contains(e.getKeyCode())) {
				keyStates[keybind.index] = false;
			}
		}
	}

	public static void init() {
		controllers = new ControllerManager();
		controllers.initSDLGamepad();
		for (Controllerbind bind : Controllerbind.values()) {
			controllerButtons[bind.index] = new GamepadButton(bind.bind);
		}
	}

	public static void update() throws ControllerUnpluggedException {
		controllers.update();
		con = controllers.getState(0);
		conIDX = controllers.getControllerIndex(0);
		anyKey.update(keyPressed);
		for (Keybind keybind : Keybind.values()) {
			inputButtons[keybind.index].update(keyStates[keybind.index]);
		}
		for (GamepadButton button : controllerButtons)
			button.update(conIDX.isButtonPressed(button.button));
	}

	public static void update(Keybind keybind) {
		inputButtons[keybind.index].update(keyStates[keybind.index]);
	}

	public static void update(Controllerbind keybind) throws ControllerUnpluggedException {
		controllers.update();
		con = controllers.getState(0);
		conIDX = controllers.getControllerIndex(0);
		controllerButtons[keybind.index].update(conIDX.isButtonPressed(keybind.bind));
		;
	}

	public static void setKeyBinds(int[] newKeyCodes) {
		for (Keybind keybind : Keybind.values()) {
			try {
				keyCodes.get(keybind.index).set(0, newKeyCodes[keybind.index]);
				inputButtons[keybind.index] = new InputButton(newKeyCodes[keybind.index]);
			} catch (Exception e) {
				keyCodes.add(Arrays.asList(keybind.default_bind, keybind.alternate_bind));
				inputButtons[keybind.index] = new InputButton(keybind.default_bind);
			}
		}

		if (SantaJam.getGame() != null) {
			SantaJam.getGame().getSettings().setKeyBinds(newKeyCodes);
		}
	}

	public static InputButton AnyKey() {
		return anyKey;
	}

	public static int getLastKeyCode() {
		return lastKeyCode;
	}

	public static InputButton getKey(Keybind keybind) {
		return inputButtons[keybind.index];
	}

	public static GamepadButton getBut(Controllerbind button) {
		return controllerButtons[button.index];
	}

	public static boolean getKeyState(Keybind keybind) {
		return keyStates[keybind.index];
	}

	public static void simulateKeyPress(Keybind keybind) {
		keyPressed = true;
		keyStates[keybind.index] = true;
	}

	public static void simulateKeyRelease(Keybind keybind) {
		keyPressed = false;
		keyStates[keybind.index] = false;
	}
}

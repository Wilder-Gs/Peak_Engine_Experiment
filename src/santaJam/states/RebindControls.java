package santaJam.states;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import santaJam.Assets;
import santaJam.Game;
import santaJam.SantaJam;
import santaJam.components.Timer;
import santaJam.graphics.UI.TextElement;
import santaJam.inputs.Controllerbind;
import santaJam.inputs.Inputs;
import santaJam.inputs.Keybind;
import santaJam.states.menus.Menu;
import santaJam.states.menus.MenuObject;
import santaJam.states.menus.MenuSelection;

public class RebindControls implements State {
	State returnState;
	TextElement action = new TextElement(false, 40, 50, TextElement.BIGMONOWIDTH, TextElement.SMALLMONOHEIGHT + 1,
			Game.WIDTH - 80,
			"", Assets.font),
			rebind = new TextElement(false, 40, 70, TextElement.BIGMONOWIDTH, TextElement.SMALLMONOHEIGHT + 1,
					Game.WIDTH - 80,
					"", Assets.font);

	Menu menu;
	GameState mainState;
	State stateToSwitch = null;

	private int[] keyCodes = new int[Keybind.values().length];
	private int currentAction = 0;

	public RebindControls(State returnState) {
		this.returnState = returnState;
	}

	@Override
	public void start(State prevState) {
		Color textColour = new Color(200,254,255),hoverColour = new Color(5,28,40) ;
		menu = new Menu(new Rectangle(), new MenuObject[] {
				new MenuSelection(new Rectangle(Game.WIDTH/2-40,50,50,20), "QUIT TO TITLE", textColour,hoverColour) {
					@Override
					public void select() {
						mainState.saveTime();
						if(!Timer.TASPlayback) { StateManager.getGameState().saveTas(); }
						stateToSwitch =  new TitleScreen();
					}
					@Override
					public void render(Graphics g) {
						super.render(g);
						bounds.x=Game.WIDTH/2-(name.length()*TextElement.BIGMONOWIDTH/2);
					}
				}
			});
			menu.select();
	}

	@Override
	public void update() {
		Keybind current = Keybind.values()[currentAction];
		int index = current.index;

		if (current.bindable) {
			action.update(
					"  PRESS BUTTON FOR " + current.name.toUpperCase() + " \n ESCAPE TO CANCEL, ENTER FOR DEFAULT");
			action.centre(Game.WIDTH - 80);

			if (current.speedrun && !SantaJam.getGame().getSettings().getSpeedrunEnabled()) {
				currentAction++;
			} else {
				if (currentAction > 0) {
					rebind.update("\n \n BINDED KEY " + KeyEvent.getKeyText(keyCodes[index - 1]).toUpperCase() + " TO "
							+ Keybind.values()[currentAction - 1].name.toUpperCase());
					rebind.centre(Game.WIDTH - 80);
				}

				if (Inputs.getKey(Keybind.PAUSE).isPressed() || Inputs.getBut(Controllerbind.PAUSE).isPressed()) {
					StateManager.setCurrentState(returnState);
				}

				if (Inputs.getKey(Keybind.ENTER).isPressed() || Inputs.getBut(Controllerbind.ENTER).isPressed()) {
					keyCodes[index] = current.default_bind;
					currentAction++;
				} else if (Inputs.AnyKey().isPressed()) {
					keyCodes[index] = Inputs.getLastKeyCode();
					currentAction++;
				}

			}
		} else {
			currentAction++;
			keyCodes[index] = current.default_bind;
		}

		if (currentAction == keyCodes.length) {
			Inputs.setKeyBinds(keyCodes);
			StateManager.setCurrentState(returnState);
		}
		// if(Inputs.AnyKey().isPressed()) {
		// System.out.println(Inputs.getLastKeyCode());
		// }
	}

	@Override
	public void render(Graphics2D g) {
		g.drawImage(Assets.creditScreen, 0, 0, null);
		g.setColor(Color.white);
		g.setFont(Assets.bigFont);
		g.drawString("Set Controls", 118, 24);

		action.render(g);
		rebind.render(g);

	}

	@Override
	public void end() {}

}

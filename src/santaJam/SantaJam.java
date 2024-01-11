package santaJam;

import com.studiohartman.jamepad.ControllerUnpluggedException;

public class SantaJam {
	private static Game game;
	public static void main(String[] args) throws ControllerUnpluggedException {
		game = new Game();
		game.run();
	}
	public static Game getGame() {
		return game;
	}
	
}

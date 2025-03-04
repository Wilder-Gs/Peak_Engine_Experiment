package santaJam.entities.player;

import santaJam.inputs.Controllerbind;
import santaJam.inputs.Inputs;
import santaJam.inputs.Keybind;
import santaJam.states.StateManager;

public class Falling extends PlayerState{

	private boolean firstFrame=true;
	
	@Override
	public void start(PlayerState prevState) {}

	@Override
	public PlayerState update(Player player) {
		super.update(player);
		if(Math.abs(player.getVelX())>TOPWALKSPEED) {
			return new SlideFalling();
			
		}
		
		if(firstFrame) {
			player.changeBounds(width, height);
			firstFrame=false;
		}
		//letting the player move, and be gravitied;
		normalMoveLeftRight(player);
		normalGravity(player);
		
		
		//going back to standing if the hit the ground
		if(player.isGrounded()) {
			return new Standing();
		}
		player.setAnim(Player.falling);
		//attacking if the buffer an attack
		/*if(Inputs.attack().getHoldLength()<5&&Inputs.attack().getHoldLength()>0) {
			return new Sliding();
		}*/
		//grappling if the buffer an grapple
		if(Inputs.getKey(Keybind.GRAPPLE).getHoldLength()<BUFFERLENGTH&&Inputs.getKey(Keybind.GRAPPLE).getHoldLength()>0&&!Inputs.getKey(Keybind.GRAPPLE).isInputUsed()) {
			Inputs.getKey(Keybind.GRAPPLE).useInput();
			return new Grapple(this, player);
		}
		
		//trying to double jump if they push jump
		if(Inputs.getKey(Keybind.JUMP).getHoldLength()<BUFFERLENGTH&&Inputs.getKey(Keybind.JUMP).getHoldLength()>0&&!Inputs.getKey(Keybind.JUMP).isInputUsed()
				&&DoubleJump.canDoubleJump()&&StateManager.getGameState().getSave().hasDoubleJump()) {
			Inputs.getKey(Keybind.JUMP).useInput();
			
			return new 	DoubleJump(this);
			
		}
		//--------CONTROLLER TIME------------
		//grappling if the buffer an grapple
		if(Inputs.getBut(Controllerbind.GRAPPLE).getHoldLength()<BUFFERLENGTH&&Inputs.getBut(Controllerbind.GRAPPLE).getHoldLength()>0&&!Inputs.getBut(Controllerbind.GRAPPLE).isInputUsed()) {
			Inputs.getBut(Controllerbind.GRAPPLE).useInput();
			return new Grapple(this, player);
		}
		
		//trying to double jump if they push jump
		if(Inputs.getBut(Controllerbind.JUMP).getHoldLength()<BUFFERLENGTH&&Inputs.getBut(Controllerbind.JUMP).getHoldLength()>0&&!Inputs.getBut(Controllerbind.JUMP).isInputUsed()
				&&DoubleJump.canDoubleJump()&&StateManager.getGameState().getSave().hasDoubleJump()) {
			Inputs.getBut(Controllerbind.JUMP).useInput();
			
			return new 	DoubleJump(this);
			
		}
		return null;
	}

	@Override
	public void end() {}

}

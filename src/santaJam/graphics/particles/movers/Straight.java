package santaJam.graphics.particles.movers;

import java.util.concurrent.ThreadLocalRandom;


public class Straight extends ParticleMover {
	private double moveX, moveY, speed;
	private int angle,angleRange;
	
	/**
	 * creates particles that move in the specified range of angles at the specified speed
	 * @param spawnPattern - determines where the particles will spawn
	 * @param angle - angle in degrees that they move
	 * @param angleRange - amount of degrees that can be randomly added or subtracted from a particle's direction
	 * (always at least 1 degree)
	 * @param speed - speed in pixels/frame that particles move
	 */
	public Straight(int x, int y,int angle, int angleRange, double speed) {
		super(x,y);
		
		if(angleRange<=0) {
			angleRange=1;
		}
		//the math class uses radians but they dont make sense so i convert degrees to radians
		double radians=ThreadLocalRandom.current().nextInt(angle-angleRange, angle+angleRange)*Math.PI/180;
		moveX = speed*Math.cos(radians); //calculates the direction and speed needed to move along the x axis
		moveY = speed*Math.sin(radians); 
		this.speed=speed;
		this.angleRange=angleRange;
		this.angle=angle;
		
	}
	/**
	 * creates particles going in a random direction in a straight line
	 * @param spawnPattern - determines where particles spawn
	 * @param speed - how fast particles travel in pixels/frame
	 */
	public Straight(int x, int y, double speed) {
		this(x,y,0,360,speed);
	}

	@Override
	public void update() {
		trueX+=moveX;
		trueY+=moveY;
		x=(int)Math.round(trueX);
		y=(int)Math.round(trueY);
		
	}

	@Override
	public ParticleMover copy(int x, int y) {
		return new Straight(x,y,angle,angleRange,speed);
	}
}

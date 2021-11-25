package santaJam.graphics.particles.shapes;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.concurrent.ThreadLocalRandom;

import santaJam.graphics.particles.shapes.colourers.ParticleColourer;
import santaJam.graphics.particles.shapes.colourers.Timed;
import santaJam.states.Camera;

/**using this particle shape will unsuprisingly make the particles ovals
 * @author The Computer Man */
public class ImgShape extends ParticleShape{

	private BufferedImage[] allImgs;
	int img;
	
	
	public ImgShape(BufferedImage[] images, ParticleColourer colourer) {
		super(colourer);
		allImgs=images;
		img=ThreadLocalRandom.current().nextInt(0, allImgs.length);
		
	}
	public ImgShape(BufferedImage[] images, int time, int range) {
		this(images, new Timed(null,time,range));
		
	}
	

	@Override
	public void render(Graphics g, Camera camera, int x, int y) {
		g.drawImage(allImgs[img], x-camera.getxOffset(), y-camera.getyOffset(),null);
	}

	@Override
	/** this returns a copy of this particle shape*/
	public ParticleShape copy() {
		return new ImgShape(allImgs,colourer.copy());
	}

}

package santaJam.states.menus;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

import santaJam.Assets;


public class MenuSelection extends MenuObject{
	protected String name;
	private boolean isHovered=false;
	private Color colour, hoverColour;
	private Font font = Assets.font;
	
	public MenuSelection(Rectangle bounds, String name) {
		this(bounds,name,Color.white,Color.black);
	}
	public MenuSelection(Rectangle bounds, String name, Color colour,Color hoverColour) {
		super(bounds);
		this.name = name;
		this.colour=colour;
		this.hoverColour=hoverColour;
	}

	@Override
	public void render(Graphics g) {
		g.setFont(font);//setting the font
		if(selected||isHovered) {
			g.setColor(hoverColour);//setting the colour for the menu text
			g.drawString(name.toUpperCase(), bounds.x+1, bounds.y+bounds.height);
			g.drawString(name.toUpperCase(), bounds.x, bounds.y+bounds.height);
		}
		
		g.setColor(colour);//setting the colour for the menu text
		g.drawString(name.toUpperCase(), bounds.x, bounds.y+bounds.height-1);
		isHovered=false;
	}
	@Override
	public void select() {
		super.select();
		//MusicManager.menuSelect.play();

	}
	@Override
	public void hover() {
		super.hover();
		isHovered=true;
	}
	public String getName() {
		return name;
	}


	
}

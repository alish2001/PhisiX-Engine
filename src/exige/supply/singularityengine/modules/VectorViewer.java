package exige.supply.singularityengine.modules;

import exige.supply.singularityengine.graphics.Screen;
import exige.supply.singularityengine.graphics.sprites.Sprite;
import exige.supply.singularityengine.physics.Vector;

/**
 * VectorViewer allows for vertical and horizontal components of a vector to be shown.
 *
 * @author Ali Shariatmadari
 */

public class VectorViewer {

    private Screen screen;
    public static Vector vector = new Vector();

    public VectorViewer(Screen screen) {
        this.screen = screen;
    }

    // Render vector components
    public void render() {
    	int xComp = (int) Math.abs(vector.getXComponent());
    	int yComp = (int) Math.abs(vector.getYComponent());
    	screen.renderSprite(30, 80, new Sprite(xComp, 2, 0x00f000), true); // xComp
    	screen.renderSprite(30,60, new Sprite(2, yComp, 0x000000), true); // yComp
    	//screen.renderSprite(300, 100, new Sprite((int) vector.getX(), 2, 0x000000), true);
    	System.out.println("rendering x=" + xComp);
    	System.out.println("rendering y=" + yComp);
    }

}
package exige.supply.singularityengine.modules;

import exige.supply.singularityengine.graphics.Screen;
import exige.supply.singularityengine.graphics.sprites.Sprite;
import exige.supply.singularityengine.physics.Vector;
import supply.exige.phisix.PhisiX;

/**
 * VectorViewer allows for vertical and horizontal components of a vector to be shown.
 *
 * @author Ali Shariatmadari
 */

public class VectorViewer {

    private Screen screen;
    public static Vector vector;

    public VectorViewer(Screen screen) {
        this.screen = screen;
    }

    // Render vector components
    public void render() {
    	int xComp = (int) Math.abs(vector.getX());
    	int yComp = (int) Math.abs(vector.getY(PhisiX.ball.time));
    	screen.renderSprite(300, 80, new Sprite(xComp * 10, 20, 0x00f000), true); // xComp
    	screen.renderSprite(300,60, new Sprite(20, yComp * 10, 0x000000), true); // yComp
        System.out.println("rendering x=" + PhisiX.ball.getX());
        System.out.println("rendering y=" + PhisiX.ball.getY());
    	//screen.renderSprite(300, 100, new Sprite((int) vector.getX(), 2, 0x000000), true);
    }

}
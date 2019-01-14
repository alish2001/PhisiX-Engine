package supply.exige.phisix;

import java.awt.Graphics;
import java.util.HashMap;
import java.util.Map;

import exige.supply.singularityengine.SingularityEngine;
import exige.supply.singularityengine.graphics.Screen;
import exige.supply.singularityengine.graphics.sprites.Sprite;
import exige.supply.singularityengine.modules.VectorViewer;
import exige.supply.singularityengine.physics.Vector;

public class PhisiX {

	public static SingularityEngine engine;
	public static P_Ball ball;
	public static int timeMultiplier = 1;

	public static void main(String[] args) {
		engine = new SingularityEngine("PHISIX");
		engine.setLevel(new L_PhisiX());
		Vector vector = new Vector(9, -70, false);
		ball = new P_Ball(engine.getLevel(), 100, 300, vector);
		engine.start();
		VectorViewer.vector = ball.getVector();
		engine.setRefreshRate(100 * timeMultiplier);
	}

	static HashMap<Integer, Integer> trail = new HashMap<>();

	public static void drawTrail(Screen screen) {
        /*trail.put(ball.getX(), ball.getY());
        for (Map.Entry<Integer, Integer> entry : trail.entrySet())
            screen.renderSprite(entry.getKey() - (ball.getSprite().getWidth() / 2), entry.getValue() - (ball.getSprite().getHeight() / 2), new Sprite(1, 1, 0x000000), false);
        // TODO RENDER CAMERA: https://gamedev.stackexchange.com/questions/138756/smooth-camera-movement-java*/
    	
    	for (double d = 0; d < ) {
    		screen.renderSprite(entry.getKey() - (ball.getSprite().getWidth() / 2), entry.getValue() - (ball.getSprite().getHeight() / 2), new Sprite(1, 1, 0x000000), false);	
    	}
    	
    }

	public static void drawStuff(Graphics g) {
		Vector vector = VectorViewer.vector;
		g.setFont(engine.getFont().deriveFont(18.0f));
		g.drawString("HORIZ=" + vector.getX() + " VERT=>" + vector.getY() + " =>" + vector.getY(ball.time), 100, 100);
		g.drawArc(30, 300, 300, 300, 60, 60);

	}
}
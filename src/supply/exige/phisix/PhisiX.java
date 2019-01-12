package supply.exige.phisix;

import java.awt.Graphics;

import exige.supply.singularityengine.SingularityEngine;
import exige.supply.singularityengine.modules.VectorViewer;
import exige.supply.singularityengine.physics.Vector;

public class PhisiX {

	public static SingularityEngine engine;
	public static P_Ball ball;

	public static void main(String[] args) {
		engine = new SingularityEngine("PHISIX");
		engine.setLevel(new L_PhisiX(100, 100));
		ball = new P_Ball(engine.getLevel(), 100, 300, new Vector(20, -60, false));
		engine.start();
		VectorViewer.vector = ball.getVector();
		// engine.setRefreshRate(120);
	}

	public static void drawStuff(Graphics g) {
		Vector vector = VectorViewer.vector;
		double time = ball.time;
		final double GRAVITY = 4.9;
		g.setFont(engine.getFont().deriveFont(18.0f));
		g.drawString("HORIZ=" + VectorViewer.vector.getXComponent() + " VERT=" + (vector.getYComponent() * time) + (GRAVITY * time * time), 100, 100);
		g.drawArc(30, 300, 300, 300, 60, 60);

	}
}
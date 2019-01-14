package exige.supply.singularityengine.modules;

import exige.supply.singularityengine.entities.Entity;
import exige.supply.singularityengine.graphics.Screen;
import exige.supply.singularityengine.levels.Level;
import supply.exige.phisix.PhisiX;

public class Camera {

	private int cameraX, cameraY;
	private Screen screen;
	private Level level;

	public Camera(Screen screen, Level level) {
		this.level = level;
		this.screen = screen;
	}

	public int getCameraX() {
		Entity entity = PhisiX.ball;
		int targetX = entity.getX() - screen.getWidth() / 2 + entity.getSprite().getWidth() / 2;
		cameraX += (targetX - cameraX) * 0.1;
		//					world.width - camera.width
		cameraX = Math.min((level.getWidth() * level.TILE_CONST) - screen.getWidth(), Math.max(0, cameraX));
		System.out.println("CameraX="+cameraX);
		return cameraX;
	}

	public int getCameraY() {
		Entity entity = PhisiX.ball;
		int targetY = entity.getY() - screen.getHeight() / 2 + entity.getSprite().getHeight() / 2;
		cameraY += (targetY - cameraY) * 0.1;
		cameraY = Math.min((level.getHeight() * level.TILE_CONST) - screen.getHeight(), Math.max(0, cameraY));
		System.out.println("CameraY="+cameraY);
		return cameraY;
	}
}
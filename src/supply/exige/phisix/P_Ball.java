package supply.exige.phisix;

import exige.supply.singularityengine.U;
import exige.supply.singularityengine.entities.projectiles.Projectile;
import exige.supply.singularityengine.graphics.sprites.Sprite;
import exige.supply.singularityengine.levels.Level;
import exige.supply.singularityengine.physics.Vector;

public class P_Ball extends Projectile {

	final double GRAVITY = 4.9;
	public double time = 0;
	
	public P_Ball(Level level, int x, int y, Vector vector) {
		super(level, x, y, vector);
		renderRange = 5000;
		damage = 0;
		sprite = new Sprite(20, 20, 0x000000);
		collidable = true;
	}
	
    @Override
    public void update() { // update method run every update cycle to update the projectile state
    	
        double newX = vector.getXComponent(); // Calculate the value to add to x to move it
        double newY = (vector.getYComponent()*time) + (GRAVITY*time*time); // Calculate the value to add to y to move it
        newY = U.roundFloatingPointError(newY, 1);
        move(newX, newY); // Attempt movement

       //if (getPixelDistanceTravelled() > renderRange) remove(); // If projectiles is past render range, remove
    }
}
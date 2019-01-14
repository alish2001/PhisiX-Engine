package supply.exige.phisix;

import exige.supply.singularityengine.entities.projectiles.Projectile;
import exige.supply.singularityengine.graphics.sprites.Sprite;
import exige.supply.singularityengine.levels.Level;
import exige.supply.singularityengine.physics.Collisions.Collision;
import exige.supply.singularityengine.physics.Vector;

public class P_Ball extends Projectile {

	final double GRAVITY = 4.9;
	public double time = 0;

	public P_Ball(Level level, int x, int y, Vector vector) {
		super(level, x, y, vector);
		damage = 0;
		sprite = new Sprite(20, 20, 0x000000);
		collidable = true;
	}
	
	public double getFlightTime() {
		// TODO: https://en.wikipedia.org/wiki/Projectile_motion
	}

    @Override
    public void update() { // update method run every update cycle to update the projectile state
        double newX = vector.getX(time); // Calculate the value to add to x to move it
        double newY = vector.getY(time); // Calculate the value to add to y to move it
        move(newX, newY); // Attempt movement

       //if (getPixelDistanceTravelled() > renderRange) remove(); // If projectiles is past render range, remove
    }

    /**
     * Moves the entity while taking into account collision.
     *
     * @param xMove Distance to move on the x-axis
     * @param yMove Distance to move on the y-axis
     */
    @Override
    public void move(double xMove, double yMove) {
        Collision potentialCollision = calculateCollision(xMove, yMove); // Calculate potential collision result

        if (!potentialCollision.doesCollide()) { // If not colliding
            // Move by changing coordinates
            x += (int) xMove;
            y += (int) yMove;
            time += 0.01;
        } else {
            //time = 0;
        }
    }

    @Override
	protected void onCollision(Collision c) {} // Override default collision method to prevent projectile removal
}
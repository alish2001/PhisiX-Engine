package exige.supply.singularityengine.physics;

import exige.supply.singularityengine.U;

/**
 * 2D Vector Class.
 * Used to calculate trajectories and movement velocity.
 *
 * @author Ali Shariatmadari
 */

public class Vector {

    private final int FP_PRECISION = 2; // Floating point precision constant
    private final double GRAVITY = 4.9;
    private double x, y, angle, speed = 0;

    public Vector(double speed, double angle, boolean radians) {
        if (!radians) { // If the angles are not in radians
            this.angle = U.roundFloatingPointError(Math.toRadians(angle), FP_PRECISION); // Convert to radians while account for floating point errors
        } else {
            this.angle = angle;
        }
        this.speed = speed;
        calculateTrajectoryAngle(); // Calculate missing values based on trajectory angle
    }

    // Calculates x and y components based on angle and speed
    private void calculateTrajectoryAngle() {
        x = U.roundFloatingPointError(speed * Math.cos(this.angle), FP_PRECISION); // Recalculate x
        y = U.roundFloatingPointError(speed * Math.sin(this.angle), FP_PRECISION); // Recalculate y
    }

    /**
     * @return Trajectory
     */
    public double get() {
        return speed;
    }

    /**
     * @return x component
     */
    public double getX() {
        return x;
    }

    /**
     * @return y component
     */
    public double getX(double time) {
        return U.roundFloatingPointError((x * time), FP_PRECISION);
    }

    /**
     * @return y component
     */
    public double getY() {
        return y;
    }

    /**
     * @return y component
     */
    public double getY(double time) {
        return U.roundFloatingPointError((y * time) + (GRAVITY * time * time), FP_PRECISION);
    }

    /**
     * @return @{@link Directions} based on angle
     */
    public Directions getDirection() {
        // Get direction after converting angle from radians to degrees and accounting for floating point errors
        return Directions.getDirection(U.roundFloatingPointError(Math.toDegrees(angle), FP_PRECISION));
    }
}
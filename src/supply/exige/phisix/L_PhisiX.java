package supply.exige.phisix;

import exige.supply.singularityengine.levels.Level;
import exige.supply.singularityengine.levels.SpawnPoint;
import exige.supply.singularityengine.levels.tiles.TileType;

/**
 * Random Level Class.
 * Generates a random level of random tiles.
 * Subclass of @{@link Level}
 *
 * @author Ali Shariatmadari
 */

public class L_PhisiX extends Level {
	
    public L_PhisiX() {
    	//
        super("PhisiX", "levels/PhisiX.png", 16); // Store level file path

        // Set one spawnpoint at (10, 10)
        SpawnPoint[] spawnPoints = new SpawnPoint[1];
        spawnPoints[0] = new SpawnPoint(10, 10);
        setSpawnPoints(spawnPoints);
       //generate(); // Generate level
    }

    // Generates blank phisix level
    private void generate() {
        // For all tiles
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                setTile(x, y, TileType.AIR.getTileClass()); // Set a random tile
            }
        }
    }
}
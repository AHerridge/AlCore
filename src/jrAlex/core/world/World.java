package jrAlex.core.world;

import jrAlex.core.world.world_objects.WorldObject;

/**
 * Created by Alex on 10/29/2016.
 */

public class World extends BoundContainer<WorldObject>
{
	public World(int x, int y, int width, int height)
	{
		super(x, y, width, height, null);
	}
}

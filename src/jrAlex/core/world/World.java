package jrAlex.core.world;

import jrAlex.core.world.world_objects.WorldObject;

/**
 * Created by Alex on 10/29/2016.
 */

public class World extends DoubleWorldObjectContainer<WorldObject>
{
	public World(int width, int height)
	{
		super(0, 0, width, height, null);
	}
}

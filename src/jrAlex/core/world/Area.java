package jrAlex.core.world;

import jrAlex.core.world.world_objects.WorldObject;

/**
 * Created by Alex on 10/28/2016.
 */

public class Area extends DoubleWorldObjectContainer<WorldObject>
{
	public Area(int x, int y, int width, int height, DoubleWorldObjectContainer container)
	{
		super(x, y, width, height, container);
	}
}

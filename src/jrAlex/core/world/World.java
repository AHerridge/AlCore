package jrAlex.core.world;

import jrAlex.core.world.world_objects.WorldObject;

import java.util.List;

/**
 * Created by Alex on 10/29/2016.
 */

public class World extends DoubleWorldObjectContainer<WorldObject>
{
	public World(int width, int height)
	{
		super(0, 0, width, height, null);
	}

	@Override
	public void update(long delta)
	{
		List<? extends Bound> objects = getObjects();
		for (int i = 0; i < objects.size(); i++)
		{
			objects.get(i).update(delta);
		}
	}
}

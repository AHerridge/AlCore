package jrAlex.core.world.world_objects;

/**
 * Created by Alex on 11/5/2016.
 */

public class WorldObjectFactory
{
	private enum WorldObjectTypes
	{
		GRASS();

		WorldObjectTypes(int width, int height)
		{

		}
	}

	public WorldObject create(WorldObjectTypes type)
	{
		return new WorldObject();
	}
}

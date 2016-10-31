package jrAlex.core.world.world_objects;

/**
 * Created by Alex on 10/31/2016.
 */

public class WorldObjectPart<E extends WorldObject> extends WorldObject
{
	private E parent;

	public WorldObjectPart(int x, int y, E parent)
	{
		super(x, y, 1, 1, parent.getContainer());
	}

	public E getParent()
	{
		return parent;
	}
}

package jrAlex.core.world.world_objects;

import jrAlex.core.world.Bound;
import jrAlex.core.world.BoundContainer;

/**
 * Created by Alex on 10/28/2016.
 */

public abstract class WorldObject extends Bound
{
	protected BoundContainer container;

	public WorldObject(int x, int y, int width, int height, BoundContainer container)
	{
		super(x, y, width, height);
		this.container = container;
	}

	public void translate(int xOffset, int yOffset)
	{
		if (container.isEmptyAt(new Bound(x + xOffset, y + yOffset, width, height)))
		{
			super.translate(xOffset, yOffset);
		}
	}

	public boolean canItGoThrough(WorldObject object)
	{

	}

	public void rotate()
	{
		if (container.isEmptyAt(new Bound(x, y, height, width)))
		{
			super.rotate();
		}
	}

	public BoundContainer getContainer()
	{
		return container;
	}
}

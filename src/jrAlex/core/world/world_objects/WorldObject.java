package jrAlex.core.world.world_objects;

import jrAlex.core.world.Bound;
import jrAlex.core.world.WorldObjectContainer;

/**
 * Created by Alex on 10/28/2016.
 */

public abstract class WorldObject extends Bound
{
	protected WorldObjectContainer container;
	protected boolean solid;

	public WorldObject(int x, int y, int width, int height, boolean solid,
	                   WorldObjectContainer container)
	{
		super(x, y, width, height);
		this.solid = solid;
		this.container = container;
	}

	@Override
	public void translate(double xOffset, double yOffset)
	{
		if (container.isEmptyAt(new Bound(x + xOffset, y + yOffset, width, height)))
		{
			container.removeObjectAt((int) x, (int) y);
			super.translate(xOffset, yOffset);
			container.addObject(this);
		}
	}

	public boolean canItGoThrough(WorldObject object)
	{
		return isSolid();
	}

	public boolean isSolid()
	{
		return solid;
	}

	public void rotate()
	{
		if (container.isEmptyAt(new Bound(x, y, height, width)))
		{
			super.rotate();
		}
	}

	public WorldObjectContainer getContainer()
	{
		return container;
	}
}

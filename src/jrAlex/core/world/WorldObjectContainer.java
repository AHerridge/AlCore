package jrAlex.core.world;

import jrAlex.core.world.world_objects.WorldObject;

import java.awt.*;
import java.util.List;

/**
 * Created by Alex on 10/31/2016.
 */

public abstract class WorldObjectContainer<E extends WorldObject> extends WorldObject
{
	public WorldObjectContainer(int x, int y, int width, int height, WorldObjectContainer container)
	{
		super(x, y, width, height, true, container);
	}

	public abstract void addObjectAt(int x, int y, E e);

	public abstract E getObjectAt(int x, int y);

	public abstract List<E> getObjectsAt(Bound b);

	public abstract E removeObjectAt(int x, int y);

	public abstract List<E> getObjects();

	public List<E> removeObjectsAt(Bound b)
	{
		List<E> removedObjects = getObjectsAt(b);

		for (E e : removedObjects)
		{
			removeObjectAt(e.x, e.y);
		}

		return removedObjects;
	}

	public E setObjectAt(int x, int y, E e)
	{
		E oldObject = removeObjectAt(x, y);
		addObjectAt(x, y, e);
		return oldObject;
	}

	public boolean isEmptyAt(int x, int y)
	{
		return getObjectAt(x, y) == null;
	}

	public boolean isEmptyAt(Bound b)
	{
		return getObjectsAt(b).isEmpty();
	}

	public boolean contains(Bound b)
	{
		return getBounds().contains(b);
	}

	public Bound getBounds()
	{
		return new Bound(0, 0, width, height);
	}

	@Override
	public void update()
	{
		super.update();
		for (E e : getObjects())
		{
			e.update();
		}
	}

	@Override
	public void redraw(Graphics g, int offX, int offY, int s)
	{
		super.redraw(g, offX, offY, s);
		for (E e : getObjects())
		{
			e.redraw(g, x + offX, y + offY, s);
		}
	}
}

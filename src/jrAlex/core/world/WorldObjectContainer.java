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
		super(x, y, width, height, true, container);// multiply w and h by window scale
	}

	public abstract void addObject(E e);

	public abstract E getObjectAt(int x, int y);

	public abstract List<E> getObjectsAt(Bound b);

	public abstract E removeObjectAt(int x, int y);

	public abstract List<E> getObjects();

	public abstract List<Point> getEmptyNeighborsOf(Bound b);

	public List<E> removeObjectsAt(Bound b)
	{
		List<E> removedObjects = getObjectsAt(b);

		for (E e : removedObjects)
		{
			removeObjectAt((int) e.x, (int) e.y);
		}

		return removedObjects;
	}

	public E setObjectAt(int x, int y, E e)
	{
		E oldObject = removeObjectAt(x, y);
		addObject(e);
		return oldObject;
	}

	public boolean isEmptyAt(int x, int y)
	{
		return getObjectAt(x, y) == null;
	}

	public List<E> getNeighborsOf(Bound b)
	{
		return getObjectsAt(new Bound(b.x - 1, b.y - 1, b.width + 1, b.height + 1));
	}

	public boolean isEmptyAt(Bound b)
	{
		List objects = getObjectsAt(b);
		return objects != null && objects.isEmpty();
	}

	public boolean contains(Bound b)
	{
		return getRelativeBounds().contains(b);
	}

	public Bound getRelativeBounds()
	{
		return new Bound(0, 0, width, height);
	}

	@Override
	public void update(long delta)
	{
		super.update(delta);
		List<? extends Bound> objects = getObjects();
		for (int i = 0; i < objects.size(); i++)
		{
			objects.get(i).update(delta);
		}
	}

	@Override
	public void redraw(Graphics g, int offX, int offY, int s)
	{
		super.redraw(g, offX, offY, s);
		for (E e : getObjects())
		{
			e.redraw(g, (int) x + offX, (int) y + offY, s);
		}
	}
}

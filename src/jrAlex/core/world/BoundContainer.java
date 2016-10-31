package jrAlex.core.world;

import jrAlex.core.world.world_objects.WorldObject;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Alex on 10/29/2016.
 */

public class BoundContainer<E extends WorldObject> extends WorldObject
{
	private LinkedList<E> objects;

	public BoundContainer(int x, int y, int width, int height, BoundContainer container)
	{
		super(x, y, width, height, container);
		this.objects = new LinkedList<>();
	}

	public boolean isEmptyAt(Bound bound)
	{
		return getObjectsAt(bound).isEmpty();
	}

	public boolean isEmptyAt(int x, int y)
	{
		return getObjectsAt(new Bound(x, y, 1, 1)).isEmpty();
	}

	public void addObject(E e)
	{
		if (isEmptyAt(e))
		{
			objects.add(e);
		}
	}

	public E removeObjectAt(int x, int y)
	{
		E oldE = getObjectAt(x, y);
		objects.remove(oldE);
		return oldE;
	}

	@Nullable
	@Contract(pure = true)
	private E getObjectAt(int x, int y)
	{
		Bound b = new Bound(x, y, 1, 1);
		return getObjectsAt(b).get(0);
	}

	public List<E> getObjectsAt(Bound bound)
	{
		List<E> foundObjects = new LinkedList<>();

		for (E e : objects)
		{
			if (e != null && e.intersects(bound))
			{
				foundObjects.add(e);
			}
		}

		return foundObjects;
	}

	public List<E> getNeighbors(Bound bound)
	{
		// List of neighbors to be returned
		List<E> neighbors = getObjectsAt(new Bound(bound.x - 1, bound.y - 1, bound.width + 1, bound.height + 1));

		// Remove itself from the list
		for (E neighbor : neighbors)
		{
			if (neighbor.equals(bound))
			{
				neighbors.remove(neighbor);
				break;
			}
		}

		return neighbors;
	}

	private E setObjectAt(int x, int y, E e)
	{
		E oldB = removeObjectAt(x, y);
		addObject(e);
		return oldB;
	}

	public List<E> getObjects()
	{
		return objects;
	}

	@Override
	public void update()
	{
		for (E e : objects)
		{
			e.update();
		}
	}

	@Override
	public void redraw(Graphics g, int s)
	{
		super.redraw(g, s);
		for (E e : objects)
		{
			e.redraw(g, s);
		}
	}
}

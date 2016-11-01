package jrAlex.core.world;

import jrAlex.core.world.world_objects.WorldObject;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Alex on 10/29/2016.
 */

public class DoubleWorldObjectContainer<E extends WorldObject> extends WorldObjectContainer<E>
{
	private List<E> array;
	private WorldObjectPart<E>[][] grid;

	public DoubleWorldObjectContainer(int x, int y, int width, int height, WorldObjectContainer container)
	{
		super(x, y, width, height, container);
		array = new LinkedList<>();
		grid = (WorldObjectPart<E>[][]) new WorldObjectPart[height][width];
	}

	@Override
	public void addObjectAt(int x, int y, E e)
	{
		e.setLocation(x, y);

		if (isEmptyAt(e))
		{
			array.add(e);

			for (int i = e.getIMinY(); i < e.getIMaxY(); i++)
			{
				for (int j = e.getIMinX(); j < e.getIMaxX(); j++)
				{
					grid[i][j] = new WorldObjectPart<>(j, i, e);
				}
			}
		}
	}

	@Override
	public E getObjectAt(int x, int y)
	{
		return grid[y][x].getParent();
	}

	@Override
	public List<E> getObjectsAt(Bound b)
	{
		LinkedList<E> foundObjects = new LinkedList<>();

		if (contains(b))
		{
			for (int i = b.getIMinY(); i < b.getIMaxY(); i++)
			{
				for (int j = b.getIMinX(); j < b.getIMaxX(); j++)
				{
					if (grid[i][j] != null)
					{
						foundObjects.add(grid[i][j].getParent());
					}
				}
			}
		}

		return foundObjects;
	}

	@Override
	public E removeObjectAt(int x, int y)
	{
		if (contains(x, y))
		{
			E oldObject = getObjectAt(x, y);

			array.remove(oldObject);

			for (int i = oldObject.getIMinY(); i < oldObject.getIMaxY(); i++)
			{
				for (int j = oldObject.getIMinX(); j < oldObject.getIMaxX(); j++)
				{
					grid[i][j] = null;
				}
			}

			return oldObject;
		}

		return null;
	}

	@Override
	public List<E> getObjects()
	{
		return array;
	}
}

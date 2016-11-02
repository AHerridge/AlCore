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
	public void addObject(E e)
	{
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
		if (contains(x, y) && grid[y][x] != null)
		{
			return grid[y][x].getParent();
		}

		return null;
	}

	@Override
	public List<E> getObjectsAt(Bound b)
	{

		if (contains(b))
		{
			LinkedList<E> foundObjects = new LinkedList<>();
			for (int i = b.getIMinY(); i < b.getIMaxY(); i++)
			{
				for (int j = b.getIMinX(); j < b.getIMaxX(); j++)
				{
					if (getObjectAt(j, i) != null && !foundObjects.contains(grid[i][j].getParent()))
					{
						foundObjects.add(grid[i][j].getParent());
					}
				}
			}
			return foundObjects;
		}

		return null;
	}

	@Override
	public List<Point> getEmptyNeighborsOf(Bound b)
	{
		LinkedList<Point> emptyNeighbors = new LinkedList<>();

		for (int i = b.getIMinY() - 1; i < b.getIMaxY() + 1; i++)
		{
			for (int j = b.getIMinX() - 1; j < b.getIMaxX() + 1; j++)
			{
				if (contains(j, i) && grid[i][j] == null)
				{
					emptyNeighbors.add(new Point(j, i));
				}
			}

		}

		return emptyNeighbors;
	}

	@Override
	public E removeObjectAt(int x, int y)
	{
		if (contains(x, y))
		{
			E oldObject = getObjectAt(x, y);

			if (oldObject != null)
			{
				array.remove(oldObject);

				for (int i = oldObject.getIMinY(); i < oldObject.getIMaxY(); i++)
				{
					for (int j = oldObject.getIMinX(); j < oldObject.getIMaxX(); j++)
					{
						grid[i][j] = null;
					}
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

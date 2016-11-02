package jrAlex.core.world.world_objects;

import jrAlex.core.world.Bound;
import jrAlex.core.world.DoubleWorldObjectContainer;
import jrAlex.core.world.WorldObjectContainer;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Alex on 10/31/2016.
 */

public class MovableWorldObject extends WorldObject
{
	private int speed;

	public MovableWorldObject(int x, int y, int width, int height, int speedPerSecond,
	                          WorldObjectContainer<? extends WorldObject> container)
	{
		super(x, y, width, height, true, container);
		this.speed = speedPerSecond;
	}

	public List<PathNode> getPathTo(int destX, int destY)
	{
		// TODO implement get closest empty position to destX and destY
		List<PathNode> path = new LinkedList<>();

		path.add(new PathNode(new Point((int) x, (int) y), null));

		while (path.get(0).getPos() != new Point(destX, destY))
		{
			List<Point2D> neighbors = new LinkedList<>();
			neighbors.addAll(container.getEmptyNeighborsOf(new Bound(path.get(0).getPos())));
			List<WorldObject> nonEmptyNeighbors = container.getNeighborsOf(new Bound(path.get(0).getPos()));

			for (int i = 0; i < nonEmptyNeighbors.size(); i++)
			{
				if (canItGoThrough(nonEmptyNeighbors.get(i)))
				{
					neighbors.add(nonEmptyNeighbors.get(i).getLocation());
				}
			}

			PathNode bestNeighbor = null;

			for (int i = 0; i < neighbors.size(); i++)
			{
				PathNode neighbor = new PathNode(neighbors.get(i), path.get(0));

				if (bestNeighbor == null || neighbor.getHeuristicCost() < bestNeighbor.getHeuristicCost())
				{
					bestNeighbor = neighbor;
				}
			}

			path.add(0, bestNeighbor);
		}

		return path;
	}

	@Override
	public void update(long delta)
	{
		translate(speed * delta / 1000.0 , speed * delta / 1000.0); // Multiply offX and offY by scale
	}
}

class PathNode
{
	private Point2D pos;
	private PathNode parent;

	public PathNode(Point2D pos, PathNode parent)
	{
		this.pos = pos;
		this.parent = parent;
	}

	public Point2D getPos()
	{
		return pos;
	}

	public PathNode getParent()
	{
		return parent;
	}

	public int getMovementCost()
	{
		return 1;
	}

	public int getHeuristicCost()
	{
		if (parent != null)
		{
			return parent.getHeuristicCost() + getMovementCost();
		}
		else
		{
			return getMovementCost();
		}
	}
}


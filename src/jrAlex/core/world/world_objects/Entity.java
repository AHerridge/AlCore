package jrAlex.core.world.world_objects;

import jrAlex.core.world.DoubleWorldObjectContainer;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Alex on 10/31/2016.
 */

public class Entity extends WorldObject
{
	public Entity(int x, int y, int width, int height, DoubleWorldObjectContainer container)
	{
		super(x, y, width, height, true, container);
	}

	public void getPathTo(int destX, int destY)
	{
		// TODO implement get closest empty position to destX and destY
		List<Point> path = new LinkedList<>();
		List<WorldObject> closed = new LinkedList<>();
	}
}

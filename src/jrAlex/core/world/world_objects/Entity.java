package jrAlex.core.world.world_objects;

import javafx.collections.transformation.SortedList;
import jrAlex.core.world.BoundContainer;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Alex on 10/31/2016.
 */

public class Entity extends WorldObject
{
	public Entity(int x, int y, int width, int height, BoundContainer container)
	{
		super(x, y, width, height, container);
	}

	public void getPathTo(int destX, int destY)
	{
		// TODO implement get closest empty position to destX and destY
		List<Point> path = new LinkedList<>();
		List<WorldObject> closed = new LinkedList<>();
		SortedList open = new SortedList<>();
	}
}

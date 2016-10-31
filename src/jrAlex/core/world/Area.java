package jrAlex.core.world;

import jrAlex.core.world.world_objects.WorldObject;

import java.awt.*;

/**
 * Created by Alex on 10/28/2016.
 */

public class Area extends BoundContainer<WorldObject>
{
	public Area(int x, int y, int width, int height, BoundContainer container)
	{
		super(x, y, width, height, container);
	}

	@Override
	public void redraw(Graphics g, int s)
	{
		g.setColor(Color.GRAY);
		super.redraw(g, s);
	}
}

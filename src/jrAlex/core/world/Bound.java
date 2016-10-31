package jrAlex.core.world;

import org.jetbrains.annotations.Contract;

import java.awt.*;

/**
 * Created by Alex on 10/28/2016.
 */

public class Bound extends Rectangle implements Updatable, Drawable
{
	public Bound(int x, int y, int width, int height)
	{
		super(x, y, width, height);
	}

	public double getDistanceTo(Bound b)
	{
		return getCenter().distance(b.getCenter());
	}

	@Contract(" -> !null")
	private Point getCenter()
	{
		return new Point((int) getCenterX(), (int) getCenterY());
	}

	public void rotate()
	{
		int temp = height;
		height = width;
		width = temp;
	}

	public int getIMaxX()
	{
		return (int) getMaxX() - 1;
	}

	public int getIMinX()
	{
		return (int) getMinX();
	}

	public int getIMaxY()
	{
		return (int) getMaxY() - 1;
	}

	public int getIMinY()
	{
		return (int) getMinY();
	}

	@Override
	public void update()
	{

	}

	@Override
	public void redraw(Graphics g, int s)
	{
		g.fillRect(x * s, y * s, width * s, height * s);
	}
}

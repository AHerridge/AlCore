package jrAlex.core.world;

import org.jetbrains.annotations.Contract;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Alex on 10/28/2016.
 */

public class Bound extends Rectangle2D.Double implements Updatable, Drawable
{
	public Bound(double x, double y, double w, double h)
	{
		super(x, y, w, h);
	}

	public Bound(Point2D p)
	{
		super(p.getX(), p.getY(), 1, 1);
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
		double temp = height;
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

	public List<Point> asPoints()
	{
		List<Point> points = new LinkedList<>();

		for (int y = getIMinY(); y < getIMaxY(); y++)
		{
			for (int x = getIMinX(); x < getIMaxX(); x++)
			{
				points.add(new Point(x, y));
			}
		}

		return points;
	}

	public int getIMinY()
	{
		return (int) getMinY();
	}

	public void translate(double offX, double offY)
	{
		x += offX;
		y += offY;
	}

	public void setLocation(double x, double y)
	{
		this.x = x;
		this.y = y;
	}

	public Point2D getLocation()
	{
		return new Point((int) x, (int) y);
	}

	@Override
	public void update(long delta)
	{

	}

	@Override
	public void redraw(Graphics g, int offX, int offY, int s)
	{
		g.drawRect((int) (x + offX) * s, (int) (y + offY) * s, (int) width * s, (int) height * s);
	}
}

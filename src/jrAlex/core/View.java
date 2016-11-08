package jrAlex.core;

import jrAlex.core.world.Drawable;
import jrAlex.core.world.Scalable;
import jrAlex.core.world.Updatable;

import javax.swing.*;

/**
 * Created by 19herridge_alexander on 11/2/16.
 */
public abstract class View extends JPanel implements Drawable, Updatable, Scalable
{
	private double scale;

	public View(double scale)
	{
		this.scale = scale;
	}

	@Override
	public double getScale()
	{
		return scale;
	}

	@Override
	public void setScale(double scale)
	{
		this.scale = scale;
	}
}

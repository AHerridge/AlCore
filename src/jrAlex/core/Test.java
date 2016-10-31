package jrAlex.core;

import jrAlex.core.world.Area;
import jrAlex.core.world.Bound;
import jrAlex.core.world.World;
import jrAlex.core.world.world_objects.animals.Animal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

/**
 * Created by Alex on 10/28/2016.
 */

public class Test
{
	public static void main(String[] args)
	{
		final World world = new World(0, 0, 20, 20);

		final Area area = new Area(0, 0, 10, 10);

		Animal animal1 = new Animal(3, 3, 2, 2, area)
		{
		};

		area.addObject(animal1);

		Animal animal2 = new Animal(0, 0, 3, 4, area)
		{
		};

		area.addObject(animal2);

		world.addObject(area);

		System.out.println(area.getObjectsAt(new Bound(2, 2, 2, 2)));

		JFrame frame = new JFrame()
		{
			{
				setFocusable(true);
				setUndecorated(true);
				setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
				setSize(320 * 2, 320 * 2);
				setLocationRelativeTo(null);
				this.add(new JPanel()
				{
					int xOff, yOff;
					int s = 32;

					{
						setFocusable(true);
						grabFocus();
						setSize(320 * 2, 320 * 2);

						this.addMouseMotionListener(new MouseMotionListener()
						{
							@Override
							public void mouseDragged(MouseEvent e)
							{

							}

							@Override
							public void mouseMoved(MouseEvent e)
							{
								if (e.getX() > getWidth() - 20)
								{
									xOff += 4;
								}
								else if (e.getX() < 20)
								{
									xOff -= 4;
								}

								if (e.getY() > getHeight() - 20)
								{
									yOff += 4;
								}
								else if (e.getY() < 20)
								{
									yOff -= 4;
								}
							}
						});
					}

					@Override
					protected void paintComponent(Graphics g)
					{
						super.paintComponent(g);

						Graphics2D g2d = (Graphics2D) g;
						g2d.translate(getWidth() / 2 + xOff, getHeight() / 2 + yOff);
						g2d.rotate(Math.toRadians(45));

						world.redraw(g, s);
					}
				});
				setVisible(true);
			}
		};
	}
}

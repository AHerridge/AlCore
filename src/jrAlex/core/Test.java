package jrAlex.core;

import jrAlex.core.world.Area;
import jrAlex.core.world.World;
import jrAlex.core.world.world_objects.Entity;

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
		World world = new World(20, 20);
		Area area = new Area(5, 5, 10, 10, world);
		Entity entity = new Entity(1, 1, 2, 2, area);

		world.addObjectAt(5, 5, area);
		area.addObjectAt(1, 1, entity);

		JFrame frame = new JFrame()
		{
			{
				setFocusable(true);
				//setUndecorated(true);
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
						//g2d.translate(getWidth() / 2 + xOff, getHeight() / 2 + yOff);
						//g2d.rotate(Math.toRadians(45));

						world.redraw(g2d, 0, 0, s);
					}
				});
				setVisible(true);
			}
		};
	}
}

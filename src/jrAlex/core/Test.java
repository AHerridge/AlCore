package jrAlex.core;

import jrAlex.core.world.Area;
import jrAlex.core.world.World;
import jrAlex.core.world.world_objects.MovableWorldObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * Created by Alex on 10/28/2016.
 */

public class Test
{
	public static void main(String[] args) throws InterruptedException
	{
		final World world = new World(20, 20);
		Area area = new Area(5, 5, 10, 10, world);
		MovableWorldObject entity = new MovableWorldObject(1, 1, 2, 2, 1, area);

		world.addObject(area);
		area.addObject(entity);

		System.out.println((int)2.99999);

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
					long lastUpdate = System.currentTimeMillis(), lastMove = System.currentTimeMillis();
					boolean mouseOnScreen;
					MouseEvent mouseEvent;
					int xOff, yOff, range, speed = -1;
					int s = 32;

					{
						setFocusable(true);
						setSize(320 * 2, 320 * 2);

						range = getWidth() / 8;

						this.addMouseListener(new MouseListener()
						{
							@Override
							public void mouseClicked(MouseEvent e)
							{

							}

							@Override
							public void mousePressed(MouseEvent e)
							{

							}

							@Override
							public void mouseReleased(MouseEvent e)
							{

							}

							@Override
							public void mouseEntered(MouseEvent e)
							{
								mouseOnScreen = true;
							}

							@Override
							public void mouseExited(MouseEvent e)
							{
								mouseOnScreen = false;
							}
						});

						this.addMouseMotionListener(new MouseMotionListener()
						{
							@Override
							public void mouseDragged(MouseEvent e)
							{

							}

							@Override
							public void mouseMoved(MouseEvent e)
							{
								mouseEvent = e;
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

						if (System.currentTimeMillis() - lastUpdate >= 60)
						{
							world.update(System.currentTimeMillis() - lastUpdate);
							lastUpdate = System.currentTimeMillis() + (System.currentTimeMillis() - lastUpdate);
						}
						world.redraw(g2d, xOff, yOff, s);

						if (mouseOnScreen && mouseEvent != null)
						{
							if (System.currentTimeMillis() - lastMove >= 50)
							{
								if (mouseEvent.getX() > getWidth() - range)
								{
									xOff += speed;
								}
								else if (mouseEvent.getX() < range)
								{
									xOff -= speed;
								}

								if (mouseEvent.getY() > getHeight() - range)
								{
									yOff += speed;
								}
								else if (mouseEvent.getY() < range)
								{
									yOff -= speed;
								}

								lastMove = System.currentTimeMillis();
							}
						}
					}
				});
				setVisible(true);
			}
		};

		while (true)
		{
			Thread.sleep(16);

			frame.repaint();
		}
	}
}

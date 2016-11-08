package jrAlex.core;

import jrAlex.core.world.Scalable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * Created by 19herridge_alexander on 11/2/16.
 */
public class MainWindow extends JFrame implements Scalable
{
	private final MainWindow instance = new MainWindow(640, 640, 1);
	private View view;
	private double scale;

	private MainWindow(int width, int height, double scale)
	{
		setFocusable(true);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setSize(width, height);
		setLocationRelativeTo(null);

		this.add(new JPanel()
		{
			long lastUpdate = System.currentTimeMillis(), lastMove = System.currentTimeMillis();
			boolean mouseOnScreen;
			MouseEvent mouseEvent;
			int xOff, yOff, range, speed = -1;

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
					view.update(System.currentTimeMillis() - lastUpdate);
					lastUpdate = System.currentTimeMillis() + (System.currentTimeMillis() - lastUpdate);
				}
				view.redraw(g2d, xOff, yOff, getScale());

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

	public View getView()
	{
		return view;
	}

	public void setView(View view)
	{
		remove(view);
		this.view = view;
		add(view);
	}

	public MainWindow getInstance()
	{
		return instance;
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

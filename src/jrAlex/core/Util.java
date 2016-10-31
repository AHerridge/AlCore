package jrAlex.core;

import org.jetbrains.annotations.Contract;

import java.awt.*;

/**
 * Created by Alex on 10/28/2016.
 */

public final class Util
{
	@Contract("_, _ -> !null")
	public static Point toIso(int x, int y)
	{
		return new Point(x - y, (x + y) / 2);
	}
}

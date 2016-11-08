package com.jralex;

import java.io.File;

/**
 * Created by Alex on 11/8/2016.
 */

public class Util
{
	public static File getResource(String fileName)
	{
		return new File("./res/" + fileName);
	}

	public static void printToConsole(Strings type, String prefix, Strings warningName, String suffix)
	{
		String text = type.getTranslation() + " : " + ((prefix == null) ? "" : " " + prefix) +
		              warningName.getTranslation() + ((suffix == null) ? "" : " " + suffix) + "!";
		if (type == Strings.Warning || type == Strings.Error)
		{
			System.err.println(text);
		}
		else
		{
			System.out.println(text);
		}
	}
}

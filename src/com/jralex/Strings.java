package com.jralex;

/**
 * Created by Alex on 11/8/2016.
 */

public enum Strings
{
	Resource_Not_Found("resource not found"),
	Warning("warning"),
	Error("error");

	String translation;

	Strings(String translation)
	{
		this.translation = translation;
	}

	public String getTranslation()
	{
		return translation;
	}
}

package com.jralex;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Alex on 11/8/2016.
 */

public class XMLResourceContainer
{
	private Map<String, String> map;

	public XMLResourceContainer(String fileName)
	{
		loadData(getXMLEventReader(fileName));
	}

	public String get(String name)
	{
		String value = map.get(name);

		if (value == null)
		{
			Util.printToConsole(Strings.Warning, null, Strings.Resource_Not_Found, name);
		}

		return value;
	}

	protected void loadData(XMLEventReader xmlEventReader)
	{
		map = new HashMap<>();
		try
		{
			String key = null;
			String value = null;

			while (xmlEventReader.hasNext())
			{
				XMLEvent event = xmlEventReader.nextEvent();

				switch (event.getEventType())
				{
					case XMLStreamConstants.START_ELEMENT:
						key = event.asStartElement().getName().getLocalPart();
						break;
					case XMLStreamConstants.CHARACTERS:
						value = event.asCharacters().getData();
						break;
				}

				if (key != null && value != null && !value.equals("\n "))
				{
					map.put(key, value);
					key = " ";
				}

				value = null;
			}
		}
		catch (XMLStreamException e)
		{
			e.printStackTrace();
		}
	}

	protected static XMLEventReader getXMLEventReader(String fileName)
	{
		try
		{
			return XMLInputFactory.newInstance()
					.createXMLEventReader(new FileReader(Util.getResource(fileName + ".xml")));
		}
		catch (XMLStreamException | FileNotFoundException e)
		{
			e.printStackTrace();
			return null;
		}
	}
}
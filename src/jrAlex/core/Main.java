package jrAlex.core;

import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

/**
 * Created by Alex on 10/30/2016.
 */

public class Main
{
	private Main()
	{
		if (!glfwInit())
		{
			System.err.println("Error: GLFW failed to initialize!");
		}

		long window = glfwCreateWindow(640, 512, "Game", 0, 0);

		glfwSetWindowPos(window, 500, 300);
		glfwShowWindow(window);

		glfwMakeContextCurrent(window);

		GL.createCapabilities();

		while (!glfwWindowShouldClose(window))
		{
			glfwPollEvents();

			glClear(GL_COLOR_BUFFER_BIT);

			glfwSwapBuffers(window);
		}

		glfwTerminate();
	}

	public static void main(String[] args)
	{
		new Main();
	}
}

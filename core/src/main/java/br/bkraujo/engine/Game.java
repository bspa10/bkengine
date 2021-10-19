package br.bkraujo.engine;

import br.bkraujo.engine.device.DeviceFactory;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class Game {
    private static final Logger LOGGER = LoggerFactory.getLogger(Game.class);
    private final String title;

    public Game(String title) {
        LOGGER.debug("<init>(%s)".formatted(title));
        this.title = title;

        final var callback = GLFWErrorCallback.createPrint(System.err);
        GLFW.glfwSetErrorCallback(callback);

        if ( !GLFW.glfwInit() ) {
            throw new EngineException("Unable to initialize GLFW");
        }
    }

    public void run() {
        final var window = DeviceFactory.createWindow(title, 800, 600);

        GL.createCapabilities();
        GLFW.glfwSwapInterval(1);

        window.show();

        while ( !window.isCloseRequested() ) {
            GL11.glClearColor(0.0f, 0.5f, 0.0f, 0.0f);
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

            // Processa eventos de entrada
            GLFW.glfwPollEvents();

            window.update();
            window.render();
        }

        window.hide();
        window.close();
    }

}

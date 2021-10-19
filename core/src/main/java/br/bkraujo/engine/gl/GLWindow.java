package br.bkraujo.engine.gl;

import br.bkraujo.engine.device.display.Window;
import org.lwjgl.glfw.Callbacks;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GLWindow implements Window {
    private static final Logger LOGGER = LoggerFactory.getLogger(GLWindow.class);
    private final long identity;
    private final int[] size = new int[2];
    private final String title;
    private int frames;
    private long time;

    public GLWindow(String title, int width, int height) {
        this.title = title;
        size[0] = width;
        size[1] = height;

        identity = createWindow();
        setPosition();

        GLFW.glfwMakeContextCurrent(identity);
    }

    private long createWindow(){
        GLFW.glfwDefaultWindowHints();
        GLFW.glfwWindowHint(GLFW.GLFW_VISIBLE, GLFW.GLFW_FALSE);
        GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, GLFW.GLFW_TRUE);

        final var identity = GLFW.glfwCreateWindow(size[0], size[1], title, 0, 0);
        if (identity == 0) {
            throw new RuntimeException("ERROR: MainWindow wasn't created");
        }

        return identity;
    }

    private void setPosition() {
        final int[] position = new int[2];
        final var videoMode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());
        if (videoMode != null) {
            final var displayWidth = videoMode.width();
            final var displayHeight = videoMode.height();

            position[0] = (displayWidth - size[0]) / 2;
            position[1] = (displayHeight - size[0]) / 2;
        } else {
            position[0] = 20;
            position[1] = 20;
        }

        GLFW.glfwSetWindowPos(identity, position[0], position[1]);
    }

    @Override
    public long identity() {
        return identity;
    }

    @Override
    public void update() {
        frames++;

        if (System.currentTimeMillis() > time + 1000) {
            GLFW.glfwSetWindowTitle(identity, title + " | FPS: " + frames);
            time = System.currentTimeMillis();
            frames = 0;
        }
    }

    @Override
    public void render() {
        GLFW.glfwSwapBuffers(identity);
    }

    @Override
    public boolean isCloseRequested() {
        return GLFW.glfwWindowShouldClose(identity);
    }

    @Override
    public void show() {
        LOGGER.debug("show()");
        GLFW.glfwShowWindow(identity);
    }

    @Override
    public void hide() {
        LOGGER.debug("hide()");
        GLFW.glfwHideWindow(identity);
    }

    @Override
    public void close() {
        LOGGER.debug("close()");
        Callbacks.glfwFreeCallbacks(identity);
        GLFW.glfwDestroyWindow(identity);
        GLFW.glfwTerminate();
    }

    public int[] getSize() {
        return size;
    }

    public void setSize(int width, int height) {
        LOGGER.debug("setSize(%s, %s)".formatted(width, height));
        GLFW.glfwSetWindowSize(identity, width, height);
    }

}


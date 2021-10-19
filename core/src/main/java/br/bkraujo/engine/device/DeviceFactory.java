package br.bkraujo.engine.device;

import br.bkraujo.engine.device.display.Window;
import br.bkraujo.engine.gl.GLWindow;

public abstract class DeviceFactory {
    private DeviceFactory(){}

    public static Window createWindow(String title, int width, int height) {
        return new GLWindow(title, width, height);
    }

}

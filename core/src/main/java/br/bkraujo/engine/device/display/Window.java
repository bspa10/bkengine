package br.bkraujo.engine.device.display;

public interface Window {

    long identity();

    void update();
    void render();
    boolean isCloseRequested();

    void show();
    void hide();
    void close();

    int[] getSize();
    void setSize(int width, int height);
}

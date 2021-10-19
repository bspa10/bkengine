package br.bkraujo.game.shooter;

import br.bkraujo.engine.Game;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Executors;

public class Application {
    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) throws InterruptedException {
        LOGGER.info("Initializing");
        final var executor = Executors.newSingleThreadExecutor();
        final var game = new Game("Game");

        LOGGER.info("Running");
        executor.execute(game::run);
        executor.shutdown();

        while (!executor.isShutdown()) {
            LOGGER.info("Waiting for the game to finish.");
            Thread.sleep(250);
        }
    }

}

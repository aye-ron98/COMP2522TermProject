package com.example.game;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * com.example.game.Game.
 *
 * @author Aron Zhang
 * @author Lex Wong
 * @version 202213
 */
public class Game extends Application {
    /**
     * Start the game in JavaFX.
     * @param stage Stage of the scene
     * @throws IOException When I/O operations are interrupted
     */
    @Override
    public void start(final Stage stage) throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader(Game.class.getResource("hello-view.fxml"));
            try {
                Scene scene = new Scene(fxmlLoader.load());

                stage.setTitle("Game");
                stage.setScene(scene);

                stage.show();
            } catch (IOException e) {
                throw new IOException();
            }
    }

    /**
     * Drive the program.
     * @param args Not used
     */
    public static void main(final String[] args) {
        launch();
    }
}

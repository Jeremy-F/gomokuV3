package fr.esiee;

import fr.esiee.Player.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
/**
 *****************************************************
 * ,----.     E3T - Esiee Paris      ,--.            *
 * '  .-./    ,---. ,--,--,--. ,---. |  |,-.,--.,--. *
 * |  | .---.| .-. ||        || .-. ||     /|  ||  | *
 * '  '--'  |' '-' '|  |  |  |' '-' '|  \  \'  ''  ' *
 * `------'  `---' `--`--`--' `---' `--'`--'`------' *
 *    Alexandre Causse            Jérémy Fornarino   *
 *****************************************************
 * @author Alexandre Causse & Jérémy Fornarino   [E3T]
 */
public class Game extends Application{


    private Board board;
    private GridPane gridPane;


    public static void main(String[] args) {
        //launch();
        /**/
        Player alexandre = new MinMaxScorePlayer("Alexandre", Color.BLUE);
        Player jeremy = new MinMaxScorePlayer("Jérémy", Color.RED);
        Board board = new Board(null, 4, 4, alexandre, jeremy);
        board.launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Gomoku C&F xX");

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Game.class.getResource("view/RootLayout.fxml"));

        BorderPane borderPane = (BorderPane) loader.load();

        this.gridPane = new GridPane();
        this.gridPane.setGridLinesVisible(true);

        GridPane boardGridPane = this.gridPane;

        borderPane.setCenter(boardGridPane);

        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        primaryStage.show();

        updateGridPane();
    }
    public void updateGridPane() {
        for (int line = 0; line < this.board.dimension(); line++) {
            for (int column = 0; column < this.board.dimension(); column++) {

                final Node boxNode = this.board.getBox(line, column).toNode();
                this.gridPane.add(boxNode,column,line);

                final int finalLine = line;
                final int finalColumn = column;

                boxNode.setOnMouseClicked(event -> {
                    this.board.play(finalLine, finalColumn);

                    this.board.graphLaunch();
                });
            }
        }
    }
    @Override
    public void init(){
        Player alexandre = new Person("Alexandre", Color.BLUE);
        Player jeremy = new MinMaxScorePlayer("Jérémy", Color.RED);
        this.board = new Board(this, 5, 4, alexandre, jeremy);
    }
    @Override
    public void stop(){

    }
}

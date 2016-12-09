package ui;

import apptemplate.AppTemplate;
import controller.FileController;
import data.GameData;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;

/**
 * Created by sai on 12/8/16.
 */
public class HelpScreen extends BorderPane{
    protected AppTemplate app;
    protected FileController fileController;
    protected GameData gamedata;
    protected Button xButton;
    protected HBox closeHBox;
    protected HBox titleHBox;
    protected Label title;
    protected VBox topVBox;
    protected VBox list;
    protected Label help;

    public HelpScreen(FileController fileController, GameData gamedata) {
        this.fileController = fileController;
        this.gamedata = gamedata;
        initialize();
    }

    public void initialize()
    {
        this.setPrefSize(800, 650);
        this.setStyle("-fx-background-color: lightseagreen;");
        //top of the screen including close button and title
        xButton = new Button();
        xButton.setText("x");
        xButton.setTooltip(new Tooltip("Close"));
        xButton.setTranslateX(760);
        xButton.setTranslateY(10);
        closeHBox = new HBox(200);
        closeHBox.getChildren().add(xButton);
        title = new Label();
        title.setText("HELP");
        title.setScaleX(2);
        title.setScaleY(2);
        titleHBox = new HBox(100);
        titleHBox.getChildren().add(title);
        titleHBox.setAlignment(Pos.CENTER);
        topVBox = new VBox(50);
        topVBox.getChildren().addAll(closeHBox, titleHBox);
        this.setTop(topVBox);

        help = new Label();
        help.setText("GO TO SRS PAGE \n GO TO SRS PAGE\n GO TO SRS PAGE\n GO TO SRS PAGE\n GO TO SRS PAGE\n GO TO SRS PAGE\n GO TO SRS PAGE\n GO TO SRS PAGE\n GO TO SRS PAGE\n GO TO SRS PAGE\n GO TO SRS PAGE\n GO TO SRS PAGE");
        help.setScaleX(3);
        help.setScaleY(3);
        this.setCenter(help);
        ScrollBar s= new ScrollBar();
        s.setOrientation(Orientation.VERTICAL);
        s.setMin(0);
        s.setMax(0);
        s.setValue(0);
        this.setRight(s);
//        s.valueProperty().addListener(new ChangeListener<Number>() {
//            public void changed(ObservableValue<? extends Number> ov,
//                                Number old_val, Number new_val) {
//                this.setLayoutY(-new_val.doubleValue());
//            }
//        });

        Button home = new Button();
        home.setText("Home");
        home.setTranslateX(200);
        this.setBottom(home);
        home.setOnAction(e -> {
            try {
                fileController.handleHomeHelpRequest();
            } catch (IOException e1) {
                e1.printStackTrace();
                System.exit(1);
            }
        });
        xButton.setOnAction(e -> {
            try {
                fileController.handleXRequest();
                System.exit(1);
            } catch (IOException e1) {
                e1.printStackTrace();
                System.exit(1);
            }
        });
    }


}

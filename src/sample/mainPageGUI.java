package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

//GUI page using JAVAFX to start and quit game working as main gui page for game

public class mainPageGUI extends Application {
    Stage primaryStage1;
    StackPane stackPane;
    Scene scene1;
    Image image;
    ImageView imageview1;
    Button button1;
    Button button2;
    ComboBox<Integer> comboBox;
    VBox vbox;
    Pitch gs = new Pitch();
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Pitch");
        image = new Image("bg-1.jpg");
        imageview1 = new ImageView(image);
        button1 = new Button("Play Game");
        button2 = new Button("Quit");
        comboBox = new ComboBox<>();
        comboBox.getItems().addAll(2, 3, 4);
        comboBox.getSelectionModel().selectFirst();
        //comboBox.setPromptText("Number of Player:");
        button1.setOnAction(e -> playerNumber());
        button2.setOnAction(e -> Platform.exit());
        button1.setStyle("-fx-background-color: #090a0c, linear-gradient(#38424b 0%, #1f2429 20%, #191d22 100%), linear-gradient(#20262b, #191d22), radial-gradient(center 50% 0%, radius 100%, rgba(114,131,148,0.9), rgba(255,255,255,0)); -fx-background-radius: 5,4,3,5; -fx-background-insets: 0,1,2,0; -fx-text-fill: white; -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 ); -fx-font-family:Arial; -fx-text-fill: linear-gradient(white, #d0d0d0); -fx-font-size: 12px; -fx-padding: 10 20 10 20;");
        button2.setStyle("-fx-background-color: #090a0c, linear-gradient(#38424b 0%, #1f2429 20%, #191d22 100%), linear-gradient(#20262b, #191d22), radial-gradient(center 50% 0%, radius 100%, rgba(114,131,148,0.9), rgba(255,255,255,0)); -fx-background-radius: 5,4,3,5; -fx-background-insets: 0,1,2,0; -fx-text-fill: white; -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 ); -fx-font-family:Arial; -fx-text-fill: linear-gradient(white, #d0d0d0); -fx-font-size: 12px; -fx-padding: 10 20 10 20;");
        vbox = new VBox();
        TextFlow label = new TextFlow();
        Text text = new Text("Pitch : The Game");
        text.setFill(Color.WHITE);
        text.setFont(Font.font(24.0));
        label.getChildren().add(text);
        label.setTextAlignment(TextAlignment.RIGHT);
        vbox.setAlignment(Pos.BASELINE_RIGHT);
        vbox.getChildren().addAll(label, comboBox, button1, button2);
        stackPane = new StackPane(imageview1, vbox);
        scene1 = new Scene(stackPane, 626, 417);
        primaryStage.setScene(scene1);
        primaryStage1 = primaryStage;
        primaryStage.show();
        //gs.start(primaryStage);
    }
    private void playerNumber()
    {
        gs.number = comboBox.getValue();
        gs.start(primaryStage1);
    }


}

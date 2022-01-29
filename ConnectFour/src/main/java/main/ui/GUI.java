package main.ui;

import main.Peli;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class GUI extends Application{
    
    private Peli peli;
    private GridPane lauta;
    
    @Override
    public void init()throws Exception{
        lauta = new GridPane();
        lauta.setBackground(new Background( new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        lauta.setHgap(0.5);
        lauta.setVgap(0.5);
        int i = 15;
        for(int x=1;x<8;x++){
            for(int y=1;y<7;y++){
                Circle ruutu = new Circle(40);
                ruutu.setFill(Color.WHITE); 
                lauta.add(ruutu, i*x, i*y);
            }
        }
        peli = new Peli();
    }
    
    @Override
    public void start(Stage stage){
        HBox sarakkeidenNapit = new HBox();
        
        Button testi = new Button("sarake 1");
        sarakkeidenNapit.getChildren().addAll(testi);
        
        VBox asettelu = new VBox();
        asettelu.getChildren().addAll(sarakkeidenNapit);
        asettelu.getChildren().addAll(lauta);
        Scene glauta = new Scene(asettelu, 680, 595);
        stage.setScene(glauta);
        stage.show();
    }
    public static void main(String args[]){
        launch(args);
    }
}

package main.ui;

import main.Peli;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class GUI extends Application{
    private Circle[] ruudut = new Circle[42];
    private Peli peli;
    private GridPane lauta;
    private int vuoro;
    private Scene taysi;
    private Scene voitto;
    private Stage popup;
    
    @Override
    public void init()throws Exception{
        lauta = new GridPane();
        lauta.setBackground(new Background( new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        lauta.setHgap(0.5);
        lauta.setVgap(0.5);
        int i = 15;
        int r = 0;
        for(int x=1;x<8;x++){
            for(int y=1;y<7;y++){
                Circle ruutu = new Circle(40);
                ruutu.setFill(Color.WHITE); 
                ruudut[r] = ruutu;
                r++;
                lauta.add(ruutu, i*x, i*y);
            }
        }
        vuoro = 1;
        peli = new Peli();
    }
    
    @Override
    public void start(Stage stage){
        Label varoitus = new Label("Tämä sarake on täysi");
        VBox viesti = new VBox();
        VBox voittoRuutu = new VBox();
        viesti.getChildren().add(varoitus);
        taysi = new Scene(viesti, 120, 50);
        voitto = new Scene(voittoRuutu, 200, 100);
        popup = new Stage();
        popup.setScene(taysi);
        HBox sarakkeidenNapit = new HBox();
                
        Button sarake1 = new Button("Sarake 1");
        sarake1.setPrefWidth(100);
        sarake1.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent t){
                if(peli.asetaPala(0)){
                    for(int i=0;i<6;i++){
                        if(ruudut[i+1].getFill() != Color.WHITE || i == 5){
                            if(vuoro == 1){
                                ruudut[i].setFill(Color.RED);
                            }else{
                                ruudut[i].setFill(Color.YELLOW);
                            }
                            break;
                        }
                    }
                    if(peli.tarkistaVoitot()){
                        voittoRuutu.getChildren().add(new Label("Pelaaja " + vuoro + " voitti!"));
                        popup.setScene(voitto);
                        popup.show();
                    }else{
                        if(vuoro == 1){
                            vuoro = 2;
                        }else{
                            vuoro =1;
                        }
                    }
                }else{
                    popup.show();
                }
            }
        });

        Button sarake2 = new Button("Sarake 2");
        sarake2.setPrefWidth(100);
        sarake2.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent t){
                if(peli.asetaPala(1)){
                    for(int i=6;i<12;i++){
                        if(ruudut[i+1].getFill() != Color.WHITE || i == 11){
                            if(vuoro == 1){
                                ruudut[i].setFill(Color.RED);
                            }else{
                                ruudut[i].setFill(Color.YELLOW);
                            }
                            break;
                        }
                    }
                    if(peli.tarkistaVoitot()){
                        voittoRuutu.getChildren().add(new Label("Pelaaja " + vuoro + " voitti!"));
                        popup.setScene(voitto);
                        popup.show();
                    }else{
                        if(vuoro == 1){
                            vuoro = 2;
                        }else{
                            vuoro =1;
                        }
                    }
                }else{
                    popup.show();
                }
            }
        });

        Button sarake3 = new Button("Sarake 3");
        sarake3.setPrefWidth(100);
        sarake3.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent t){
                if(peli.asetaPala(2)){
                    for(int i=12;i<18;i++){
                        if(ruudut[i+1].getFill() != Color.WHITE || i == 17){
                            if(vuoro == 1){
                                ruudut[i].setFill(Color.RED);
                            }else{
                                ruudut[i].setFill(Color.YELLOW);
                            }
                            break;
                        }
                    }
                    if(peli.tarkistaVoitot()){
                        voittoRuutu.getChildren().add(new Label("Pelaaja " + vuoro + " voitti!"));
                        popup.setScene(voitto);
                        popup.show();
                    }else{
                        if(vuoro == 1){
                            vuoro = 2;
                        }else{
                            vuoro =1;
                        }
                    }
                }else{
                    popup.show();
                }
            }
        });

        Button sarake4 = new Button("Sarake 4");
        sarake4.setPrefWidth(100);
        sarake4.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent t){
                if(peli.asetaPala(3)){
                    for(int i=18;i<24;i++){
                        if(ruudut[i+1].getFill() != Color.WHITE || i == 23){
                            if(vuoro == 1){
                                ruudut[i].setFill(Color.RED);
                            }else{
                                ruudut[i].setFill(Color.YELLOW);
                            }
                            break;
                        }
                    }
                    if(peli.tarkistaVoitot()){
                        voittoRuutu.getChildren().add(new Label("Pelaaja " + vuoro + " voitti!"));
                        popup.setScene(voitto);
                        popup.show();
                    }else{
                        if(vuoro == 1){
                            vuoro = 2;
                        }else{
                            vuoro =1;
                        }
                    }
                }else{
                    popup.show();
                }
            }
        });

        Button sarake5 = new Button("Sarake 5");
        sarake5.setPrefWidth(100);
        sarake5.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent t){
                if(peli.asetaPala(4)){
                    for(int i=24;i<30;i++){
                        if(ruudut[i+1].getFill() != Color.WHITE || i == 29){
                            if(vuoro == 1){
                                ruudut[i].setFill(Color.RED);
                            }else{
                                ruudut[i].setFill(Color.YELLOW);
                            }
                            break;
                        }
                    }
                    if(peli.tarkistaVoitot()){
                        voittoRuutu.getChildren().add(new Label("Pelaaja " + vuoro + " voitti!"));
                        popup.setScene(voitto);
                        popup.show();
                    }else{
                        if(vuoro == 1){
                            vuoro = 2;
                        }else{
                            vuoro =1;
                        }
                    }
                }else{
                    popup.show();
                }
            }
        });

        Button sarake6 = new Button("Sarake 6");
        sarake6.setPrefWidth(100);
        sarake6.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent t){
                if(peli.asetaPala(5)){
                    for(int i=30;i<36;i++){
                        if(ruudut[i+1].getFill() != Color.WHITE || i == 35){
                            if(vuoro == 1){
                                ruudut[i].setFill(Color.RED);
                            }else{
                                ruudut[i].setFill(Color.YELLOW);
                            }
                            break;
                        }
                    }
                    if(peli.tarkistaVoitot()){
                        voittoRuutu.getChildren().add(new Label("Pelaaja " + vuoro + " voitti!"));
                        popup.setScene(voitto);
                        popup.show();
                    }else{
                        if(vuoro == 1){
                            vuoro = 2;
                        }else{
                            vuoro =1;
                        }
                    }
                }else{
                    popup.show();
                }
            }
        });

        Button sarake7 = new Button("Sarake 7");
        sarake7.setPrefWidth(100);
        sarake7.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent t){
                if(peli.asetaPala(6)){
                    for(int i=36;i<42;i++){
                        if(ruudut[i+1].getFill() != Color.WHITE){
                            if(vuoro == 1){
                                ruudut[i].setFill(Color.RED);
                            }else{
                                ruudut[i].setFill(Color.YELLOW);
                            }
                            break;
                        }
                        if(i == 40){
                            i++;
                            if(vuoro == 1){
                                ruudut[i].setFill(Color.RED);
                            }else{
                                ruudut[i].setFill(Color.YELLOW);
                            }
                            break;
                        }
                    }
                    if(peli.tarkistaVoitot()){
                        voittoRuutu.getChildren().add(new Label("Pelaaja " + vuoro + " voitti!"));
                        popup.setScene(voitto);
                        popup.show();
                    }else{
                        if(vuoro == 1){
                            vuoro = 2;
                        }else{
                            vuoro =1;
                        }
                    }
                }else{
                    popup.show();
                }
            }
        });
        sarakkeidenNapit.getChildren().addAll(sarake1, sarake2, sarake3, sarake4, sarake5, sarake6, sarake7);
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

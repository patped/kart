/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package no.hit.kart;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;


public class KartApp extends Application {
  final private String FIL = "/hendelser.txt";
   
    @Override
    public void start(Stage stage) {
    // Definerer vinduet og initierer scenegrafen
    
    Group root = new Group();
    Scene scene = new Scene(root, 800, 400);
    stage.setScene(scene);
    stage.setTitle("Demo av enkel grafikk i JavaFX");
    stage.setResizable(false);
    
    Image bilde = new Image("/verden.jpg");
    ImageView bildenode = new ImageView();
    bildenode.setImage(bilde);
    root.getChildren().add(bildenode);
    
    // Les inn
    Hendelse[] hendelser = null;
    try {
      hendelser = lesFraFil(FIL);
    } catch (Exception ex) {
      Logger.getLogger(KartApp.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    for (int i=0; i<hendelser.length; i++)
      root.getChildren().add(tegnSirkel(hendelser[i]));
    
    // Viser scenegrafen
    stage.show();
  }
 
  public static void main(String[] args) throws Exception {
    launch(args);
  }

  private int LesHeltallFraBruker(String kordinatNavn) {
    TextInputDialog dialog = new TextInputDialog();
    dialog.setHeaderText("Kordinat " + kordinatNavn);
    Optional<String> result = dialog.showAndWait();
    if (result.isPresent())
      return Integer.parseInt(result.get());
    else
      return 0;
  }
  
  public Hendelse[] lesFraFil(String fil) throws Exception {
    InputStream in = getClass().getResourceAsStream(fil);
    String [] data;
    BufferedReader innfil = new BufferedReader(new InputStreamReader(in, "UTF-8"));
    int antall = Integer.parseInt(innfil.readLine());
    Hendelse[] hendelser = new Hendelse[antall];
    for (int i=0;i<antall;i++) {
      hendelser[i] = new Hendelse(innfil.readLine());
    }
    return hendelser;
  }
  
    // Tegner en sirkel (legger den inn i scenegrafen)
    private Circle tegnSirkel(Hendelse hendelse) {
    Circle sirkel = new Circle(
            hendelse.getPunkt().getX(),
            hendelse.getPunkt().getY(),
            10,
            Color.rgb(0, 0, 255)
    );
    return sirkel;
  }
}
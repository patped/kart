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
import javafx.scene.text.Text;
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

    // Les inn fra bruker
    Punkt bruker = new Punkt(LesHeltallFraBruker("X"), LesHeltallFraBruker("Y"));
    root.getChildren().add(
        tegnSirkel(
            bruker,
            farge(bruker, bruker)
        )
    );
    Text beskrivelse = new Text("Bruker");
    beskrivelse.setX(bruker.getX() + 20);
    beskrivelse.setY(bruker.getY() + 5);
    root.getChildren().add(beskrivelse);

    // Les inn fra fil
    Hendelse[] hendelser = null;
    try {
      hendelser = lesFraFil(FIL);
    } catch (Exception ex) {
      Logger.getLogger(KartApp.class.getName()).log(Level.SEVERE, null, ex);
    }
    for (int i = 0; i < hendelser.length; i++) {
      root.getChildren().add(
          tegnSirkel(
              hendelser[i].getPunkt(),
              farge(bruker, hendelser[i].getPunkt())
          )
      );
      beskrivelse = new Text(hendelser[i].getBeskrivelse());
      beskrivelse.setX(hendelser[i].getPunkt().getX() + 20);
      beskrivelse.setY(hendelser[i].getPunkt().getY() + 5);
      root.getChildren().add(beskrivelse);
    }

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
    if (result.isPresent()) {
      return Integer.parseInt(result.get());
    } else {
      return 0;
    }
  }

  public Hendelse[] lesFraFil(String fil) throws Exception {
    InputStream in = getClass().getResourceAsStream(fil);
    String[] data;
    BufferedReader innfil = new BufferedReader(new InputStreamReader(in, "UTF-8"));
    int antall = Integer.parseInt(innfil.readLine());
    Hendelse[] hendelser = new Hendelse[antall];
    for (int i = 0; i < antall; i++) {
      hendelser[i] = new Hendelse(innfil.readLine());
    }
    return hendelser;
  }

  // Tegner en sirkel (legger den inn i scenegrafen)
  private Circle tegnSirkel(Punkt punkt, int[] farge) {
    Circle sirkel = new Circle(
        punkt.getX(),
        punkt.getY(),
        10,
        Color.rgb(farge[0], farge[1], farge[2])
    );
    return sirkel;
  }

  // Velger farge på sirkel
  private int[] farge(Punkt a, Punkt b) {
    if (a.avstand(b) == 0) {
      return new int[]{0, 255, 0};
    }
    if (a.avstand(b) < 20) {
      return new int[]{255, 0, 0};
    } else {
      return new int[]{0, 0, 255};
    }
  }
}

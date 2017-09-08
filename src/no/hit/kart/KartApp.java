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
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

public class KartApp extends Application {

  final private String FIL = "/hendelser.txt";
  Group root = new Group();

  @Override
  public void start(Stage stage) {
    // Definerer vinduet og initierer scenegrafen

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
    lagKryss(bruker.getX(), bruker.getY());

    // Les inn fra fil
    Hendelse[] hendelser = null;
    try {
      hendelser = lesFraFil(FIL);
    } catch (Exception ex) {
      Logger.getLogger(KartApp.class.getName()).log(Level.SEVERE, null, ex);
    }

    // Finner det nærmeste punktet
    double kortesteAvstand = Double.MAX_VALUE;
    Punkt nærmeste = null;
    for (Hendelse hendelse : hendelser) {
      if (bruker.avstand(hendelse.getPunkt()) < kortesteAvstand) {
        kortesteAvstand = bruker.avstand(hendelse.getPunkt());
        nærmeste = hendelse.getPunkt();
      }
    }
    
    for (int i = 0; i < hendelser.length; i++) {
      root.getChildren().add(
          tegnSirkel(
              hendelser[i].getPunkt(),
              farge(nærmeste, hendelser[i].getPunkt(), kortesteAvstand)
          )
      );
      Text beskrivelse = new Text(hendelser[i].getBeskrivelse());
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
  private int[] farge(Punkt nærmeste, Punkt a, double kortesteAvstand) {
    if (kortesteAvstand < 50 && nærmeste == a) {
      return new int[]{255, 0, 0};
    } else {
      return new int[]{0, 0, 255};
    }
  }

  private void lagKryss(int fraBrukerX, int fraBrukerY) {
    Rectangle kryss1 = new Rectangle(fraBrukerX + 5.5, fraBrukerY - 10.5, 4, 16);
    Rectangle kryss2 = new Rectangle(fraBrukerX, fraBrukerY, 4, 16);
    kryss1.setFill(Color.BLUE);
    kryss2.setFill(Color.BLUE);
    Rotate rotate = new Rotate(45, fraBrukerX, fraBrukerY);
    Rotate rotate2 = new Rotate(135, fraBrukerX, fraBrukerY);
    kryss2.getTransforms().add(rotate);
    kryss1.getTransforms().add(rotate2);

    root.getChildren().add(kryss1);
    root.getChildren().add(kryss2);

  }
}

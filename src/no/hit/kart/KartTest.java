/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package no.hit.kart;

/**
 *
 * @author patrick
 */
public class KartTest {

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {

    //Punkt test
    Punkt A = new Punkt(30, 700);
    Punkt B = new Punkt(300, 86);
    System.out.println(A.avstand(B));
    System.out.println(A);

    //Hendelse test
    Hendelse C = new Hendelse(
        new Dato(23, 05, 2017),
        B,
        "Kul tur"
    );
    System.out.println(C.toString());
  }

}

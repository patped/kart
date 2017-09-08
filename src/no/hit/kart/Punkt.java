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
public class Punkt {

  private int x;
  private int y;

  public Punkt(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public int getX() {
    return x;
  }

  public void setX(int x) {
    this.x = x;
  }

  public int getY() {
    return y;
  }

  public void setY(int y) {
    this.y = y;
  }

  @Override
  public String toString() {
    return "Punkt{" + "x=" + x + ", y=" + y + '}';
  }

  public double avstand(Punkt punktTo) {
    return Math.sqrt(
        Math.pow((-this.x + punktTo.x), 2)
        + Math.pow((-this.y + punktTo.y), 2)
    );
  }

}

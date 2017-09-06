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
public class Hendelse {
  Dato dato;
  Punkt punkt;
  String beskrivelse;

  public Hendelse(Dato dato, Punkt punkt, String beskrivelse) {
    this.dato = dato;
    this.punkt = punkt;
    this.beskrivelse = beskrivelse;
  }

  public Dato getDato() {
    return dato;
  }

  public void setDato(Dato dato) {
    this.dato = dato;
  }

  public Punkt getPunkt() {
    return punkt;
  }

  public void setPunkt(Punkt punkt) {
    this.punkt = punkt;
  }

  public String getBeskrivelse() {
    return beskrivelse;
  }

  public void setBeskrivelse(String beskrivelse) {
    this.beskrivelse = beskrivelse;
  }

  @Override
  public String toString() {
    return "Hendelse{" + "dato=" + dato + ", punkt=" + punkt + ", beskrivelse=" + beskrivelse + '}';
  }
}

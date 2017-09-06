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
public class Dato {
    private int dag;
    private int mnd;
    private int år;

    public Dato(int dag, int mnd, int år) {
        this.dag = dag;
        this.mnd = mnd;
        this.år = år;
    }
    
    public Dato(String data) {
      String[] dato = data.split("\\.");
      
      dag = Integer.parseInt(dato[0]);
      mnd = Integer.parseInt(dato[1]);
      år = Integer.parseInt(dato[2]);
    }

    public int getDag() {
        return dag;
    }

    public void setDag(int dag) {
        this.dag = dag;
    }

    public int getMnd() {
        return mnd;
    }

    public void setMnd(int mnd) {
        this.mnd = mnd;
    }

    public int getÅr() {
        return år;
    }

    public void setÅr(int år) {
        this.år = år;
    }

    @Override
    public String toString() {
        return "Dato{" + "dag=" + dag + ", mnd=" + mnd + ", \u00e5r=" + år + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + this.dag;
        hash = 83 * hash + this.mnd;
        hash = 83 * hash + this.år;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Dato other = (Dato) obj;
        if (this.dag != other.dag) {
            return false;
        }
        if (this.mnd != other.mnd) {
            return false;
        }
        if (this.år != other.år) {
            return false;
        }
        return true;
    }
    
    
}

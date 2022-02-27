package com.home;

import java.time.LocalDateTime;

public class Fuvar {
    private int taxi_id;
    private String indulas;
    private int idotartam;
    private double tavolsag;
    private double viteldij;
    private double borravalo;
    private String fizetes_modja;

    public Fuvar(String lineString) {
        String[] line = lineString.split(";");

        this.taxi_id = Integer.parseInt(line[0]);
        this.indulas = line[1];
        this.idotartam = Integer.parseInt(line[2]);
        this.tavolsag = Double.parseDouble(line[3].replace(",", "."));
        this.viteldij = Double.parseDouble(line[4].replace(",", "."));
        this.borravalo = Double.parseDouble(line[5].replace(",", "."));
        this.fizetes_modja = line[6];
    }

    public int getTaxi_id() {
        return taxi_id;
    }

    public void setTaxi_id(int taxi_id) {
        this.taxi_id = taxi_id;
    }

    public String getIndulas() {
        return indulas;
    }

    public void setIndulas(String indulas) {
        this.indulas = indulas;
    }

    public int getIdotartam() {
        return idotartam;
    }

    public void setIdotartam(int idotartam) {
        this.idotartam = idotartam;
    }

    public double getTavolsag() {
        return tavolsag;
    }

    public void setTavolsag(double tavolsag) {
        this.tavolsag = tavolsag;
    }

    public double getViteldij() {
        return viteldij;
    }

    public void setViteldij(double viteldij) {
        this.viteldij = viteldij;
    }

    public double getBorravalo() {
        return borravalo;
    }

    public void setBorravalo(double borravalo) {
        this.borravalo = borravalo;
    }

    public String getFizetes_modja() {
        return fizetes_modja;
    }

    public void setFizetes_modja(String fizetes_modja) {
        this.fizetes_modja = fizetes_modja;
    }

    @Override
    public String toString() {
        return "\nFuvar{" +
                "taxi_id=" + taxi_id +
                ", indulas='" + indulas + '\'' +
                ", idotartam=" + idotartam +
                ", tavolsag=" + tavolsag +
                ", viteldij=" + viteldij +
                ", borravalo=" + borravalo +
                ", fizetes_modja='" + fizetes_modja + '\'' +
                '}';
    }
}

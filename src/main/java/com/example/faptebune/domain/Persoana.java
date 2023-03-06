package com.example.faptebune.domain;

public class Persoana extends Entity<Long>{
    private String nume;
    private String prenume;
    private String username;
    private String parola;
    Orase oras;
    private String strada;
    private String numar_strada;
    private String telefon;

    public Persoana(Long id, String nume, String prenume, String username, String parola, Orase oras, String strada, String numar_strada, String telefon) {
        super(id);
        this.nume = nume;
        this.prenume = prenume;
        this.username = username;
        this.parola = parola;
        this.oras = oras;
        this.strada = strada;
        this.numar_strada = numar_strada;
        this.telefon = telefon;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getParola() {
        return parola;
    }

    public void setParola(String parola) {
        this.parola = parola;
    }

    public Orase getOras() {
        return oras;
    }

    public void setOras(Orase oras) {
        this.oras = oras;
    }

    public String getStrada() {
        return strada;
    }

    public void setStrada(String strada) {
        this.strada = strada;
    }

    public String getNumar_strada() {
        return numar_strada;
    }

    public void setNumar_strada(String numar_strada) {
        this.numar_strada = numar_strada;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    @Override
    public String toString() {
        return "Persoana{" +
                "nume='" + nume + '\'' +
                ", prenume='" + prenume + '\'' +
                ", username='" + username + '\'' +
                ", parola='" + parola + '\'' +
                ", oras=" + oras +
                ", strada='" + strada + '\'' +
                ", numar_strada='" + numar_strada + '\'' +
                ", telefon='" + telefon + '\'' +
                '}';
    }
}
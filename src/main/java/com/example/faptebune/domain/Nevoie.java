package com.example.faptebune.domain;

import java.time.LocalDateTime;

public class Nevoie extends Entity<Long>{
    private String titlu;
    private String descriere;
    private LocalDateTime deadline;
    private Long om_in_nevoie;
    private Long om_salvator;
    private String status;

    public Nevoie(Long id, String titlu, String descriere, LocalDateTime deadline, Long om_in_nevoie, Long om_salvator, String status) {
        super(id);
        this.titlu = titlu;
        this.descriere = descriere;
        this.deadline = deadline;
        this.om_in_nevoie = om_in_nevoie;
        this.om_salvator = om_salvator;
        this.status = status;
    }

    public String getTitlu() {
        return titlu;
    }

    public void setTitlu(String titlu) {
        this.titlu = titlu;
    }

    public String getDescriere() {
        return descriere;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public Long getOm_in_nevoie() {
        return om_in_nevoie;
    }

    public void setOm_in_nevoie(Long om_in_nevoie) {
        this.om_in_nevoie = om_in_nevoie;
    }

    public Long getOm_salvator() {
        return om_salvator;
    }

    public void setOm_salvator(Long om_salvator) {
        this.om_salvator = om_salvator;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Nevoie{" +
                "titlu='" + titlu + '\'' +
                ", descriere='" + descriere + '\'' +
                ", deadline=" + deadline +
                ", om_in_nevoie=" + om_in_nevoie +
                ", on_salvator=" + om_salvator +
                ", status='" + status + '\'' +
                '}';
    }
}
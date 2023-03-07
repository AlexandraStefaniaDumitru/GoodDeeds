package com.example.faptebune.service;

import com.example.faptebune.domain.Nevoie;
import com.example.faptebune.domain.Orase;
import com.example.faptebune.domain.Persoana;
import com.example.faptebune.domain.exceptions.EmptyStringException;
import com.example.faptebune.domain.exceptions.NegativeNumberException;
import com.example.faptebune.repository.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Service {
    Repository<Long, Persoana> persoanaRepository;
    Repository<Long, Nevoie> nevoieRepository;

    public Service(Repository<Long, Persoana> persoanaRepository, Repository<Long, Nevoie> nevoieRepository) {
        this.persoanaRepository = persoanaRepository;
        this.nevoieRepository = nevoieRepository;
    }

    public void inregistrare(String nume, String prenume, String username, String parola, Orase oras, String strada, String nrStrada, String telefon) {
        Long id = Long.parseLong(telefon);
        Persoana persoana = new Persoana(id, nume, prenume, username, parola, oras, strada, nrStrada, telefon);
        try {
            this.persoanaRepository.save(persoana);
        } catch (EmptyStringException | NegativeNumberException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Persoana> getAllPersoane() {
        List<Persoana> persoanaList = new ArrayList<>();
        this.persoanaRepository.findAll().forEach(persoanaList::add);
        return persoanaList;
    }

    public List<Nevoie> getAllNevoiByPersoana(String username){
        Persoana persoana = this.findPersoanaByUsername(username);
        List<Nevoie> nevoi = new ArrayList<>();
        for(Nevoie nevoie:this.nevoieRepository.findAll()){
            Persoana omNevoie = this.persoanaRepository.findOne(nevoie.getOm_in_nevoie());
            assert persoana != null;
            if(omNevoie.getOras()==persoana.getOras()&& !Objects.equals(omNevoie.getId(), persoana.getId())){
                nevoi.add(nevoie);
            }
        }
        return nevoi;
    }

    private Persoana findPersoanaByUsername(String username) {
        for(Persoana persoana:this.persoanaRepository.findAll()){
            if(Objects.equals(persoana.getUsername(), username)){
                return persoana;
            }
        }
        return null;
    }

    public Nevoie rezolvaNevoie(Nevoie nevoie, String username) {
        Persoana persoana = this.findPersoanaByUsername(username);
        nevoie.setStatus("Hero already found!");
        assert persoana != null;
        nevoie.setOm_salvator(persoana.getId());
        try {
            this.nevoieRepository.update(nevoie,nevoie);
            return nevoie;
        } catch (EmptyStringException | NegativeNumberException e) {
            throw new RuntimeException(e);
        }
    }

    public Iterable<Nevoie> getFapteByUser(String username) {
        Persoana persoana = this.findPersoanaByUsername(username);
        List<Nevoie> fapte = new ArrayList<>();
        for(Nevoie nevoie:this.nevoieRepository.findAll()){
            assert persoana != null;
            if(Objects.equals(nevoie.getOm_salvator(), persoana.getId())){
                fapte.add(nevoie);
            }
        }
        return fapte;
    }

    public void addNevoie(String username,String titlu, String descriere, LocalDate date) {
        Persoana persoana = this.findPersoanaByUsername(username);
        assert persoana != null;
        Long id = persoana.getId()+date.getDayOfYear()+ date.getDayOfMonth();
        LocalDateTime deadline = LocalDateTime.of(date, LocalTime.parse("23:59:59"));
        Nevoie nevoie = new Nevoie(id,titlu, descriere,deadline,persoana.getId(), null, "Looking for a hero!");
        try {
            this.nevoieRepository.save(nevoie);
        } catch (EmptyStringException | NegativeNumberException e) {
            throw new RuntimeException(e);
        }
    }
}
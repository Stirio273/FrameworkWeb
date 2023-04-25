/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import connexion.*;
import connexion.utilisateur.*;
import generalbdd.BDDObject;
import java.sql.Connection;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import etu1832.framework.ModelView;
import etu1832.framework.annotation.RequestMapping;
import generalbdd.annotation.*;

/**
 *
 * @author ONEF
 */

@Table(nom = "employe")
public class Employe {
    @PrimaryKey
    int id;
    String nom;
    Date dateNaissance;
    double salaire;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public double getSalaire() {
        return salaire;
    }

    public void setSalaire(double salaire) {
        this.salaire = salaire;
    }

    public Employe() {
    }

    public Employe(int id, String nom, Date dateNaissance, double salaire) {
        this.id = id;
        this.nom = nom;
        this.dateNaissance = dateNaissance;
        this.salaire = salaire;
    }

    @RequestMapping(path = "/emp-save")
    public void save() {
        System.out.println("Employe");
        System.out.println("Nom " + this.nom);
        System.out.println("Date de naissance " + this.dateNaissance);
        System.out.println("Salaire " + this.salaire);
    }

    @RequestMapping(path = "/emp-add")
    public ModelView getInsert() {
        ModelView mv = new ModelView("form.jsp");
        return mv;
    }

    @RequestMapping(path = "/emp-modify")
    public ModelView getUpdate() {
        ModelView mv = new ModelView("home.jsp");
        return mv;
    }

    @RequestMapping(path = "/emp-readAll")
    public ModelView getAll() {
        List<Employe> list = new ArrayList<>();
        list.add(new Employe(1, "Rakoto", new Date(), 1000));
        list.add(new Employe(2, "Rabe", new Date(), 1500));
        list.add(new Employe(3, "Rasoa", new Date(), 2450));
        list.add(new Employe(4, "Faniry", new Date(), 800));
        list.add(new Employe(5, "Safidy", new Date(), 1200));
        list.add(new Employe(6, "Ando", new Date(), 3000));
        ModelView mv = new ModelView("all.jsp");
        mv.addItem("listeEmployes", list);
        return mv;
    }

    @RequestMapping(path = "/emp-remove")
    public ModelView getSupprimer() {
        ModelView mv = new ModelView("home.jsp");
        return mv;
    }
}

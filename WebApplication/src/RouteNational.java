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
import etu1832.framework.ModelView;
import etu1832.framework.annotation.RequestMapping;
import generalbdd.annotation.*;

/**
 *
 * @author ONEF
 */

@Table(nom = "route")
public class RouteNational {
    @PrimaryKey
    int id;
    String nom;
    int idType;

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

    public int getIdType() {
        return idType;
    }

    public void setIdType(int idType) {
        this.idType = idType;
    }

    public RouteNational() {
    }

    public RouteNational(int id, String nom, int idType) {
        this.id = id;
        this.nom = nom;
        this.idType = idType;
    }

    @RequestMapping(path = "/rn-add")
    public ModelView getInsert() {
        ModelView mv = new ModelView("add.jsp");
        return mv;
    }

    @RequestMapping(path = "/rn-modify")
    public ModelView getUpdate() {
        ModelView mv = new ModelView("home.jsp");
        return mv;
    }

    @RequestMapping(path = "/rn-readAll")
    public ModelView getAll() {
        List<RouteNational> list = new ArrayList<>();
        list.add(new RouteNational(1, "RN1", 1));
        list.add(new RouteNational(2, "RN2", 2));
        list.add(new RouteNational(3, "RN3", 1));
        list.add(new RouteNational(4, "RN4", 3));
        list.add(new RouteNational(5, "RN5", 2));
        list.add(new RouteNational(6, "RN6", 1));
        ModelView mv = new ModelView("all.jsp");
        mv.addItem("listeRN", list);
        return mv;
    }

    @RequestMapping(path = "/rn-remove")
    public ModelView getSupprimer() {
        ModelView mv = new ModelView("home.jsp");
        return mv;
    }
}

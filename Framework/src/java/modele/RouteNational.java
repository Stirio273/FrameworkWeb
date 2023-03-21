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

import etu1832.framework.annotation.RequestMapping;
import generalbdd.annotation.*;

/**
 *
 * @author ONEF
 */
@RequestMapping(path = "/rn")
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

    public RouteNational(int id, String nom, int idType) {
        this.id = id;
        this.nom = nom;
        this.idType = idType;
    }

    @RequestMapping(path = "/add")
    public void insert(Connection con) throws Exception {
        boolean isNewConnexion = false;
        if (con == null) {
            con = new Connect(User.nom, User.password, User.base).getConnectPostgre();
            isNewConnexion = true;
        }
        BDDObject dao = new BDDObject();
        dao.insert(this, con, null);
        if (isNewConnexion == true) {
            con.commit();
            con.close();
        }
    }

    @RequestMapping(path = "/modify")
    public void update(Connection con, String[] attributs) throws Exception {
        boolean isNewConnexion = false;
        if (con == null) {
            con = new Connect(User.nom, User.password, User.base).getConnectPostgre();
            isNewConnexion = true;
        }
        BDDObject dao = new BDDObject();
        dao.update(this, attributs, null, con, null);
        if (isNewConnexion == true) {
            con.commit();
            con.close();
        }
    }

    @RequestMapping(path = "/read")
    public RouteNational[] getAllRoutes(Connection con) throws Exception {
        boolean isNewConnexion = false;
        if (con == null) {
            con = new Connect(User.nom, User.password, User.base).getConnectPostgre();
            isNewConnexion = true;
        }
        BDDObject dao = new BDDObject();
        Object[] o = dao.find(this, null, con, null);
        if (isNewConnexion == true) {
            con.commit();
            con.close();
        }
        return Arrays.copyOf(o, o.length, RouteNational[].class);
    }

    @RequestMapping(path = "/remove")
    public void supprimer(Connection con) throws Exception {
        boolean isNewConnexion = false;
        if (con == null) {
            con = new Connect(User.nom, User.password, User.base).getConnectPostgre();
            isNewConnexion = true;
        }
        BDDObject dao = new BDDObject();
        dao.delete(this, null, con, null);
        if (isNewConnexion == true) {
            con.commit();
            con.close();
        }
    }
}

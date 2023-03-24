/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import etu1832.framework.annotation.RequestMapping;
/**
 *
 * @author itu
 */
public class Emp {
    int id;
    String nom;

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
    
    @RequestMapping(path="/emp-all")
    public void getAll(){
    }
    
    @RequestMapping(path="/emp-add")
    public void insert(){}
    
    @RequestMapping(path="/emp-change")
    public void modify(){}
    
    @RequestMapping(path="/emp-remove")
    public void delete(){}
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etu1832.framework;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

/**
 *
 * @author itu
 */
public class ModelView {
    String vue;
    HashMap<String, Object> data;
    HashMap<String, Object> session;
    boolean JSON;
    boolean invalidateSession;
    List<String> removeSession;

    public String getVue() {
        return vue;
    }

    public void setVue(String vue) {
        this.vue = vue;
    }

    public HashMap<String, Object> getData() {
        return data;
    }

    public void setData(HashMap<String, Object> data) {
        this.data = data;
    }

    public HashMap<String, Object> getSession() {
        return session;
    }

    public void setSession(HashMap<String, Object> session) {
        this.session = session;
    }

    public boolean isJSON() {
        return JSON;
    }

    public void setJSON(boolean jSON) {
        JSON = jSON;
    }

    public boolean isInvalidateSession() {
        return invalidateSession;
    }

    public void setInvalidateSession(boolean invalidateSession) {
        this.invalidateSession = invalidateSession;
    }

    public List<String> getRemoveSession() {
        return removeSession;
    }

    public void setRemoveSession(List<String> removeSession) {
        this.removeSession = removeSession;
    }

    public ModelView(String vue) {
        this.vue = vue;
        this.data = new HashMap<>();
        this.session = new HashMap<>();
        this.removeSession = new ArrayList<String>();
    }

    public ModelView(String vue, HashMap<String, Object> data) {
        this.vue = vue;
        this.data = data;
    }

    public void addItemData(String key, Object value) {
        this.data.put(key, value);
    }

    public void addItemSession(String key, Object value) {
        this.session.put(key, value);
    }
}

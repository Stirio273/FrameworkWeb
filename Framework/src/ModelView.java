/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etu1832.framework;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author itu
 */
public class ModelView {
    String vue;
    HashMap<String, Object> data;

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

    public ModelView(String vue) {
        this.vue = vue;
        this.data = new HashMap<>();
    }

    public ModelView(String vue, HashMap<String, Object> data) {
        this.vue = vue;
        this.data = data;
    }

    public void addItem(String key, Object value) {
        this.data.put(key, value);
    }
}

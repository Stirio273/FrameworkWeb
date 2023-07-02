package etu1832.framework;

import java.util.HashMap;
import java.util.Map;

public class Model {
    HashMap<String, Object> fromHttpSession;

    public HashMap<String, Object> getFromHttpSession() {
        return fromHttpSession;
    }

    public void setFromHttpSession(HashMap<String, Object> fromHttpSession) {
        this.fromHttpSession = fromHttpSession;
    }

    public Model() {
        this.fromHttpSession = new HashMap<String, Object>();
    }

    public Model(HashMap<String, Object> fromHttpSession) {
        this.fromHttpSession = fromHttpSession;
    }

    public void addItem(String key, Object value) {
        this.fromHttpSession.put(key, value);
    }
}

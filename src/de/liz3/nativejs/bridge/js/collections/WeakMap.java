package de.liz3.nativejs.bridge.js.collections;

import java.util.HashMap;

/**
 * Created by liz3 on 01.08.17.
 */
public class WeakMap {

    private HashMap<Object, Object> wm = new HashMap<>();


    public boolean has(String key) {
        return wm.containsKey(key);
    }
    public void delete(String key) {
        if(has(key)) wm.remove(key);
    }
    public Object get(String key) {
        return wm.get(key);
    }
    public void set(Object key, Object value) {
        wm.put(key, value);
    }
}

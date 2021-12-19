package az.rock.util;


import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class BoostMap<K,V> extends AbstractMap<K,V> implements Map<K,V> {
    Node<K,V>[] map;

    private record Node<K,V>(K key,V value){};



    @Override
    public Set<Entry<K, V>> entrySet() {
        return null;
    }


}

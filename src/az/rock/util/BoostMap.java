package az.rock.util;


import java.util.*;

public final class BoostMap<K,V> extends AbstractMap<K,V> implements Map<K,V> {

    private final int initialCapacity  = 1000;

    private int lastFreeIndex = 0;

    //Keyleri burada tutacam
    private final Set<KeyNode<K>> keySet = new HashSet<>();



    //Deyerleri burada tutacam
    @SuppressWarnings("unchecked")
    private final V[] values = (V[]) new Object[initialCapacity];

    private class KeyNode<K>{
        int index;
        K key;

        public KeyNode(K key) {
            this.key = key;
            index = lastFreeIndex;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            KeyNode<?> keyNode = (KeyNode<?>) o;
            return Objects.equals(key, keyNode.key);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key);
        }
    }


    @Override
    public V put(K key, V value) {
        if(keySet.add(new KeyNode<>(key))){
            values[lastFreeIndex] = value;
            lastFreeIndex++;
            return null;
        }else {
            return value;
        }
    }

    @Override
    public V get(Object key) {
        for (KeyNode<K> keyNode : keySet) {
            if (keyNode.key == key)
                return values[keyNode.index];
        }
        return null;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return null;
    }


    @Override
    public String toString() {
        return "BoostMap{" +
                "keySet=" + keySet +
                ", values=" + Arrays.toString(values) +
                "}\n";
    }
}

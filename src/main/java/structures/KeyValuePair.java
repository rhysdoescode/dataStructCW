package structures;

public class KeyValuePair<K, V> {
    private K key;
    private V value;
    
    public KeyValuePair(K nKey, V nValue) {
        this.key = nKey;
        this.value = nValue;
    }

    public V getValue() {
        return this.value;
    }

    public K getKey() {
        return this.key;
    }

    public boolean setValue(V newValue) {
        this.value = newValue;
        return true;
    }

    

}

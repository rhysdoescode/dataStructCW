package structures;

public class KeyValuePair<V> {
    private String key;
    private V value;
    
    public KeyValuePair(String nKey, V nValue) {
        this.key = nKey;
        this.value = nValue;
    }

    public V getValue() {
        return this.value;
    }

    public String getKey() {
        return this.key;
    }

    public boolean setValue(V newValue) {
        this.value = newValue;
        return true;
    }

    

}

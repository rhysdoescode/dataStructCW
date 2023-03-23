package structures;

import java.util.Calendar;

public class SimpleMap  {
    private KeyValuePair[] map;
    private int capacity;
    private int size;

    public SimpleMap(int capacity){
        this.capacity = capacity;
        this.size = 0;
        this.map = new KeyValuePair [capacity];
    }

    public void add (KeyValuePair pair) {
        map[size] = pair;
        size++;
    }

    /**
     * Gets all the key value pairs in the map, formats them into a readable string,
     * and returns the resulting string
     * @return a string of all the key/value pairs in the list
     */
    public String getPairs(){
        String pairsString = "";

        for (int i = 0; i < size; i++){
            pairsString += (" (Key: " + map[i].getKey() + " | Value: " + map[i].getValue() + ") ");
        }

        return pairsString;
    }

    /**
     * Linear search to find a given property, by looking for the key in the
     * map which matches the 'key' parameter. Returns the value associated with the key
     * @param key the key of the property being looked for
     * @return the value associated with the key
     */
    public Object getPropertyByKey(String key){
        int indexOfProperty = -1;
        for (int i = 0; i < size; i++){
            if (map[i].getKey() == key){
                indexOfProperty = i;
                break;
            }
        }

        if (indexOfProperty == -1){
            return null;
        }
        else{
            return map[indexOfProperty].getValue();
        }
    }

    public boolean setPropertyByKey(String key, Object value){
        int indexOfProperty = -1;
        for (int i = 0; i < size; i++){
            if (map[i].getKey() == key){
                indexOfProperty = i;
                break;
            }
        }

        if (indexOfProperty == -1){
            return false;
        }
        else{
            map[indexOfProperty].setValue(value);
            return true;
        }
    }

    

    public Calendar getKey() {
        return null;
    }


    
    
}

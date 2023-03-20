package structures;

import javax.xml.crypto.dsig.keyinfo.KeyValue;

/**
 * Hash table used for the movie data. Each cell in the table is indexed
 * by using the hash of the movies id, and contains an array of key/value pairs of the movies
 * properties. 
 * 
 */
public class HashTable {
    private LinkedList<SimpleMap>[] table;
     
    private int bucketsFilled;
    private int capacity;
    private int size;


    public HashTable(){
        this.bucketsFilled = 0;
        this.size = 0;
        this.capacity = 2;
        table = (LinkedList<SimpleMap>[]) new LinkedList[this.capacity];
    }
    
    /**
     * Adds a new list key/value pairs of a given movie's attributes
     * to the hash table. 
     * @param key The MovieID
     * @param attributeList An array of key/value pairs of the movies attributes
     */
    public void add(SimpleMap attributeList) {
        int hashCode = hash((Integer) attributeList.getPropertyByKey("id"));
        //System.out.println("Hash code: "+ hashCode);
        if (bucketsFilled > capacity * 0.7){
            growHashTable();
        }

        LinkedList<SimpleMap> tableRow = table[hashCode];

        if (tableRow == null) {
            LinkedListElement<SimpleMap> newHead = new LinkedListElement<>(attributeList);
            table[hashCode] = new LinkedList<SimpleMap>(newHead);
            bucketsFilled++;
        }
        else {
            LinkedListElement<SimpleMap> newHead = new LinkedListElement<>(attributeList);
            newHead.setNext(tableRow.getHead());
            table[hashCode].setHead(newHead);
        }

        size++;
    }

    /**
     * Makes the table bigger, retaining all elements but making its size
     * the smallest possible prime number, while still being bigger
     * than the previous size.
     */
    public void growHashTable(){
        boolean nextPrimeFound = false;
        int oldCapacity = capacity;
        capacity = (capacity * 2) - 1;
        // Get next biggest prime number
        while (!nextPrimeFound){
            nextPrimeFound = true;
            capacity++;
           
            for (int i = 2; i < capacity; i++){
                if(capacity % i == 0){
                    nextPrimeFound = false;
                    break;
                }
            }   
        }
        
        // Make new array have the capacity of the next biggest prime number
        LinkedList<SimpleMap>[] oldTable = table;
        table = (LinkedList<SimpleMap>[]) new LinkedList[capacity];

        // Copy contents of old array into the new one
        bucketsFilled = 0;
        size = 0;
        for (int i = 0; i < oldCapacity; i++){
            if (oldTable[i] != null){
                LinkedListElement<SimpleMap> elementToAdd = oldTable[i].getHead();
                while (elementToAdd != null){
                    add(elementToAdd.getValue());
                    elementToAdd = elementToAdd.getNext();
                }
            }
        }
    }

    /**
     * Returns a simple hash of the value given.
     * @param value value to hash
     * @return the hashed value
     */
    public int hash(int value){
        return value % capacity;
    }

    public Object getPropertyByID(int id, String propertyTitle){
        int hashCode = hash(id);
        Object propertyValue = null;
        LinkedListElement<SimpleMap> elementToCheck = table[hashCode].getHead();
        
        while (elementToCheck != null){
            SimpleMap map = elementToCheck.getValue();
            if((Integer) map.getPropertyByKey("id") == id){
                propertyValue = map.getPropertyByKey(propertyTitle);
                break;
            }
            elementToCheck = elementToCheck.getNext();
        }

        
        return propertyValue;
        
    }

    public void printFirstElementsOfEachRowInTable(){
       //System.out.println("Trying to print");

        for (int i = 0; i < capacity; i++){
            if (table[i] != null){
                System.out.println("Row: " + i + " | Head Element: "+ table[i].getHead().getValue().getPairs());
            }
            
        }
    
    }

    /**
     * Getter that returns how many maps are in
     * the hash map
     * @return the amount of maps stored in the hash map
     */
    public int getSize(){
        return size;
    }

}

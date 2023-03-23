package structures;

public class SimpleArrayList {
   private Object[] array;
   private int size;
   private int capacity;

   public SimpleArrayList(){
      this.array = new Object[10];
      this.size = 0;
      this.capacity = 10;
   }

   /**
    * Add new value to simple array list. 
    * @param value Value to add to the array list
    */
   public void add(Object value){
      if(size < capacity){
         array[size] = value;
         size++;
      }
      else{
         Object[] newArray = new Object[capacity + 1];
         for (int i = 0; i < capacity; i++){
            newArray[i] = array[i];
         }
      }

   }
   
   
}

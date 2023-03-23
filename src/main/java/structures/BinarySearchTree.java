package structures;

import javax.xml.crypto.dsig.keyinfo.KeyValue;

import java.util.Arrays;
import java.util.Calendar;

/**
 * Binary Tree that sorts key/value pairs by the key.
 */
public class BinarySearchTree <K extends Comparable<K>, V, X extends Number>  {
   BSTNode<K, SimpleMap> root;
   private int size;

   public BinarySearchTree(){
      root = null;
      size = 0;
   }
   
   /**
    * Adds a new node into the tree. If the root is empty, set the root as the new
    * value, otherwise place it in the correct location in the tree, to preserve every node
    * being ordered by key.
    * @param newValue the value to add to the tree
    */
   public void add(KeyValuePair<K, SimpleMap> newValue){

      if (root == null){
         System.out.println("root about to not be null");
         root = new BSTNode(newValue);
      }
      else{
         addIntoSubtree(root, newValue);
      }
      size++;
   }

   /**
    * Recursive function that places a given node into the correct 
    * subtree from the current node. Traverses the tree, moving left when the value-to-be-added
    * smaller than the value of the node being traversed on, and moving right when the value-to-be-added is
    * bigger or equal to the value of the node being traversed on.
    *
    * When the tree can't be traversed any more following these rules because the left/right
    * subtree is null, make the node the first value in the subtree.
    *
   * @param startOfSubtree The node the new value will be compared to 
    * @param newValue The value of the node to be added to the subtree.
    */
    private void addIntoSubtree(BSTNode<K, SimpleMap> startOfSubtree, KeyValuePair<K, SimpleMap> newValue){
      K newKey = newValue.getKey();
      K currentKey = startOfSubtree.getValue().getKey();

      // If new key is equal or above the old key, needs to 
      // be added in the right subtree. Otherwise, it needs to
      // be addded in the left subtree
      if(newKey.compareTo(currentKey) >= 0){
         if (startOfSubtree.getLeftChild() == null){
            startOfSubtree.setLeftChild(new BSTNode(newValue));
         }
         else{
            addIntoSubtree(startOfSubtree.getLeftChild(), newValue);
         }
      }
      else{
         if (startOfSubtree.getRightChild() == null){
            startOfSubtree.setRightChild(new BSTNode(newValue));
         }
         else{
            addIntoSubtree(startOfSubtree.getRightChild(), newValue);
         }
      }
   };

   public void printNodesInOrder(){
      inOrder(root);
   }


   public int[] getIntPropertiesInRange(K min, K max, String propertyKey){
      return processGetIntPropertiesInRange((BSTNode<K, SimpleMap>) root, min, max, 0, propertyKey);
   }

   public float[] getFloatPropertiesInRange(K min, K max, String propertyKey, KeyValuePair<K, V> target){
      return processGetFloatPropertiesInRange(root, min, max, propertyKey, target);
   }

   private int[] processGetIntPropertiesInRange(BSTNode<K, SimpleMap> currentNode, K min, K max, int count, String propertyKey){
      int[] array = new int[size];

      K currentNodeKey = currentNode.getValue().getKey();

      if (currentNodeKey.compareTo(min) >= 0 && currentNodeKey.compareTo(max) <= 0){
         array[count] = (int) currentNode.getValue().getValue().getPropertyByKey(propertyKey);
         count++;
         return combineIntArrays(processGetIntPropertiesInRange(currentNode.getLeftChild(), min, max, count, propertyKey),
         processGetIntPropertiesInRange(currentNode.getRightChild(), min, max, count, propertyKey), array);
      }
      else if (currentNodeKey.compareTo(min) < 0){
         return processGetIntPropertiesInRange(currentNode.getLeftChild(), min, max, count, propertyKey);
      }
      else{
         return processGetIntPropertiesInRange(currentNode.getRightChild(), min, max, count, propertyKey);
      }
   }


   
   /**
    * Recursive function which, given an intitial node, returns an array of float properties (e.g. rating of a movie)
    * of nodes with a key between two values, by recursively traversing the tree.
    * @param currentNode The current node the function is inspecting
    * @param min The mininum key value the node can have, for the float property to be added to the array
    * @param max The maximum key value the node can have, for the float property to be added to the array
    * @param propertyKey
    * @return
    */
    public float[] processGetFloatPropertiesInRange(BSTNode<K, SimpleMap> currentNode, K min, K max, String propertyKey, KeyValuePair<K, V> target){
      float[] array = new float[size];

      for (int i = 0; i < size; i++){
         array[i] = (float) -1.0;
      }

      if (currentNode == null){
         System.out.println("Current node: null");
         return new float[0];
      }

      K currentNodeKey = currentNode.getValue().getKey();

      if (currentNodeKey.compareTo(min) > 0 && currentNodeKey.compareTo(max) < 0){
         SimpleMap currentMap = currentNode.getValue().getValue();
         System.out.println(target.getKey());
         System.out.println((target.getValue()));
         System.out.println(currentMap.getPropertyByKey((String) target.getKey()));

         if (target == null || (target.getValue() == currentMap.getPropertyByKey((String) target.getKey()))){
            System.out.println(currentNode.getValue().getValue().getPairs());
            array[0] = (float) currentNode.getValue().getValue().getPropertyByKey(propertyKey);
            System.out.println("Found node in range!");
            float[][] floatArrays = {processGetFloatPropertiesInRange(currentNode.getLeftChild(), min, max, propertyKey, target),
               processGetFloatPropertiesInRange(currentNode.getRightChild(), min, max, propertyKey, target), array};
            return combineFloatArrays(floatArrays);
         }
      }
      
         float[][] floatArrays = {processGetFloatPropertiesInRange(currentNode.getLeftChild(), min, max, propertyKey, target),
            processGetFloatPropertiesInRange(currentNode.getRightChild(), min, max, propertyKey, target), array};
            return combineFloatArrays(floatArrays);
      
      
      
   }

   public int[] combineIntArrays(int[] arrayOne, int[] arrayTwo, int[] arrayThree){
     int newLength = arrayOne.length + arrayTwo.length + arrayThree.length;

     int[] newArray = new int[newLength];

     for (int i = 0; i < arrayOne.length; i++){
      newArray[i] = arrayOne[i];
     }
     for(int j = 0; j < arrayTwo.length; j++){
      newArray[j + (arrayOne.length)] = arrayTwo[j];
     }
     for (int k = 0; k < arrayThree.length; k++){
      newArray[k + (arrayOne.length + arrayTwo.length)] = arrayThree[k];
     }

     return newArray;
   }

   

   /**
    * Function that combines arrays of floats, removing any
    * -1.0 values from the final result.
    * @param arrays An array of float arrays to combine
    * @return The combined array of floats
    */
   public float[] combineFloatArrays(float[][] arrays){
      int newLength = 0;

      for (int i = 0; i < arrays.length; i++){
         newLength += arrays[i].length;
      }

      float[] newArray = new float[newLength];
      for (int i = 0; i < newLength; i++){
         newArray[i] = (float) -1.0;
      }
      int newArrayIndex = 0;

      for (int i = 0; i < arrays.length; i++ ){
         if (arrays[i].length != 0){
            for (int j = 0; j < arrays[i].length; j++){
               if (arrays[i][j] != -1){
                  newArray[newArrayIndex] = arrays[i][j];
                  newArrayIndex++;
               }
            }
         }  
      }

      float[] finalArray = new float[newArrayIndex];

      for (int i = 0; i < newArrayIndex; i++){
         finalArray[i] = newArray[i];
      }
      
      return finalArray;
    }

   /**
    * Print all nodes from a given node in order.
    * @param node
    */ 
   public void inOrder(BSTNode<K, SimpleMap> node){
      if (node != null){
         inOrder(node.getRightChild());
         System.out.println("Printing all nodes in tree: " + ((Calendar) node.getValue().getKey()).getTime());
         inOrder(node.getLeftChild());

         //System.out.println("ROOT is" + ((Calendar) root.getValue().getKey()).getTime());
         if (root.getRightChild() == null){
            System.out.println("Right xhild null");
         }
         else{
            System.out.println("RIGHT CHILD!!! " + ((Calendar) root.getRightChild().getValue().getKey()).getTime());
         }
         if (root.getLeftChild() == null){
            System.out.println("left child null");
         }
         else{
            System.out.println("Left child!!! " + ((Calendar) root.getLeftChild().getValue().getKey()).getTime());
         }
         
         
      }
   }

   /**
    * Getter for how many items are in the tree
    * @return how many nodes are in the tree
    */
   public int getSize(){
      return size;

   }
   
}

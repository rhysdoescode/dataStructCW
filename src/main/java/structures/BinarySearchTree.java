package structures;

import javax.xml.crypto.dsig.keyinfo.KeyValue;

/**
 * Binary Tree that sorts key/value pairs by the key.
 */
public class BinarySearchTree <K extends Comparable<K>, V>  {
   BSTNode<K, V> root;

   public BinarySearchTree(){
      root = null;
   }
   
   /**
    * Adds a new node into the tree. If the root is empty, set the root as the new
    * value, otherwise place it in the correct location in the tree, to preserve every node
    * being ordered by key.
    * @param newValue the value to add to the tree
    */
   public void add(KeyValuePair<K, V> newValue){
      if (root == null){
         root = new BSTNode(newValue);
      }
      else{
         addIntoSubtree(root, newValue);
      }
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
   private void addIntoSubtree(BSTNode<K, V> startOfSubtree, KeyValuePair<K, V> newValue){
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
   }

   public void printNodesInOrder(){
      inOrder(root);
   }

   public void inOrder(BSTNode<K, V> node){
      if (node != null){
         inOrder(node.getRightChild());
         System.out.println("Node: " + node.getValue().getKey());
         inOrder(node.getLeftChild());
      }
   }

   
}

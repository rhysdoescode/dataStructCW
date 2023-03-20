package structures;

/** Key/value pair node in a binary tree */
public class BSTNode <K, V> {
   private KeyValuePair<K, V> value;
   private BSTNode<K, V> leftChild;
   private BSTNode<K, V> rightChild;

   public BSTNode(KeyValuePair<K, V> newValue){
      this.value = newValue;
      this.leftChild = null;
      this.rightChild = null;
   }

   /**
    * Getter for the left child of the current node.
    * If it doesn't exist, it will return null.
    * @return the left child of the current node
    */
   public BSTNode<K, V> getLeftChild(){
      return leftChild;
   }

   /**
    * Setter for the left child of the current node
   */
   public void setLeftChild(BSTNode<K, V> newLeftChild){
      leftChild = newLeftChild;
   }

   /**
    * Getter for the right child of the current node.
    * If it doesn't exist, it will return null.
    * @return the right child of the current node
    */
   public BSTNode<K, V> getRightChild(){
      return rightChild;
   }

    /**
    * Setter for the right child of the current node
   */
   public void setRightChild(BSTNode<K, V> newRightChild){
      rightChild = newRightChild;
   }

   /**
    * Getter for the value stored in this node.
    * @return the value stored in this node of the tree
    */
   public KeyValuePair<K, V> getValue(){
      return value;
   }

    /**
    * Setter for the value stored in this node.
    */
   public void setValue(KeyValuePair<K, V> newValue){
      value = newValue;
   }

   
}

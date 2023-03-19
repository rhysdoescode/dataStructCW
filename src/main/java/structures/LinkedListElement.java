package structures;
/**
 * Data structure for singly linked list.
 */
public class LinkedListElement<E> {
    private LinkedListElement<E> next;
    private E value;

    public LinkedListElement(E value){
        this.value = value;
    }

    /**
     * Getter for the value property.
     * @return the value this linkedListElement holds
     */
    public E getValue(){
        return value;
    }

    /**
     * Setter for the next property - i.e. sets the linkedListItem
     * that this linkedListItem is linked to
     * @param newNext The item that this linkedList will be linked to
     */
    public void setNext(LinkedListElement<E> newNext){
        next = newNext;
    }

    /**
     * Getter for the element that this element is connected to
     * @return the item this linkedListElement is connected to
     */
    public LinkedListElement<E> getNext(){
        return next;
    }
}

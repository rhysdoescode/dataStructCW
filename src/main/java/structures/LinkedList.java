package structures;
/**
 * Basic linked list
 */
public class LinkedList<E> {
    private LinkedListElement<E> head;
    public LinkedList(LinkedListElement<E> head){
        this.head = head;
    }

    /**
     * Getter for the head of the linked list
     * @return the head of the linked list
     */
    public LinkedListElement<E> getHead(){
        return head;
    }

    /**
     * Setter for the head of the linked list
     * @param newHead The new head of the linked list
     */
    public void setHead(LinkedListElement<E> newHead){
        head = newHead;
    }

    public void traverse(){
        LinkedListElement<E> element  = head;
        while(element.getNext() != null){
            System.out.println(element.getNext());
        }
    }
    
}

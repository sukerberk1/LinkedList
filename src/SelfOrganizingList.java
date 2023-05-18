import java.util.Iterator;
import java.util.NoSuchElementException;

public class SelfOrganizingList<T> implements Iterable<T> {

    private Node<T> head = null;
    private Node<T> tail = null;
    private int size = 0;


    public void add(T element){
        var newNode = new Node<>(element);
        size++;
        if (head == null) {
            head = tail = newNode;
            return;
        }
        tail.next = newNode;
        tail = newNode;
    }

    public T get(int i) {
        if (i < 0 || i >= size) {
            throw new NoSuchElementException(String.format("Illegal index (%d) in SelfOrganizingList.get()", i));
        }
        var current = head;
        for (var j = 0; j < i; j++) {
            current = current.next;
        }
        current.numberOfUses++;

        reorderNode( current, findPrev(current) );
        return current.element;
    }

    /**
     * Przenieś nodeToMove bliżej początku listu, jeśli jego licznik jest większy niż licznik w węzłach poprzedzających.
     * @param nodeToMove węzeł do ewentualnego przeniesienia.
     * @param prevOfNodeToMove węzeł poprzedzający węzeł do przeniesienia.
     */
    private void reorderNode(Node<T> nodeToMove, Node<T> prevOfNodeToMove) {
        Node<T> nodeA = head;
        while (nodeA != nodeToMove){
            if (nodeToMove.numberOfUses > nodeA.numberOfUses && nodeA != head) {
                Node<T> nodeAprev = findPrev(nodeA);
                // first, we take node to move and "cut it out" of the list
                prevOfNodeToMove.next = nodeToMove.next;
                // then, we pin it to right nodes
                // 1. set the pointer of nodeToMove to the found node with lesser usage
                nodeToMove.next = nodeA;
                // 2. set the pointer of previous node of the found one to the nodeToMove
                nodeAprev.next = nodeToMove;

                break;
            } else if (nodeToMove.numberOfUses > nodeA.numberOfUses){
                // Handle the stance of moving the node to the very beginning explicitly, similarly as the above
                prevOfNodeToMove.next = nodeToMove.next;
                this.head = nodeToMove;
                nodeToMove.next = nodeA;

                break;
            }
            nodeA = nodeA.next;
        }
    }

    public int size() {
        return size;
    }

    /** Function purpose  is to return previous node of node passed int the parameter. If searching for the prev
     * node fails, it returns head instead. */
    public Node<T> findPrev(Node<T>node) {
        Node<T> prevNode;
        var currentNode = head;
        for (var j = 0; j < this.size; j++) {
            prevNode = currentNode;
            currentNode = currentNode.next;
            if (currentNode == node) {
                return prevNode;
            }
        }
        return head;
    }

    @Override
    public String toString() {
        var toReturn = new StringBuilder("(");
        var current = head;
        while (current != null) {
            toReturn.append(current).append(", ");
            current = current.next;
        }
        if (size > 0) {
            var length = toReturn.length();
            toReturn.delete(length - 2, length);
        }
        return toReturn.append(")").toString();
    }

    public Iterator<T> iterator() {
        return new Iterator<>() {
            Node<T> current = head;

            @Override public boolean hasNext() {
                return current != null;
            }

            @Override public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("SelfOrganizingList.iterator() over-iterated");
                }
                T toReturn = current.element;
                current = current.next;
                return toReturn;
            }
        };
    }

}

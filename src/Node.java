public class Node<E> { // mimo że często parametr klasy zagnieżdżonej jest nazywany tak samo jak
    // parameter klasy zewnętrznej, to są to inne klasy parametryzujące, tutaj to widać ewidentnie.
    E element;
    Node<E> next;
    int numberOfUses = 1;
    Node(E element) {
        this.element = element;
    }
    @Override public String toString() {
        return element + "[" + numberOfUses + "]";
    }
}
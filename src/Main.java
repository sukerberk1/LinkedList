
import java.util.Iterator;
import java.util.NoSuchElementException;


public class Main {
    public static void main(String[] args) {
        var list = new SelfOrganizingList<String>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("e");
        list.get(0); list.get(0);
        System.out.format("%s%n", list);
        list.get(4);
        System.out.format("%s%n", list);
        list.get(2);
        System.out.format("%s%n", list);

        list.get(4);
        System.out.format("%s%n", list);
        list.get(3);
        System.out.format("%s%n", list);
        list.get(1);
        System.out.format("%s%n", list);
    }
}
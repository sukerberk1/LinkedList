
import java.util.Iterator;
import java.util.NoSuchElementException;


public class Main {
    public static void main(String[] args) {
        var list = new SelfOrganizingList<String>();
        list.add("a");
        System.out.format("%s%n", list);
        list.add("B");
        System.out.format("%s%n", list);
        list.get(0);
        System.out.format("%s%n", list);
        list.add("c");
        System.out.format("%s%n", list);
        list.get(2);
        System.out.format("%s%n", list);
        list.get(2);
        list.add("f");
        list.add("g");
        list.add("sh");
        list.add("gff");
        System.out.format("%s%n", list);
        list.get(4);
        list.get(4);
        list.get(4);
        list.get(4);
        list.get(4);
        System.out.format("%s%n", list);
    }
}
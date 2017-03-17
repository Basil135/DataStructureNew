import java.util.Iterator;

public class Main {

    public static void main(String... args) {

        ArrayCollection<Integer> arrayCollection = new ArrayCollection<>();

        Iterator iterator = arrayCollection.iterator();

        arrayCollection.add(1);
        arrayCollection.add(2);
        arrayCollection.add(3);

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

    }

}

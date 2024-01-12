package question_3;

public class ArraySwapper {
    public  <E> void swapElements(E[] elements, int firstIndex, int secondIndex) {
        if (elements == null || firstIndex < 0 || firstIndex >= elements.length || secondIndex < 0 || secondIndex >= elements.length) {

            System.out.println("Invalid input: Unable to swap elements.");
            return;
        }

        E temp = elements[firstIndex];
        elements[firstIndex] = elements[secondIndex];
        elements[secondIndex] = temp;
    }
}

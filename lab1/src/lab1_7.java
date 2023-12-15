import java.util.Random;

public class lab1_7 {
    public static void main(String[] args) {

        Random random = new Random();
        int[] arr = new int[20];
        System.out.println("Массив: ");

        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(20);
            System.out.print(arr[i] + " ");
        }

        System.out.print("\nИндексы элементов, которые кратны 3 и 5: ");
        for (int i = 0; i < arr.length; i++) {
            if ((arr[i] % 3 == 0) || (arr[i] % 5 == 0))
                System.out.print(i + " ");
        }
    }
}
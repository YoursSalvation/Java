import java.util.Random;
import java.util.Scanner;

public class lab1_7 {
    public static void main(String[] args) {
        Scanner sca = new Scanner(System.in);
        Random random = new Random();
        System.out.println("Введите размерность массива: ");
        int n = sca.nextInt();
        int[] arr = new int[n];
        System.out.println("Массив: ");

        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(1, 20);
            System.out.print(arr[i] + " ");
        }

        System.out.print("\nИндексы элементов, которые кратны 3 или 5: ");
        for (int i = 0; i < arr.length; i++) {
            if ((arr[i] % 3 == 0) || (arr[i] % 5 == 0))
                System.out.print(i + " ");
        }
    }
}
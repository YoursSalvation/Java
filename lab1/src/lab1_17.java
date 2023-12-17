import java.util.Random;
import java.util.Scanner;

public class lab1_17 {
    public static void main(String[] args) {
        Scanner sca = new Scanner(System.in);
        Random random = new Random();
        System.out.println("Введите размерность массива: ");
        int n = sca.nextInt();
        int[] arr = new int[n];
        System.out.println("Масив: ");

        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(1, 20);
            System.out.print(arr[i] + " ");
        }
        System.out.println("\nВведите x");
        int x = sca.nextInt();
        System.out.print("\nЭлементы, которые являются квадратом " + x + ": ");
        for (int i = 0; i < arr.length; i++) {
            if (x*x == arr[i])
                System.out.print(arr[i] + " ");
        }
    }
}
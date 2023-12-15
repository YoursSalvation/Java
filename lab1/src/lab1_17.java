import java.util.Random;
import java.util.Scanner;

public class lab1_17 {
    public static void main(String[] args) {

        Random random = new Random();
        int[] arr = new int[20];
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите x");
        int x = sc.nextInt();
        System.out.println("Масив: ");

        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(20);
            System.out.print(arr[i] + " ");
        }

        System.out.print("\nЭлементы, которые являются квадратом x: ");
        for (int i = 0; i < arr.length; i++) {
            if (x*x == arr[i])
                System.out.print(arr[i] + " ");
        }
    }
}
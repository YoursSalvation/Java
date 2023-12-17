import java.util.Random;
import java.util.Scanner;

public class lab1_2_17 {
    public static void main(String[] args) {
        Scanner sca = new Scanner(System.in);
        Random random = new Random();
        System.out.println("Введите количество строк в массиве");
        int st = sca.nextInt();
        System.out.println("Введите количество столбцов в массиве");
        int sb = sca.nextInt();
        int[][] arr = new int[st][sb];
        System.out.println("Массив: ");

        for (int i = 0; i < arr.length; i++) {
            System.out.println();
            for (int j = 0; j < arr.length; j++) {
                arr[i][j] = random.nextInt(1, 10);
                System.out.print(arr[i][j] + " ");
            }
        }

        int[] sumstr = new int[arr.length];
        int[] sumstl = new int[arr.length];
        int str = 0;
        int stl = 0;
        while (str < arr.length) {
            for (int j = 0; j < arr.length; j++) {
                sumstr[str] += arr[str][j];
            }
            str++;
        }
        while (stl < arr.length) {
            for (int i = 0; i < arr.length; i++) {
                sumstl[stl] += arr[i][stl];
            }
            stl++;
        }
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                if (sumstr[i] == sumstl[j]) {
                    System.out.println("\nСумма строки " + (i+1) + " равна сумме столбца " + (j+1));
                }
            }
        }
    }
}
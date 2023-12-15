import java.util.Random;

public class lab1_2_17 {
    public static void main(String[] args) {

        Random random = new Random();
        int[][] arr = new int[3][3];
        System.out.println("Массив: ");

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                arr[i][j] = random.nextInt(1, 10);
                System.out.print(arr[i][j] + " ");
                if (j == 2)
                    System.out.println("");
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
                    System.out.println("Сумма строки " + i + " равна сумме столбца " + j);
                }
            }
        }
    }
}

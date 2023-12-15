import java.util.Random;

public class lab1_2_7 {
    public static void main(String[] args) {

        Random random = new Random();
        int[][] arr = new int[3][3];
        int x = 0, y = 0;
        System.out.println("Массив: ");

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                arr[i][j] = random.nextInt(1, 10);
                System.out.print(arr[i][j] + " ");
                if (j == 2)
                    System.out.println("");
                if (arr[i][j] % 2 == 0)
                    x += 1;
                else
                    y += 1;
            }
        }

        int[] chet = new int[x];
        int[] nechet = new int[y];
        int k = 0;
        int n = 0;

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                if (arr[i][j] % 2 == 0) {
                    chet[k] = arr[i][j];
                    k += 1;
                }
                else {
                    nechet[n] = arr[i][j];
                    n += 1;
                }
            }
        }

        System.out.print("Массив четных: ");
        for (int i = 0; i < x; i++) {
            System.out.print(chet[i] + " ");
        }
        System.out.print("\nМассив нечетных: ");
        for (int i = 0; i < y; i++) {
            System.out.print(nechet[i] + " ");
        }
    }
}
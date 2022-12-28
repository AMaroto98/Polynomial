import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        // Arrays de tamaño 10, pero pueden ser de cualquier tamaño dependiendo del polinomio.

        int[] first = new int[10];
        int[] second = new int[10];

        Scanner scan = new Scanner(System.in);

        int j = 0;

        for (int i = 0; i < 10; i++) {

            first[i] = scan.nextInt();
            first[i + 1] = scan.nextInt();
        }

        j = 0;

        for (int i = 0; i < 10; i++) {

            second[i] = scan.nextInt();
            second[i + 1] = scan.nextInt();
        }

        System.out.println();
    }
}

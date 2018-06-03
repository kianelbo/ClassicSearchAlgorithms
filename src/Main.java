import algorithms.*;
import problems.EightPuzzle;
import problems.RescuerRobot;
import problems.Rubik;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = null;

//        int[][] matrix = new int[3][3];
//        try {
//            input = new Scanner(new File("EightPuzzleInput.txt"));
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//
//        while (input.hasNext())
//            for (int i = 0; i < 3; i++) for (int j = 0; j < 3; j++)
//                matrix[i][j] = input.nextInt();
//
//        new Bidirectional(false).solve(new EightPuzzle(matrix));


        try {
            input = new Scanner(new File("RescuerRobotInput.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int n = input.nextInt();
        int m = input.nextInt();
        int size = input.nextInt();
        ArrayList<int[]> walls = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            int[] ia = new int[4];
            ia[0] = input.nextInt() - 1;
            ia[1] = input.nextInt() - 1;
            ia[2] = input.nextInt() - 1;
            ia[3] = input.nextInt() - 1;
            walls.add(ia);
        }

        new Bidirectional(true).solve(new RescuerRobot(n, m, walls));


//        try {
//            input = new Scanner(new File("RubikInput.txt"));
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        char[] colors = input.nextLine().replaceAll(" ", "").toCharArray();
//        new DFS(false, 14).solve(new Rubik(colors));
    }
}

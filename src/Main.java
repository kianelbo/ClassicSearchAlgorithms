import algorithms.*;
import problems.BaseProblem;
import problems.EightPuzzle;
import problems.RescuerRobot;
import problems.Rubik;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Choose a problem:");
        System.out.println("1. Rescuer Robot");
        System.out.println("2. Eight Puzzle");
        System.out.println("3. Rubik\n");
        Scanner scanner = new Scanner(System.in);

        BaseProblem problem = null;

        switch (scanner.nextInt()) {
            case 1:
                problem = new RescuerRobot("RescuerRobotInput.txt");
                break;
            case 2:
                problem = new EightPuzzle("EightPuzzleInput.txt");
                break;
            case 3:
                problem = new Rubik("RubikInput.txt");
                break;
            default:
                System.out.println("Invalid input");
                System.exit(-1);
        }

        System.out.println("Choose an algorithm: (add \"-g\" for graph search or \"-t\" for tree search)");
        System.out.println("1. BFS");
        System.out.println("2. DFS");
        System.out.println("3. UCS");
        System.out.println("4. Bidirectional");
        System.out.println("5. AStar");

        BaseAlgorithm algorithm = null;
        int algorithmNuber = scanner.nextInt();
        boolean isGraphSearch = scanner.next().equalsIgnoreCase("-g");
        switch (algorithmNuber) {
            case 1:
                algorithm = new BFS(isGraphSearch);
                break;
            case 2:
                System.out.println("Choose limit: (-1 for without limiting and 0 for gradual limit increase)");
                algorithm = new DFS(isGraphSearch, scanner.nextInt());
                break;
            case 3:
                algorithm = new UCS(isGraphSearch);
                break;
            case 4:
                algorithm = new Bidirectional(isGraphSearch);
                break;
            case 5:
                algorithm = new AStar(isGraphSearch);
                break;
            default:
                System.out.println("Invalid input");
        }

        algorithm.solve(problem);
    }
}

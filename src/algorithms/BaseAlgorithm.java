package algorithms;

import problems.BaseProblem;

import java.util.ArrayList;

public abstract class BaseAlgorithm {
    ArrayList<Action> solution;
    ArrayList<BaseState> expanded;
    int visited;
    int maxMemory;
    boolean isGraphSearch;

    public void solve(BaseProblem problem) {
        startDeclare();
        maxMemory = 0;
        if (search(problem)) {
            System.out.println("Goal Reached :)");
            System.out.println("Path found: ");
            for (Action a : solution) System.out.print(" -> " + a.getLabel());

            System.out.println("\nTotal cost of found path: " + getTotalCost());
            System.out.println("Number of expanded nodes: " + expanded.size());
            System.out.println("Number of visited nodes: " + visited);
            System.out.println("Maximum memory use: " + maxMemory);
        }
        else System.out.println("Goal not found :(");
    }

    abstract boolean search(BaseProblem problem);

    abstract int getTotalCost();

    private void startDeclare() {
        System.out.println("Solving with " + getClass().getName());
    }
}

package problems;

import algorithms.Action;
import algorithms.BaseState;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class EightPuzzle extends BaseProblem {

    public static final int[] dx = new int[]{0, 0, -1, 1};
    public static final int[] dy = new int[]{-1, 1, 0, 0};
    public static final String[] label = new String[]{"L", "R", "U", "D"};

    public EightPuzzle(String filename) {
        Scanner input = null;

        int[][] matrix = new int[3][3];
        try {
            input = new Scanner(new File(filename));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while (input.hasNext())
            for (int i = 0; i < 3; i++)
                for (int j = 0; j < 3; j++)
                    matrix[i][j] = input.nextInt();

        initialState = new PuzzleState(matrix);
    }


    @Override
    public ArrayList<Action> getActions(BaseState s) {
        ArrayList<Action> acts = new ArrayList<>();

        int[] xy = ((PuzzleState) s).getZero();
        int x = xy[0], y = xy[1];
        if (x == -1 || y == -1) return acts;

        for (int i = 0; i < 4; i++)
            if (validPoint(x + dx[i], y + dy[i]))
                acts.add(new Action(i, label[i], 1));

        return acts;
    }

    @Override
    public BaseState results(BaseState s, Action act) {
        int[][] oldMat = ((PuzzleState) s).matrix;
        int[][] newMat = new int[3][3];
        for (int i = 0; i < 3; i++)
            System.arraycopy(oldMat[i], 0, newMat[i], 0, 3);

        int[] xy = ((PuzzleState) s).getZero();
        int x = xy[0];
        int y = xy[1];

        newMat[x][y] = newMat[x + dx[act.code]][y + dy[act.code]];
        newMat[x + dx[act.code]][y + dy[act.code]] = 0;

        return new PuzzleState(newMat);
    }

    @Override
    public boolean goalTest(BaseState s) {
        int[][] mat = ((PuzzleState) s).matrix;
        for (int i = 0; i < 3; i++) for (int j = 0; j < 3; j++) if (mat[i][j] != (i * 3 + j)) return false;
        return true;
    }

    @Override
    public BaseState getGoalState() {
        return new PuzzleState(new int[][]{{0, 1, 2}, {3, 4, 5}, {6, 7, 8}});
    }

    private boolean validPoint(int x, int y) {
        return x >= 0 && x <= 2 && y >= 0 && y <= 2;
    }


    private class PuzzleState extends BaseState {
        private int[][] matrix;

        public PuzzleState(int[][] m) {
            this.matrix = m;
        }

        @Override
        public boolean isEqual(BaseState s) {
            int[][] other = ((PuzzleState) s).matrix;
            for (int i = 0; i < 3; i++)
                for (int j = 0; j < 3; j++) if (this.matrix[i][j] != other[i][j]) return false;
            return true;
        }

        @Override
        public double getHeuristic() {
            int h = 0;
            for (int i = 0; i < 3; i++)
                for (int j = 0; j < 3; j++)
                    h += Math.abs(this.matrix[i][j] / 3 - i) + Math.abs(this.matrix[i][j] % 3 - j);
            return h;
        }

        private int[] getZero() {
            int[] xy = new int[]{-1, -1};
            for (int i = 0; i < 3; i++)
                for (int j = 0; j < 3; j++)
                    if (this.matrix[i][j] == 0) {
                        xy[0] = i;
                        xy[1] = j;
                    }
            return xy;
        }
    }
}

package problems;

import algorithms.Action;
import algorithms.BaseState;

import java.util.ArrayList;

public class EightPuzzle extends BaseProblem {

    public EightPuzzle(int[][] m) {
        initialState = new PuzzleState(m);
    }


    @Override
    public ArrayList<Action> getActions(BaseState s) {
        ArrayList<Action> acts = new ArrayList<>();

        int[] xy = ((PuzzleState) s).getZero();
        int i0 = xy[0], j0 = xy[1];
        if (i0 == -1 || j0 == -1) return null;


        if (j0 > 0) acts.add(new Action(0, "L", 1));
        if (i0 > 0) acts.add(new Action(1, "U", 1));
        if (j0 < 2) acts.add(new Action(2, "R", 1));
        if (i0 < 2) acts.add(new Action(3, "D", 1));

        return acts;
    }

    @Override
    public BaseState results(BaseState s, Action act) {
        int[][] oldMat = ((PuzzleState) s).matrix;
        int[][] newMat = new int[3][3];
        for (int i = 0; i < 3; i++) for (int j = 0; j < 3; j++) newMat[i][j] = oldMat[i][j];

        int[] xy = ((PuzzleState) s).getZero();
        int i0 = xy[0], j0 = xy[1];


        switch (act.code) {
            case 0: //Move Left
                newMat[i0][j0] = oldMat[i0][j0 - 1];
                newMat[i0][j0 - 1] = 0;
                break;

            case 1: //Move Up
                newMat[i0][j0] = oldMat[i0 - 1][j0];
                newMat[i0 - 1][j0] = 0;
                break;

            case 2: //Move Right
                newMat[i0][j0] = oldMat[i0][j0 + 1];
                newMat[i0][j0 + 1] = 0;
                break;

            default: //Move Down
                newMat[i0][j0] = oldMat[i0 + 1][j0];
                newMat[i0 + 1][j0] = 0;
                break;
        }

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
            for (int i = 0; i < 3; i++) for (int j = 0; j < 3; j++)
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

package problems;

import algorithms.Action;
import algorithms.BaseState;

import java.util.ArrayList;

public class RescuerRobot extends BaseProblem {
    private int N, M;
    private ArrayList<int[]> walls;

    public RescuerRobot(int n, int m, ArrayList<int[]> wallsList) {
        this.N = n;
        this.M = m;
        initialState = new MapState(0, 0);

        // TODO: inputs for walls properly processed in parsing (-1 the indices!)

        this.walls = wallsList;
        int wallsCount = walls.size();
        int[] w;
        for (int i = 0; i < wallsCount; i++) {
            w = walls.get(i);
            walls.add(new int[]{w[2], w[3], w[0], w[1]});
        }
    }


    @Override
    public ArrayList<Action> getActions(BaseState s) {
        MapState ms = (MapState) s;
        ArrayList<Action> acts = new ArrayList<>();

        if (ms.x > 0 && !wallCheck(ms.x, ms.y, -1, 0)) acts.add(new Action(0, "L", 1));
        if (ms.y > 0 && !wallCheck(ms.x, ms.y, 0, -1)) acts.add(new Action(1, "U", 1));
        if (ms.x < N - 1 && !wallCheck(ms.x, ms.y, 1, 0)) acts.add(new Action(2, "R", 1));
        if (ms.y < M - 1 && !wallCheck(ms.x, ms.y, 0, 1)) acts.add(new Action(3, "D", 1));

        return acts;
    }

    @Override
    public BaseState results(BaseState s, Action act) {
        MapState ms = (MapState) s;

        switch (act.code) {
            case 0: //Move Left
                return new MapState(ms.x - 1, ms.y);
            case 1: //Move Up
                return new MapState(ms.x, ms.y - 1);
            case 2: //Move Right
                return new MapState(ms.x + 1, ms.y);
            default: //(3) Move Down
                return new MapState(ms.x, ms.y + 1);
        }
    }

    @Override
    public boolean goalTest(BaseState s) {
        MapState ms = (MapState) s;
        return ms.x == (N - 1) && ms.y == (M - 1);
    }

    @Override
    public BaseState getGoalState() {
        return new MapState(N, M);
    }


    private boolean wallCheck(int x, int y, int xd, int yd) {
        for (int[] w : walls)
            if (x == w[0] && y == w[1] && w[2] == (x + xd) && w[3] == (y + yd)) return true;
        return false;
    }


    private class MapState extends BaseState {
        private int x, y;

        public MapState(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean isEqual(BaseState s) {
            MapState ms = (MapState) s;
            return this.x == ms.x && this.y == ms.y;
        }

        @Override
        public double getHeuristic() {
            return Math.sqrt((x - N)*(x - N) + (y - M)*(y - M));
        }
    }
}

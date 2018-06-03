package problems;

import algorithms.Action;
import algorithms.BaseState;

import java.util.ArrayList;

public class Rubik extends BaseProblem {
    private ArrayList<Action> acts;


    public Rubik(char[] colors) {
        initialState = new RubikState(colors);

        acts = new ArrayList<>();
        acts.add(new Action(0, "T"));
        acts.add(new Action(1, "TC"));
        acts.add(new Action(2, "F"));
        acts.add(new Action(3, "FC"));
        acts.add(new Action(4, "R"));
        acts.add(new Action(5, "RC"));
    }

    @Override
    public ArrayList<Action> getActions(BaseState s) {
        //All actions are always possible!
        return acts;
    }

    @Override
    public BaseState results(BaseState s, Action act) {
        char[] curColors = ((RubikState) s).colors;
        char[] newColors = curColors.clone();

        switch (act.code) {
            case 0: //Top
                newColors[4] = curColors[20];
                newColors[5] = curColors[21];
                newColors[16] = curColors[4];
                newColors[17] = curColors[5];
                newColors[14] = curColors[17];
                newColors[15] = curColors[16];
                newColors[20] = curColors[16];
                newColors[21] = curColors[14];
                newColors[0] = curColors[2];
                newColors[1] = curColors[0];
                newColors[2] = curColors[3];
                newColors[3] = curColors[1];
                break;
            case 1: //TopR
                newColors[20] = curColors[4];
                newColors[21] = curColors[5];
                newColors[4] = curColors[16];
                newColors[5] = curColors[17];
                newColors[17] = curColors[14];
                newColors[16] = curColors[15];
                newColors[16] = curColors[20];
                newColors[14] = curColors[21];
                newColors[2] = curColors[0];
                newColors[0] = curColors[1];
                newColors[3] = curColors[2];
                newColors[1] = curColors[3];
                break;
            case 2: //Front
                newColors[20] = curColors[2];
                newColors[22] = curColors[3];
                newColors[8] = curColors[22];
                newColors[9] = curColors[20];
                newColors[17] = curColors[8];
                newColors[19] = curColors[9];
                newColors[2] = curColors[19];
                newColors[3] = curColors[17];
                newColors[7] = curColors[5];
                newColors[5] = curColors[4];
                newColors[6] = curColors[7];
                newColors[4] = curColors[6];
                break;
            case 3: //FrontR
                newColors[2] = curColors[20];
                newColors[3] = curColors[22];
                newColors[22] = curColors[8];
                newColors[20] = curColors[9];
                newColors[8] = curColors[17];
                newColors[9] = curColors[19];
                newColors[19] = curColors[2];
                newColors[17] = curColors[3];
                newColors[5] = curColors[7];
                newColors[4] = curColors[5];
                newColors[7] = curColors[6];
                newColors[6] = curColors[4];
                break;
            case 4: //Right
                newColors[1] = curColors[5];
                newColors[3] = curColors[7];
                newColors[15] = curColors[3];
                newColors[13] = curColors[1];
                newColors[9] = curColors[13];
                newColors[11] = curColors[15];
                newColors[5] = curColors[9];
                newColors[7] = curColors[11];
                newColors[20] = curColors[22];
                newColors[21] = curColors[20];
                newColors[22] = curColors[23];
                newColors[23] = curColors[21];
                break;
            case 5: //RightR
                newColors[5] = curColors[1];
                newColors[7] = curColors[3];
                newColors[3] = curColors[15];
                newColors[1] = curColors[13];
                newColors[13] = curColors[9];
                newColors[15] = curColors[11];
                newColors[9] = curColors[5];
                newColors[11] = curColors[7];
                newColors[22] = curColors[20];
                newColors[20] = curColors[21];
                newColors[23] = curColors[22];
                newColors[21] = curColors[23];
                break;
        }
        return new RubikState(newColors);
    }

    @Override
    public boolean goalTest(BaseState s) {
        char[] cols = ((RubikState) s).colors;
        for (int i = 0; i < 21; i += 4)
            if (cols[i] != cols[i + 1] || cols[i] != cols[i + 2] || cols[i] != cols[i + 3]) return false;
        return true;
    }

    @Override
    public BaseState getGoalState() {
        return null;
    }


    private class RubikState extends BaseState {
        private char[] colors;

        public RubikState(char[] colors) {
            this.colors = colors;
        }

        @Override
        public boolean isEqual(BaseState s) {
            RubikState rs = (RubikState) s;
            for (int i = 0; i < 24; i++) if (this.colors[i] != rs.colors[i]) return false;
            return true;
        }

        @Override
        public double getHeuristic() {
            return 0;
        }
    }
}

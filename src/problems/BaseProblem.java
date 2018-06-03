package problems;

import algorithms.Action;
import algorithms.BaseState;

import java.util.ArrayList;

public abstract class BaseProblem {
    BaseState initialState;

    public abstract ArrayList<Action> getActions(BaseState s);

    public abstract BaseState results(BaseState s, Action act);

    public abstract boolean goalTest(BaseState s);

    public BaseState getInitialState() {
        return this.initialState;
    }

    public abstract BaseState getGoalState();
}

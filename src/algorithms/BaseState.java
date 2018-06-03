package algorithms;

import java.util.ArrayList;
import java.util.Comparator;

public abstract class BaseState {
    public ArrayList<Action> actionSeq;
    public double currentCost;

    public static Comparator<BaseState> costComparator = Comparator.comparingDouble(c -> c.currentCost);

    abstract public boolean isEqual(BaseState s);

    public abstract double getHeuristic();
}

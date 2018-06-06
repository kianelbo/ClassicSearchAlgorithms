package algorithms;

import problems.BaseProblem;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class AStar extends BaseAlgorithm {

    public AStar(boolean isGraphSearch) {
        this.isGraphSearch = isGraphSearch;
    }

    @Override
    public boolean search(BaseProblem problem) {
        PriorityQueue<BaseState> queue = new PriorityQueue<>(BaseState.costComparator);
        expanded = new ArrayList<>();

        BaseState initialState = problem.getInitialState();
        initialState.actionSeq = new ArrayList<>();

        queue.add(initialState);
        visited = 1;

        while (!queue.isEmpty()) {
            int memory = queue.size() + (!isGraphSearch ? 0 : expanded.size());
            maxMemory = Math.max(maxMemory, memory);

            BaseState s = queue.remove();
            if (problem.goalTest(s)) {
                solution = s.actionSeq;
                return true;
            }
            expanded.add(s);
            for (Action a : problem.getActions(s)) {
                BaseState targetState = problem.results(s, a);
                boolean adding = true;
                if (isGraphSearch)
                    for (BaseState cs : expanded)
                        if (cs.isEqual(targetState)) {
                            adding = false;
                            break;
                        }
                for (BaseState nextState : queue)
                    if (nextState.isEqual(targetState)) {
                        if (s.currentCost + a.cost + targetState.getHeuristic() < nextState.currentCost)
                            nextState.currentCost = s.currentCost + a.cost + targetState.getHeuristic();
                        adding = false;
                        break;
                    }

                if (adding) {
                    visited++;
                    ArrayList<Action> previousActs = new ArrayList<>(s.actionSeq);
                    previousActs.add(a);

                    targetState.actionSeq = previousActs;
                    targetState.currentCost += a.cost - targetState.getHeuristic();
                    queue.add(targetState);
                }

            }

        }
        return false;
    }

    @Override
    int getTotalCost() {
        int finalCost = 0;
        for (Action a : solution)
            finalCost += a.cost;
        return finalCost;
    }

    @Override
    int getExpandedNumbers() {
        return expanded.size();
    }
}

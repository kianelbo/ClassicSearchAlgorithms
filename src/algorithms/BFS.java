package algorithms;

import problems.BaseProblem;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BFS extends BaseAlgorithm {

    public BFS(boolean isGraphSearch) {
        this.isGraphSearch = isGraphSearch;
    }


    @Override
    public boolean search(BaseProblem problem) {
        Queue<BaseState> queue = new LinkedList<>();
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
                        adding = false;
                        break;
                    }

                if (adding) {
                    visited++;
                    ArrayList<Action> previousActs = new ArrayList<>(s.actionSeq);
                    previousActs.add(a);

                    targetState.actionSeq = previousActs;
                    queue.add(targetState);
                }

            }
        }
        return false;
    }

    @Override
    int getTotalCost() {
        return 0;
    }

    @Override
    int getExpandedNumbers() {
        return expanded.size();
    }
}

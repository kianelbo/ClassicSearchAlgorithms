package algorithms;

import problems.BaseProblem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class Bidirectional extends BaseAlgorithm {

    public Bidirectional(boolean isGraphSearch) {
        this.isGraphSearch = isGraphSearch;
    }

    @Override
    public boolean search(BaseProblem problem) {
        Queue<BaseState>[] queue = new LinkedList[2];
        expanded = new ArrayList<>();
        ArrayList<BaseState>[] expandeds = new ArrayList[2];

        for (int i = 0; i < 2; i++) {
            queue[i] = new LinkedList<>();
            expandeds[i] = new ArrayList<>();
        }

        BaseState initialState = problem.getInitialState();
        initialState.actionSeq = new ArrayList<>();
        BaseState goalState = problem.getGoalState();
        goalState.actionSeq = new ArrayList<>();

        queue[0].add(initialState);
        queue[1].add(goalState);
        visited = 2;

        int turn = 0;
        while (!queue[0].isEmpty() && !queue[1].isEmpty()) {
            int memory = queue[0].size() + queue[1].size() + (isGraphSearch ? 0 : expandeds[0].size() + expandeds[1].size());
            maxMemory = Math.max(maxMemory, memory);

            BaseState[] s = new BaseState[2];

            for (BaseState check0 : expandeds[0])
                for (BaseState check1 : expandeds[1])
                    if (check0.isEqual(check1)) {
                        solution = check0.actionSeq;
                        solution.add(0, new Action(-1, "from origin:"));
                        solution.add(new Action(-1, "\n -> from goal:"));
                        Collections.reverse(check1.actionSeq);
                        solution.addAll(check1.actionSeq);
                        expanded.addAll(expandeds[0]);
                        expanded.addAll(expandeds[1]);
                        return true;
                    }
            s[turn] = queue[turn].remove();
            expandeds[turn].add(s[turn]);
            for (Action a : problem.getActions(s[turn])) {
                BaseState targetState = problem.results(s[turn], a);
                boolean adding = true;
                if (isGraphSearch)
                    for (BaseState cs : expandeds[turn])
                        if (cs.isEqual(targetState)) {
                            adding = false;
                            break;
                        }
                for (BaseState nextState : queue[turn])
                    if (nextState.isEqual(targetState)) {
                        adding = false;
                        break;
                    }

                if (adding) {
                    visited++;
                    ArrayList<Action> previousActs = new ArrayList<>(s[turn].actionSeq);
                    previousActs.add(a);

                    targetState.actionSeq = previousActs;
                    queue[turn].add(targetState);
                }
            }
            turn ^= 1;
        }
        return false;
    }

    @Override
    int getTotalCost() {
        return 0;
    }
}

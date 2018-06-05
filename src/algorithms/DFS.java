package algorithms;

import problems.BaseProblem;

import java.util.ArrayList;
import java.util.Stack;

public class DFS extends BaseAlgorithm {
    private boolean isIDS = false;
    private int limit;

    public DFS(boolean isGraphSearch, int l) {
        this.isGraphSearch = isGraphSearch;
        if (limit == 0) {
            isIDS = true;
            limit = 1;
        } else this.limit = l;
    }


    @Override
    public boolean search(BaseProblem problem) {
        if (isIDS)
            while (true)
                if (searchDFS(problem)) return true;
                else limit++;
        return searchDFS(problem);
    }


    private boolean searchDFS(BaseProblem problem) {
        Stack<BaseState> stack = new Stack<>();
        expanded = new ArrayList<>();

        BaseState initialState = problem.getInitialState();
        initialState.actionSeq = new ArrayList<>();

        stack.add(initialState);
        visited = 1;

        while (!stack.empty()) {
            int memory = stack.size() + (isGraphSearch ? 0 : expanded.size());
            maxMemory = Math.max(maxMemory, memory);

            BaseState s = stack.pop();
            if (problem.goalTest(s)) {
                solution = s.actionSeq;
                return true;
            }
            expanded.add(s);

            if (limit != -1 && s.actionSeq.size() >= limit) continue;

            for (Action a : problem.getActions(s)) {
                BaseState targetState = problem.results(s, a);
                boolean adding = true;
                if (isGraphSearch) for (BaseState cs : expanded)
                    if (cs.isEqual(targetState)) {
                        adding = false;
                        break;
                    }
                for (BaseState nextState : stack)
                    if (nextState.isEqual(targetState)) {
                        adding = false;
                        break;
                    }
                if (adding) {
                    visited++;
                    ArrayList<Action> previousActs = new ArrayList<>(s.actionSeq);
                    previousActs.add(a);

                    targetState.actionSeq = previousActs;
                    stack.push(targetState);
                }

            }
        }
        return false;
    }


    @Override
    int getTotalCost() {
        return 0;
    }
}

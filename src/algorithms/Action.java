package algorithms;

public class Action {
    public int code, cost;
    private String label;

    public Action (int c, String l, int cost) {
        this.code = c;
        this.label = l;
        this.cost = cost;
    }

    public Action (int c, String l) {
        this.code = c;
        this.label = l;
    }

    public String getLabel() {
        return label;
    }

}

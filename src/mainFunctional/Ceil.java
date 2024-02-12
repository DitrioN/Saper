package mainFunctional;

public class Ceil {
    private boolean open = false;
    private CeilType type = CeilType.empty;
    private int radius = 0;

    public boolean isOpen () {
        return open;
    }

    public CeilType getType () {
        return type;
    }

    public int getRadius () {
        return radius;
    }

    public void setOpen (boolean open) {
        this.open = open;
    }

    public void setType (CeilType type) {
        this.type = type;
    }

    public void addRadius () {
        this.radius += 1;
    }
}

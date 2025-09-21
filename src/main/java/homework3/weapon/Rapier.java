package homework3.weapon;

public class Rapier {
    private String owner;
    private boolean isClean;

    public Rapier(String owner) {
        this.owner = owner;
        isClean = true;

    }

    public void polish() {
        isClean = true;
    }

    public void makeDirty() {
        isClean = false;
    }

    public String getOwner() {
        return owner;
    }

    public boolean isClean() {
        return isClean;
    }

}

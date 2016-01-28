package m2dl.com.binarydiver.data;

public enum Difficulty {

    FACILE("Facile", 1f),
    NORMAL("Normal", 1.5f),
    DIFFICILE("Difficile", 2f),
    HARDCORE("Hardcore", 4f);

    private String name;
    private float multiplier;

    Difficulty(String name, float multiplier) {
        this.name = name;
        this.multiplier = multiplier;
    }

    public float getMultiplier() {
        return multiplier;
    }

    @Override
    public String toString() {
        return name;
    }
}

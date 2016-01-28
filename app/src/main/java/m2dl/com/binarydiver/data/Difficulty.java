package m2dl.com.binarydiver.data;

public enum Difficulty {
    FACILE("Facile"),
    NORMAL("Normal"),
    DIFFICILE("Difficile"),
    HARDCORE("Hardcore");

    private String name;

    Difficulty(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}

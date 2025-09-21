package homework3.enums;

public enum Trait {
    DRUNKARD("Пьяница"),
    ACCOMPLISHED_DUELIST("Превосходный фехтовальщик"),
    CARDSHARP("Шулер"),
    VAIN("Гордый"),
    BRAWLER("Задира");

    private final String description;

    Trait(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}

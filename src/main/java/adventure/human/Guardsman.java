package adventure.human;

import adventure.enums.TrainingLevel;
import adventure.enums.Trait;

public class Guardsman extends Soldier {

    public Guardsman(String name, Trait trait) {
        super(name, trait, TrainingLevel.ADEPT);
    }
}

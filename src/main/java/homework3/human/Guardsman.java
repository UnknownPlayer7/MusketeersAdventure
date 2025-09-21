package homework3.human;

import homework3.enums.TrainingLevel;
import homework3.enums.Trait;

public class Guardsman extends Soldier {

    public Guardsman(String name, Trait trait) {
        super(name, trait, TrainingLevel.ADEPT);
    }
}

package homework3.human;

import homework3.enums.TrainingLevel;
import homework3.enums.Trait;

public class Musketeer extends Soldier{

    public Musketeer(String name, Trait trait) {
        super(name, trait, TrainingLevel.ELITE);
    }


}

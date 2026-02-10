package adventure.human;

import adventure.enums.TrainingLevel;
import adventure.enums.Trait;

public class Musketeer extends Soldier{

    public Musketeer(String name, Trait trait) {
        super(name, trait, TrainingLevel.ELITE);
    }


}

package homework3.human;

import homework3.weapon.Rapier;
import homework3.enums.TrainingLevel;
import homework3.enums.Trait;

import java.util.ArrayList;

abstract public class Soldier extends Man{

    protected TrainingLevel trainingLevel;
    protected Rapier rapier;

    public Soldier(String name, Trait trait, TrainingLevel trainingLevel) {
        super(name, trait);
        this.trainingLevel = trainingLevel;
        rapier = new Rapier(name);
    }

    public void attack(Soldier opponent) {
        if(getFencingMoveChance() - opponent.getFencingMoveChance() > 30) {
            opponent.getWounded();
            System.out.printf("%s исполняет виртуозный выпад. %s получил ранение.\n", name, opponent.name);
            System.out.println("Дуэль оконьчена!\n");

            rapier.makeDirty();
            opponent.rapier.makeDirty();
        } else {
            System.out.printf("""
                    %s совершил стремительное движение шпаги, описав дугу, но замешательство в глазах выдало
                    нерешительность — %s парировал, воспользовавшись моментом.\n""", name, opponent.name);
        }
    }

    protected int getFencingMoveChance() {

        int firstBonus = trainingLevel == TrainingLevel.ELITE ? 20 : 0;
        int secondBonus = trait == Trait.ACCOMPLISHED_DUELIST ? 30 : 0;

        return (int)(Math.random() * 50 + firstBonus + secondBonus);
    }

    public void polish(ArrayList<Rapier> arsenal) {
        if(trait == Trait.VAIN) {
            this.say("Да как Вы смеете мне приказывать! Что бы Я?! Да никогда!");
            return;
        } else if (!isWeaponSoiled(arsenal)) {
            this.say("В арсенале все шпаги в идеальном состоянии. Нечего чистить,командир.");
            return;
        }
        System.out.printf("%s открыл арсенал и осматривает шпаги...\n", name);

        for (Rapier rapier : arsenal) {
            if(!rapier.isClean()) {
                rapier.polish();
                System.out.printf("%s тщательно полирует шпагу %sа\n", name, rapier.getOwner());
            }
        }

    }

    private boolean isWeaponSoiled(ArrayList<Rapier> arsenal) {
        for (Rapier rapier : arsenal) {
            if(!rapier.isClean()) {
                return true;
            }
        }
        return false;
    }

    public Rapier putOutRapier() {
        Rapier baredRapier = rapier;
        rapier = null;
        return baredRapier;
    }

    public void takeRapierFromArsenal(ArrayList<Rapier> arsenal) {
        for (int i = 0; i < arsenal.size(); i++) {
            if(arsenal.get(i).getOwner().equals(name)) {
                this.rapier = arsenal.remove(i--);
            }

        }
    }
}

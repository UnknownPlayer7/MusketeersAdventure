package homework3.location;

import homework3.serviceClasses.ConsoleHelper;
import homework3.serviceClasses.FileIO;
import homework3.weapon.Rapier;
import homework3.human.Musketeer;

import java.util.ArrayList;

public class Barracks {
    private final Tavern tavern;
    private final Plaza plaza;
    private ArrayList<Musketeer> musketeers = new ArrayList<>();
    private final ArrayList<Rapier> arsenal = new ArrayList<>();
    private final ConsoleHelper consoleHelper = new ConsoleHelper();


    public Barracks() {
        tavern = new Tavern(this);
        plaza = new Plaza(this);
    }


    public void enterMusketeers(ArrayList<Musketeer> musketeers) {
        System.out.println("Мушкетеры зашли в казарму.");
        this.musketeers = musketeers;

        for (Musketeer musketeer : musketeers) {
            putRapierIntoArsenal(musketeer);
        }
        showBarracks();
    }

    private void showBarracks() {
        consoleHelper.showBarracksMenu();
        switch (consoleHelper.waitNumber()) {
            case 1 -> polishRapiers(consoleHelper.choosePerformer(musketeers));
            case 2 -> recover(consoleHelper.choosePerformer(musketeers));
            case 3 -> leave();
            case 4 -> sleep();
            default -> {
                consoleHelper.showIncorrectNumberEnterMessage(1,3);
                showBarracks();
            }
        }
        showBarracks();
    }

    private void putRapierIntoArsenal(Musketeer musketeer) {
        arsenal.add(musketeer.putOutRapier());
        System.out.println(musketeer.getName() + " кладет свою рапиру в арсенал.");
    }

    private void polishRapiers(Musketeer musketeer) {

        if (!musketeers.isEmpty() && !arsenal.isEmpty() && musketeer.canEngage()) {
            musketeer.polish(arsenal);
        }
    }

    private void recover(Musketeer musketeer) {
        if(!musketeers.isEmpty() && musketeer.isWounded()) {
            musketeer.say("Лекарь сказал, что не знает поможет ли эта микстура, но точно не повредит.");
            musketeer.recover();
            System.out.println("Мушкетер восстановился от ранений.");
        }
    }

    private void leave() {
        if(musketeers != null) {
            for (Musketeer musketeer : musketeers) {
                musketeer.takeRapierFromArsenal(arsenal);
            }

            System.out.println("Мушкетеры покинули казарму.");
            chooseDestination();
            this.musketeers = null;
        } else {
            ConsoleHelper.noMusketeersHere("Казарма");
        }

    }

    private void sleep() {


        if(musketeers != null) {
            System.out.println("Мушкетеры, отбой!");

            for (Musketeer musketeer : musketeers) {
                musketeer.sleep();
            }
            FileIO.savePlayer(musketeers.getLast());
            System.exit(0);
        } else {
            ConsoleHelper.noMusketeersHere("Казарма");
        }
    }

    private void chooseDestination() {
        System.out.println("""
        Куда направимся?
        1. Таверна;
        2. Площадь.""");

        switch (consoleHelper.waitNumber()) {
            case 1 -> tavern.enterMusketeers(musketeers);
            case 2 -> plaza.enterMusketeers(musketeers);
            default -> {
                consoleHelper.showIncorrectNumberEnterMessage(1,2);
                leave();
            }
        }
    }


}

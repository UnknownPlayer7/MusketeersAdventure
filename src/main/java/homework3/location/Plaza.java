package homework3.location;

import homework3.human.Guardsman;
import homework3.human.Musketeer;
import homework3.serviceClasses.ConsoleHelper;
import homework3.serviceClasses.PersonCreator;

import java.util.ArrayList;

public class Plaza {
    private static final ConsoleHelper consoleHelper = new ConsoleHelper();
    private ArrayList<Musketeer> musketeers = new ArrayList<>();
    private final ArrayList<Guardsman> guardsmen;
    private final Barracks barracks;

    public Plaza(Barracks barracks) {
        this.barracks = barracks;
        guardsmen = PersonCreator.createDefaultGuardsmen();
    }

    public void enterMusketeers(ArrayList<Musketeer> musketeers) {
        System.out.println("Мушкетеры прибыли на прощадь.");
        this.musketeers = musketeers;
        showPlaza();
    }

    private void showPlaza() {
        consoleHelper.showPlazaMenu();
        switch (consoleHelper.waitNumber()) {
            case 1 -> lookAround();
            case 2 -> leave();
            default -> {
                consoleHelper.showIncorrectNumberEnterMessage(1,2);
                showPlaza();
            }
        }
        showPlaza();
    }

    private void lookAround() {
        if(musketeers != null) {
            System.out.println("Улицы площади выгледят пустынно...");

            if(hasAmbush()) {
                attackMusketeers();
            }
        } else {
            ConsoleHelper.noMusketeersHere("Площадь");
        }
    }

    private boolean hasAmbush() {
        for (Guardsman guardsman : guardsmen) {
            if(guardsman.isWounded()) {
                return false;
            }
        }
        return true;
    }

    private void attackMusketeers() {
        System.out.println("За считаные секунды мушкетеров окружили гвардейцы кардинала.");
        guardsmen.getFirst().say("Сложите свои шпаги, господа, и может быть мы пощадим вас!");
        musketeers.getLast().say("""
                Героев Франции не счесть.
                За короля пойдем на смерть!
                Пусть ветер играет плащом на плечах.
                Мы — мушкетёры, и смерть нам не страх!
                """);
        System.out.println("БОЙ:");

        ArrayList<Musketeer> musketeersTeam = new ArrayList<>(musketeers.stream().filter(x -> !x.isWounded()).toList());
        ArrayList<Guardsman> guardsmenTeam = new ArrayList<>(guardsmen.stream().filter(x -> !x.isWounded()).toList());
        Musketeer musketeer;
        Guardsman guardsman;

        while (!guardsmenTeam.isEmpty() && !musketeersTeam.isEmpty()){
            musketeer = musketeersTeam.getFirst();
            guardsman = guardsmenTeam.getFirst();

            musketeer.attack(guardsman);

            if(guardsman.isWounded()) {
                guardsmenTeam.remove(guardsman);
            } else {
                guardsman.attack(musketeer);
                if(musketeer.isWounded()) {
                    musketeersTeam.remove(musketeer);
                }
            }
        }

        if(!musketeersTeam.isEmpty()) {
            celebrateVictory();
        } else {
            System.out.println("Мушкетеры понесли сокрушительное поражение.");
        }
        leave();
    }

    private void celebrateVictory() {
        musketeers.getFirst().say("Шляпы долой, господа!");
        musketeers.getLast().say("Да здравствует король!");
    }

    private void leave() {
        if(musketeers != null) {
            System.out.println("Мушкетеры покинули площадь.");
            barracks.enterMusketeers(musketeers);
            musketeers = null;
        } else {
            ConsoleHelper.noMusketeersHere("Площадь");
        }
    }
}

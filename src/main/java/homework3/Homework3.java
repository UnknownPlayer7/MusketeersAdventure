package homework3;

import homework3.enums.Trait;
import homework3.human.Musketeer;
import homework3.location.Barracks;
import homework3.serviceClasses.ConsoleHelper;
import homework3.serviceClasses.PersonCreator;
import homework3.serviceClasses.FileIO;

import java.util.ArrayList;
import java.util.List;

public class Homework3 {

    private static final Barracks barracks = new Barracks();
    private static final ConsoleHelper consoleHelper = new ConsoleHelper();

    public static void showHomework() {
        barracks.enterMusketeers(gatherMusketeers());
    }

    private static ArrayList<Musketeer> gatherMusketeers() {
        ArrayList<Musketeer> musketeers = createPermanentMusketeers();
        musketeers.add(createPlayerMusketeer());
        return musketeers;
    }

    private static ArrayList<Musketeer> createPermanentMusketeers() {

        return new ArrayList<>(List.of(new Musketeer("Д'Артаньян", Trait.CARDSHARP),
                new Musketeer("Атос", Trait.DRUNKARD),
                new Musketeer("Арамис", Trait.ACCOMPLISHED_DUELIST),
                new Musketeer("Партос", Trait.VAIN)));
    }

    private static Musketeer createPlayerMusketeer() {
        consoleHelper.showStartMenu();

        switch (consoleHelper.waitNumber()) {
            case 1 -> {
                return FileIO.loadPlayer();
            }
            case 2 -> {
                return new PersonCreator().createMusketeer();
            }
            default -> consoleHelper.showIncorrectNumberEnterMessage(1, 2);
        }
        return createPlayerMusketeer();
    }

}

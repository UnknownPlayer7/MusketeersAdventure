package adventure;

import adventure.enums.Trait;
import adventure.human.Musketeer;
import adventure.location.Barracks;
import adventure.serviceClasses.ConsoleHelper;
import adventure.serviceClasses.PersonCreator;

import java.util.ArrayList;
import java.util.List;

public class Adventure {

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
        PersonCreator personCreator = new PersonCreator();

        switch (consoleHelper.waitNumber()) {
            case 1 -> {
                return personCreator.loadPlayer();
            }
            case 2 -> {
                return personCreator.createMusketeer();
            }
            default -> consoleHelper.showIncorrectNumberEnterMessage(1, 2);
        }
        return createPlayerMusketeer();
    }

}

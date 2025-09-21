package homework3.location;

import homework3.enums.Trait;
import homework3.human.Citizen;
import homework3.human.Man;
import homework3.human.Musketeer;
import homework3.serviceClasses.ConsoleHelper;
import homework3.serviceClasses.PersonCreator;

import java.util.ArrayList;

public class Tavern {
    private static final ConsoleHelper consoleHelper = new ConsoleHelper();
    private ArrayList<Musketeer> musketeers = new ArrayList<>();
    private final ArrayList<Citizen> citizens;
    private final Barracks barracks;

    public Tavern(Barracks barracks) {
        this.barracks = barracks;
        citizens = PersonCreator.createDefaultCitizens();
    }

    public void enterMusketeers(ArrayList<Musketeer> musketeers) {
        System.out.println("Мушкетеры зашли в таверну.");
        this.musketeers = musketeers;
        showTavern();
    }

    private void showTavern() {
        consoleHelper.showTavernMenu();
        switch (consoleHelper.waitNumber()) {
            case 1 -> playDices();
            case 2 -> drink();
            case 3 -> leave();
            default -> {
                consoleHelper.showIncorrectNumberEnterMessage(1,3);
                showTavern();
            }
        }
        showTavern();
    }

    private void playDices() {
        if(musketeers == null) {
            ConsoleHelper.noMusketeersHere("Таверна");
            return;
        }

        int roundCounter = 1;
        Man[] competitors = gatherEveryone();
        System.out.print("\nЧестный люд собрался за столом для игры в кости!");

        while (competitors.length > 1) {
            System.out.printf("\n%d РАУНД!", roundCounter);

            competitors = getWinner(competitors);
            roundCounter++;
        }
        System.out.printf("\n%s - абсолютный победитель игры в кости!\n", competitors[0].getName());

        if(isWinnerCitizen(competitors[0]) && hasBrawlerAmongMusketeers()) {
            initiateBrawl();
        }
    }

    private Man[] gatherEveryone() {
        ArrayList<Man> competitorsList = new ArrayList<>();
        competitorsList.addAll(musketeers);
        competitorsList.addAll(citizens);

        return competitorsList.toArray(new Man[0]);
    }

    private Man[] getWinner(Man[] competitors) {

        Man[][] table = writeScoreResultIntoTable(competitors);

        return writeWinnerList(table).toArray(new Man[0]);
    }

    private Man[][] writeScoreResultIntoTable(Man[] competitors) {
        Man[][] table = new Man[19][competitors.length];
        for (Man men : competitors) {
            int score = men.throughDices();

            for (int i = 0; i < table[score].length; i++) {
                if(table[score][i] == null) {
                    table[score][i] = men;
                    break;
                }
            }
        }
        return table;
    }

    private ArrayList<Man> writeWinnerList(Man[][] results) {
        ArrayList<Man> winnerList = new ArrayList<>();
        for (int score = results.length - 1; score > 0 ; score--) {
            if(results[score][0] != null) {
                for (int i = 0; i < results[score].length; i++) {
                    if (results[score][i] != null) {
                        winnerList.add(results[score][i]);
                    } else {
                        break;
                    }
                }
                break;
            }
        }
        return winnerList;
    }

    private boolean isWinnerCitizen(Man winner) {
        return winner instanceof Citizen;
    }

    private boolean hasBrawlerAmongMusketeers() {
        for (Musketeer musketeer : musketeers) {
            if(musketeer.trait == Trait.BRAWLER) {
                return true;
            }
        }
        return false;
    }
    
    private void initiateBrawl() {
        System.out.println("Видимо среди мушкетеров есть задира и ему не понравилось, что горожанин победил.");
        System.out.println("КУЛАЧНЫЙ БОЙ:");

        ArrayList<Musketeer> musketeersTeam = new ArrayList<>(musketeers.stream().filter(x -> !x.isWounded()).toList());
        ArrayList<Citizen> citizensTeam = new ArrayList<>(citizens.stream().filter(x -> !x.isWounded()).toList());
        Musketeer musketeer;
        Citizen citizen;

        do {
            musketeer = musketeersTeam.getFirst();
            citizen = citizensTeam.getFirst();

            musketeer.punch(citizen);

            if(citizen.isWounded()) {
                citizensTeam.remove(citizen);
            } else {
                citizen.punch(musketeer);
                if(musketeer.isWounded()) {
                    musketeersTeam.remove(musketeer);
                }
            }
        } while (!citizensTeam.isEmpty() && !musketeersTeam.isEmpty());

        if(!musketeersTeam.isEmpty()) {
            drink();
        }
        leave();
    }

    private void drink() {
        if(musketeers != null) {
            for (Musketeer musketeer : musketeers) {
                musketeer.drinkBeer();
            }
        } else {
            ConsoleHelper.noMusketeersHere("Таверна");
        }

    }

    private void leave() {
        if(musketeers != null) {
            System.out.println("Мушкетеры покинули таверну.");
            barracks.enterMusketeers(musketeers);
            musketeers = null;
        } else {
            ConsoleHelper.noMusketeersHere("Таверна");
        }
    }




}

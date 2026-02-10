package adventure.serviceClasses;

import adventure.enums.ServiceMessage;
import adventure.enums.Trait;
import adventure.human.Citizen;
import adventure.human.Guardsman;
import adventure.human.Musketeer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class PersonCreator {
    private final Musketeer courier = new Musketeer("Жан-Поль", Trait.ACCOMPLISHED_DUELIST);
    private final ConsoleHelper consoleHelper = new ConsoleHelper();
    private String name = "Пьер";
    private Trait trait = Trait.BRAWLER;

    public Musketeer createMusketeer() {
        courier.say("""
                Эй ты! Да, ты! Не стой столбом, поди сюда, да поживее.
                У меня распоряжение самого Короля!
                Тебя производят в мушкетеры. Это большая честь.
                Осталась небольшая формальность. Вот взгляни. Это твое личное дело. Если есть что исправить - говори.
                """);
        consoleHelper.showMusketeerDossier(name, trait);
        checkMusketeerData();
        consoleHelper.showMusketeerDossier(name, trait);
        courier.say("Отлично! С этим покончено, а теперь ступай в казарму.");

        return new Musketeer(name, trait);
    }

    private void checkMusketeerData () {
        System.out.println("""
                
                1. В имени ошибка;
                2. Особые черты указаны неверно;
                3. Все в порядке;""");

        switch (consoleHelper.waitNumber()) {
            case 1 -> {
                System.out.print("Напиши имя: ");
                name = consoleHelper.waitNotEmptyLine();
            }
            case 2 -> {
                System.out.printf("""
                            
                            1. %s\t2. %s\t3. %s
                            4. %s\t5. %s
                            """, Trait.values()[0].getDescription(), Trait.values()[1].getDescription(),
                        Trait.values()[2].getDescription(), Trait.values()[3].getDescription(),
                        Trait.values()[4].getDescription());

                int number;
                while ((number = (consoleHelper.waitNumber())) < 1 || number > 5 ) {
                    consoleHelper.showIncorrectNumberEnterMessage(1,5);
                }
                trait = Trait.values()[number - 1];
            }
            case 3 -> {
                return;
            }
            default -> {
                consoleHelper.showIncorrectNumberEnterMessage(1,3);
            }
        }
        checkMusketeerData();
    }

    public static ArrayList<Citizen> createDefaultCitizens() {
        return new ArrayList<>(List.of(new Citizen("Горн", Trait.CARDSHARP),
                                     new Citizen("Лестер", Trait.CARDSHARP),
                                     new Citizen("Милтен", Trait.CARDSHARP),
                                     new Citizen("Диего", Trait.CARDSHARP)));
    }

    public static ArrayList<Guardsman> createDefaultGuardsmen() {
        return new ArrayList<>(List.of(new Guardsman("Корнет Жибер", Trait.ACCOMPLISHED_DUELIST),
                                       new Guardsman("Сержант Эмильен", Trait.ACCOMPLISHED_DUELIST),
                                       new Guardsman("Гвардеец Ален", Trait.ACCOMPLISHED_DUELIST),
                                       new Guardsman("Гвардеец Даниэль", Trait.ACCOMPLISHED_DUELIST),
                                       new Guardsman("Гвардеец Шарль", Trait.ACCOMPLISHED_DUELIST)));
    }

    public Musketeer loadPlayer() {
        Locator locator = new Locator();
        String name = locator.getAttributeById(1,"name");
        Trait trait = Trait.valueOf(locator.getAttributeById(1,"trait"));

        return new Musketeer(name, trait);
    }
}

package homework3.serviceClasses;

import homework3.enums.Trait;
import homework3.human.Musketeer;

import java.util.ArrayList;
import java.util.Scanner;

public class ConsoleHelper {
    private Scanner keyboard = new Scanner(System.in);

    public int waitNumber() {
        System.out.print("Введи номер: ");

        if(keyboard.hasNextInt()) {
            return keyboard.nextInt();
        } else {
            System.out.println("Ошибка! Попробуй снова!");
            return waitNumber();
        }
    }

    public String waitNotEmptyLine() {
        String currentLine;

        do {
            currentLine = keyboard.nextLine().trim();
        }while (currentLine.isEmpty());

        return currentLine;
    }

    public void showIncorrectNumberEnterMessage(int leftBorder, int rightBorder) {
        System.out.printf("Ошибка! Необходимо ввести число от %d до %d\n", leftBorder , rightBorder);
    }

    public void showMusketeerDossier(String name, Trait trait) {
        System.out.printf("""
                ----------------------------------------------
                               ⚜️ЛИЧНОЕ ДЕЛО⚜️
                       Имя: %s
                       Особые черты: %s
                
                Подпись: 𝓘𝓰𝓻𝓸𝓴
                ----------------------------------------------
                """, name, trait.getDescription());
    }

    public void showTavernMenu() {
        System.out.println("""
                1. Играть в кости;
                2. Пить пиво;
                3. Вернуться в казарму.""");
    }

    public void showPlazaMenu() {
        System.out.println("""
                1. Осмотреться;
                2. Вернуться в казарму.""");
    }

    public void showBarracksMenu() {
        System.out.println("""
                1. Полировать шпаги;
                2. Лечить ранения;
                3. Покинуть казарму.
                4. Мушкетеры, отбой! *(завершить приложение)*""");
    }

    public void showStartMenu() {
        System.out.println("""
                Желаете загрузить существующего мушкетера?
                1. Да
                2. Нет""");
    }

    public void showMusketeers(ArrayList<Musketeer> musketeers) {
        for (int i = 0; i < musketeers.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, musketeers.get(i).getName());
        }
    }

    public Musketeer choosePerformer(ArrayList<Musketeer> musketeers) {
        showMusketeers(musketeers);

        int index = waitNumber();
        int border = musketeers.size();

        if( index > border || index < 1 ) {
            showIncorrectNumberEnterMessage(1, border);
            choosePerformer(musketeers);
        }
        return musketeers.get(index - 1);
    }

    public static void noMusketeersHere(String location) {
        System.out.printf("[%s]: Мушкетеров здесь нет.", location);
    }
}

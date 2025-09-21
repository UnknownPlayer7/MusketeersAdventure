package homework3.human;

import homework3.enums.Trait;

abstract public class Man {
    public final Trait trait;
    protected final String name;
    protected boolean isWounded;
    protected boolean isDrunk;
    protected boolean isSleep;
    protected int beerMugCount;

    public Man(String name, Trait trait) {
        this.name = name;
        this.trait = trait;
    }

    public void punch(Man opponent) {
        if(getPunchMoveChance() - opponent.getPunchMoveChance() > 40) {
            opponent.getWounded();
            System.out.printf("\n%s провел точный джеб в челюсть. %s рухнул навзничь.", name, opponent.name);
            System.out.println("\nБой оконьчен!");
        } else if (getPunchMoveChance() - opponent.getPunchMoveChance() > 20) {
            System.out.printf("\n%s совершил молниеносный левых хук," +
                    "но %s устоял на ногах.", name, opponent.name);
        } else {
            System.out.printf("\n%s рванул вперед с прямым ударом, но %s ловко склонил " +
                    "голову и увернулся.", name, opponent.name);
        }
    }

    protected int getPunchMoveChance() {

        int bonus = trait == Trait.BRAWLER ? 10 : 0;

        return (int)(Math.random() * 90 + bonus);
    }

    public int throughDices() {
        int[] dices = new int[3];
        int sum = 0;

        for (int i = 0; i < 3; i++) {
            dices[i] = getRandomDice();
            sum += dices[i];
        }

        System.out.printf("\n%s бросает кости...\t%d\t||\t%d\t||\t%d",name,dices[0],dices[1],dices[2]);
        return sum;
    }

    private int getRandomDice() {
        int dice;

        do {
            dice = trait == Trait.CARDSHARP ? (int)(Math.random() * 3 + 3) : (int)(Math.random() * 6 + 1);
        }while (dice < 1 || dice > 6);


        return dice;
    }

    public void say(String phrase) {
        System.out.printf("[%s]: %s\n", name, phrase);
    }

    public void drinkBeer() {
        recovery();
        isDrunk = true;
        beerMugCount++;
        if(beerMugCount > 3) {
            System.out.printf("%s сегодня выпил достаточно. Он едва держится на ногах.\n", name);
        }
        if( !isTooDrunk() && trait == Trait.DRUNKARD) {
            drinkBeer();
        }
    }

    private void recovery() {
        isWounded = false;
        switch (beerMugCount) {
            case 0 -> say("Пивка для рывка!");
            case 1 -> say("Еще по одной?");
            case 2 -> say("Не дай себе засохнуть!");
            case 3 -> say("Он меня не уважает?!");
        }
    }

    public void sleep() {
        isSleep = true;
    }

    protected void getWounded() {
        isWounded = true;
    }

    public boolean canEngage() {
        if (isWounded() || isSleep() || isTooDrunk()) {
            System.out.printf("%s не в состоянии что-либо сделать.\n", name);
            return false;
        }
        return true;
    }

    public boolean isWounded() {
        return isWounded;
    }

    public boolean isTooDrunk() {
        return isDrunk && beerMugCount > 3;
    }

    public boolean isSleep() {
        return isSleep;
    }

    public String getName() {
        return name;
    }

    public void recover() {
        isWounded = false;
    }

    public String getMainInfo() {
        return String.format("""
                %s
                %s
                """, name, trait);
    }
}

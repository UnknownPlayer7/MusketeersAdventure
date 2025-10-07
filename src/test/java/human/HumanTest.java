package human;

import homework3.enums.Trait;
import homework3.human.Musketeer;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import testBase.TestBase;

import java.lang.reflect.InvocationTargetException;


public class HumanTest extends TestBase {

    @Test(dataProvider = "musketeerDiceTest", groups = "positive")
    public void throughDices(Musketeer musketeer) {
        int dice;
        for (int i = 0; i < 100; i++) {
            try {
                dice = (int) getPrivateMethodWithoutArgs(musketeer.getClass(), "getRandomDice").invoke(musketeer);
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }

            if(dice < 1 || dice > 6) {
                Assert.fail(getReport(
                        "dice in the range [1,6]",
                        String.valueOf(dice),
                        "getRandomDice()"
                ));
            }
        }
    }

    @DataProvider(name = "musketeerDiceTest")
    public Object[][] musketeerDiceTestProvider() {
        return new Object[][]{
                {new Musketeer("Test1", Trait.CARDSHARP)},
                {new Musketeer("Test2", Trait.ACCOMPLISHED_DUELIST)}
        };
    }

    @Test(dataProvider = "musketeerCanEngageTest", groups = "positive")
    public void canEngage(Musketeer musketeer, boolean expected) {
       String report = String.format(
               """
                        
                       If unit sleep OR wounded OR too drunk (drunk over 3 beer mugs)* return FALSE;
                       Unit status: 1) isSleep:    %b
                                    2) isWounded:  %b
                                    3) isDrunk: %b
                                    4) beerMugCount: %d
                       Expected result of method canEngage(): %b
                       Actual: %b
                       """,
               musketeer.isSleep(), musketeer.isWounded(), musketeer.isDrunk(),
               musketeer.getBeerMugCount(), expected, musketeer.canEngage()
       );

       Assert.assertEquals(musketeer.canEngage(), expected, report);
    }

    @DataProvider(name = "musketeerCanEngageTest")
    public Object[][] musketeerCanEngageTestProvider() {
        return new Object[][]{
                {createMusketeer(true, false, false, 0), false},
                {createMusketeer(false, true, false, 0), false},
                {createMusketeer(false, false, true, 4), false},
                {createMusketeer(false, false, false, 0), true},
                {createMusketeer(false, false, false, 4), true}
        };
    }

    @Test(dataProvider = "musketeerPunchMoveChanceTest", groups = "positive")
    public void getPunchMoveChance(Musketeer musketeer) {
        int chance;
        try{
            chance = (int) getPrivateMethodWithoutArgs(musketeer.getClass(),"getPunchMoveChance").invoke(musketeer);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }

        if(chance < 0 || chance >= 100) {
            Assert.fail(getReport(
                    "punch chance movement in the range [0,100)",
                    String.valueOf(chance),
                    "getPunchMoveChance()"
            ));
        }

    }

    @DataProvider(name = "musketeerPunchMoveChanceTest")
    public Object[][] musketeerPunchMoveChanceTestProvider() {
        return new Object[][]{
                {new Musketeer("Test1", Trait.BRAWLER)},
                {new Musketeer("Test2", Trait.ACCOMPLISHED_DUELIST)}
        };
    }
}

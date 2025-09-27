package location;

import homework3.location.Barracks;
import homework3.location.Plaza;
import org.testng.Assert;
import org.testng.annotations.Test;
import testBase.TestBase;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class PlazaTest extends TestBase {

    @Test(groups = "positive")
    public void hasAmbushPositiveTest() {
        hasAmbushPattern(false);
    }

    @Test(groups = "negative")
    public void hasAmbushNegativeTest() {
        hasAmbushPattern(true);
    }

    public void hasAmbushPattern(boolean guardsmenHaveWounds) {
        boolean actual;
        Plaza plaza = new Plaza(new Barracks());
        setPrivateField(plaza.getClass(), plaza, "guardsmen", new ArrayList<>(List.of(
                createGuardsman(guardsmenHaveWounds),
                createGuardsman(guardsmenHaveWounds),
                createGuardsman(guardsmenHaveWounds),
                createGuardsman(guardsmenHaveWounds)
        )));

        try{
            actual = (boolean) getPrivateMethodWithoutArgs(plaza.getClass(), "hasAmbush").invoke(plaza);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }

        Assert.assertEquals(actual, !guardsmenHaveWounds, "If no one guardsman wounded method hasAmbush() had to return true.");

    }
    
    


}

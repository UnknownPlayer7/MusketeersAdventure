package testBase;

import homework3.enums.Trait;
import homework3.human.Guardsman;
import homework3.human.Musketeer;
import homework3.human.Soldier;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class TestBase {

    public String getReport(String expected, String actual, String method) {
        return String.format(
                """
                
                Expected: %s
                Actual: %s
                In the method: %s
                """, expected, actual, method);
    }

    public Musketeer createMusketeer(boolean isWounded, boolean isSleep, boolean isDrunk, int beerMugCount) {
        Musketeer musketeer = new Musketeer("Test", Trait.BRAWLER);
        setPrivateField(musketeer.getClass(), musketeer, "isWounded", isWounded);
        setPrivateField(musketeer.getClass(), musketeer, "isSleep", isSleep);
        setPrivateField(musketeer.getClass(), musketeer, "isDrunk", isDrunk);
        setPrivateField(musketeer.getClass(), musketeer, "beerMugCount", beerMugCount);
        return musketeer;
    }

    public Guardsman createGuardsman(boolean isWounded) {
        Guardsman guardsman = new Guardsman("Test", Trait.ACCOMPLISHED_DUELIST);
        setPrivateField(guardsman.getClass(), guardsman, "isWounded", isWounded);
        return guardsman;
    }

    public void setPrivateField(Class<?> clazz, Object object, String fieldName, boolean fieldValue) {
        try{
            Field field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(object, fieldValue);

        } catch (NoSuchFieldException e) {
            setPrivateField(clazz.getSuperclass(), object, fieldName, fieldValue);

        } catch (IllegalArgumentException | IllegalAccessException | NullPointerException e) {
            throw new RuntimeException(e);
        }
    }

    public void setPrivateField(Class<?> clazz, Object object, String fieldName, int fieldValue) {
        try{
            Field field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(object, fieldValue);

        } catch (NoSuchFieldException e) {
            setPrivateField(clazz.getSuperclass(), object, fieldName, fieldValue);

        } catch (IllegalArgumentException | IllegalAccessException | NullPointerException e) {
            throw new RuntimeException(e);
        }
    }

    public void setPrivateField(Class<?> clazz, Object object, String fieldName, ArrayList<Soldier> fieldValue) {
        try{
            Field field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(object, fieldValue);

        } catch (NoSuchFieldException e) {
            setPrivateField(clazz.getSuperclass(), object, fieldName, fieldValue);

        } catch (IllegalArgumentException | IllegalAccessException | NullPointerException e) {
            throw new RuntimeException(e);
        }
    }


    public Method getPrivateMethodWithoutArgs(Class<?> clazz, String methodName) {
        try{
            Method method = clazz.getDeclaredMethod(methodName);
            method.setAccessible(true);
            return method;

        } catch (NoSuchMethodException e) {
           return getPrivateMethodWithoutArgs(clazz.getSuperclass(), methodName);

        } catch (NullPointerException e) {
            throw new RuntimeException(e);
        }
    }





}

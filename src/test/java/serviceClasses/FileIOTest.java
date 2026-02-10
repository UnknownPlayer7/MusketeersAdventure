package serviceClasses;

import adventure.enums.Trait;
import adventure.human.Musketeer;
import adventure.serviceClasses.FileIO;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;

public class FileIOTest {

    @AfterMethod
    public void deleteFile() {
        try {
            Files.delete(FileIO.PATH_TO_DEFAULT_MUSKETEERS);
        } catch (Exception e) {
            // DO NOTHING
        }
    }

    //Test is no longe relevant after migration to PostgreSQL
    @Test(enabled = false)
    public void saveToFile() {
        Musketeer expectedMusketeer = new Musketeer("Test", Trait.BRAWLER);
        Musketeer actualMusketeer;
        FileIO.savePlayer(expectedMusketeer);

        try(BufferedReader reader = new BufferedReader(new FileReader(String.valueOf(FileIO.PATH_TO_DEFAULT_MUSKETEERS)))) {
            String name = reader.readLine().trim();
            Trait trait = Trait.valueOf(reader.readLine().trim());
            actualMusketeer = new Musketeer(name, trait);
        }
        catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }

        Assert.assertEquals(actualMusketeer,expectedMusketeer);

    }

    //Test is no longer relevant after migration to PostgreSQL
    @Test(enabled = false)
    public void loadFromFile() {
        Musketeer expectedMusketeer = new Musketeer("Test", Trait.BRAWLER);
        FileIO.savePlayer(expectedMusketeer);
        Musketeer actualMusketeer = FileIO.loadPlayer();

        Assert.assertEquals(actualMusketeer, expectedMusketeer);
    }

}

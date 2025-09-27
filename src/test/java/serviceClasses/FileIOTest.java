package serviceClasses;

import homework3.enums.Trait;
import homework3.human.Musketeer;
import homework3.serviceClasses.FileIO;
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

    @Test(groups = "positive")
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

    @Test(groups = "positive")
    public void loadFromFile() {
        Musketeer expectedMusketeer = new Musketeer("Test", Trait.BRAWLER);
        FileIO.savePlayer(expectedMusketeer);
        Musketeer actualMusketeer = FileIO.loadPlayer();

        Assert.assertEquals(actualMusketeer,expectedMusketeer);
    }

}

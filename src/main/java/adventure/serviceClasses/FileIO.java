package adventure.serviceClasses;

import adventure.enums.ServiceMessage;
import adventure.enums.Trait;
import adventure.human.Musketeer;

import java.io.*;
import java.nio.file.Path;

/*
    Deprecated after migration to PostgreSQL
    File-based storage is no longer used
*/
@Deprecated
public class FileIO {

    public static final Path PATH_TO_DEFAULT_MUSKETEERS = Path.of("src\\main\\resources\\humans\\player.txt");

    public static void savePlayer(Musketeer player) {
        try(FileWriter writer = new FileWriter(PATH_TO_DEFAULT_MUSKETEERS.toString())) {

      //      writer.write(player.getMainInfo());
            System.out.println(ServiceMessage.SAVE_SUCCESS);

        } catch (IOException e) {
            System.out.println(ServiceMessage.SAVE_EXCEPTION);
        }
    }

    public static Musketeer loadPlayer() {
        try(BufferedReader reader = new BufferedReader(new FileReader(String.valueOf(PATH_TO_DEFAULT_MUSKETEERS)))) {
            String name = reader.readLine().trim();
            Trait trait = Trait.valueOf(reader.readLine().trim());

            System.out.println(ServiceMessage.LOAD_SUCCESS);
            return new Musketeer(name, trait);
        }
        catch (Exception e) {
            System.out.println(ServiceMessage.LOAD_EXCEPTION);
            return new PersonCreator().createMusketeer();
        }
    }
}

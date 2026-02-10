package adventure.serviceClasses;

import java.util.List;

public class Locator {

    List<List<String>> table;
    int attributeId;
    int attributeName;
    int attributeTrait;

    public Locator() {
        DBLauncher launcher = new DBLauncher();
        table = launcher.getListOfRows();
        analyzeHeaders();
    }

    public String getAttributeById(int id, String attribute) {
        String value = null;

        switch (attribute) {
            case "name" -> {
                for (int i = 1; i < table.size(); i++) {
                    if(table.get(i).get(attributeId).equals(String.valueOf(id))) {
                        value = table.get(i).get(attributeName);
                    }
                }
            }
            case "trait" -> {
                for (int i = 1; i < table.size(); i++) {
                    if(table.get(i).get(attributeId).equals(String.valueOf(id))) {
                        value = table.get(i).get(attributeTrait);
                    }
                }
            }
        }
        return value;
    }



    private void analyzeHeaders() {
        for (int i = 0; i < table.getFirst().size(); i++) {
            if(table.getFirst().get(i).equals("id")) {
                attributeId = i;
            } else if(table.getFirst().get(i).equals("name")) {
                attributeName = i;
            } else if(table.getFirst().get(i).equals("trait")) {
                attributeTrait = i;
            }
        }
    }

}

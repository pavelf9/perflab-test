
package task3;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Task3 {
    public static void main(String[] args) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        JsonReader valuesFile = new JsonReader(new FileReader("values.json"));
        Values valuesData = gson.fromJson(valuesFile, Values.class);

        JsonReader testsFile = new JsonReader(new FileReader("tests.json"));
        StructureUnit testsData = gson.fromJson(testsFile, StructureUnit.class);

        HashMap<Integer, String> valuesMap = new HashMap<>();
        for (ValuesUnit valuesUnit : valuesData.values) {
            valuesMap.put(valuesUnit.id, valuesUnit.value);
        }

        for (StructureUnit structureUnit : testsData.values) {
            structureUnitParse(structureUnit, valuesMap);
        }

        try (Writer writer = new FileWriter("report.json")) {
            gson.toJson(testsData, writer);
        }
    }

    private static void structureUnitParse(StructureUnit structureUnit, HashMap<Integer, String> valuesMap) {
        structureUnit.value = valuesMap.getOrDefault(structureUnit.id, "");

        List<StructureUnit> structureUnits = structureUnit.values;
        if (structureUnits != null) {
            for (StructureUnit structureUnitChild : structureUnits) {
                structureUnitParse(structureUnitChild, valuesMap);
            }
        }
    }

    private static class StructureUnit {
        int id;
        String title;
        String value;
        List<StructureUnit> values;
    }

    private static class Values {
        List<ValuesUnit> values;
    }

    private static class ValuesUnit {
        int id;
        String value;
    }
}


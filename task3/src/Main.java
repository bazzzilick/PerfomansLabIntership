import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static Map<Integer, String> testMap;

    class Tests {
        public List<Test> tests;
    }

    class Test {
        public int id;
        public String title;
        public String value;
        public List<Test> values;
    }

    class Values {
        public List<Value> values;
    }

    class Value {
        public int id;
        public String value;
    }

    public static void main(String[] args) throws FileNotFoundException {

        //Read tests.json file and add json obj to map
        Gson gsonTest = new Gson();
        Tests tests = gsonTest.fromJson(new FileReader(args[1]), Tests.class);
        testMap = new HashMap<Integer, String>();
        for (Test test : tests.tests) {
            addValuesToMap(test);
        }

        //Read values.json file and update map values
        Gson gsonValue = new Gson();
        Values values = gsonValue.fromJson(new FileReader(args[0]), Values.class);
        for (Value value : values.values) {
            testMap.put(value.id, value.value);
        }

        /*for (Map.Entry<Integer, String> pair : testMap.entrySet()) {
            System.out.println(pair.getKey() + " " + pair.getValue());
        }*/

        //update Test.class json objects
        for (Test test : tests.tests) {
            addValuesToReport(test);
        }

        //Print result to report.json
        try (PrintWriter out = new PrintWriter(new FileWriter("report.json"))) {
            Gson gsonOut = new GsonBuilder().setPrettyPrinting().create();
            String jsonOutString = gsonOut.toJson(tests);
            out.write(jsonOutString);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //update field value in Test.class objects for report
    public static void addValuesToReport(Test test) {
        if (test.values != null) {
            for (Test innerTest : test.values) {
                addValuesToReport(innerTest);
            }
        }
        if (testMap.containsKey(test.id)) {
            test.value = testMap.get(test.id);
        }
    }

    //add all inner json objects to the map
    public static void addValuesToMap(Test test) {
        if (test.values != null) {
            for (Test innerTest : test.values) {
                addValuesToMap(innerTest);
            }
        }
        testMap.put(test.id, "");
    }


}

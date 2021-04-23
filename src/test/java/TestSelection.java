package test.java;

import org.junit.*;
import main.java.*;

import java.util.ArrayList;
import java.util.HashMap;

public class TestSelection {

    @Test
    public void testSelectRows() {
        DataFrame dfcsv = new DataFrame("src/main/resources/testselection.csv");

        // Test select rows from index 1 to index 2
        DataFrame cropped = dfcsv.selectRows(1,2);

        HashMap<String, ArrayList> hash = cropped.getData();

        ArrayList<String> nom = hash.get("nom");

        Assert.assertEquals(nom.get(0), "merlu");
        Assert.assertEquals(nom.get(1), "demarque");

        ArrayList<String> prenom = hash.get("prenom");

        Assert.assertEquals(prenom.get(0), "merou");
        Assert.assertEquals(prenom.get(1), "quentin");

        ArrayList<String> age = hash.get("age");

        Assert.assertEquals(age.get(0), "12");
        Assert.assertEquals(age.get(1), "25");

        // Test select row at 1
        cropped = dfcsv.selectRow(1);

        hash = cropped.getData();

        nom = hash.get("nom");

        Assert.assertEquals(nom.get(0), "merlu");

        prenom = hash.get("prenom");

        Assert.assertEquals(prenom.get(0), "merou");

        age = hash.get("age");

        Assert.assertEquals(age.get(0), "12");

        // Test select rows from beginning to index 1
        cropped = dfcsv.selectRowsTo(1);

        hash = cropped.getData();

        nom = hash.get("nom");

        Assert.assertEquals(nom.get(0), "jean");
        Assert.assertEquals(nom.get(1), "merlu");

        prenom = hash.get("prenom");

        Assert.assertEquals(prenom.get(0), "jean");
        Assert.assertEquals(prenom.get(1), "merou");

        age = hash.get("age");

        Assert.assertEquals(age.get(0), "45");
        Assert.assertEquals(age.get(1), "12");

        // Test select rows from index 2 to the end
        cropped = dfcsv.selectRowsFrom(2);

        hash = cropped.getData();

        nom = hash.get("nom");

        Assert.assertEquals(nom.get(0), "demarque");
        Assert.assertEquals(nom.get(1), "cipriani");

        prenom = hash.get("prenom");

        Assert.assertEquals(prenom.get(0), "quentin");
        Assert.assertEquals(prenom.get(1), "ugo");

        age = hash.get("age");

        Assert.assertEquals(age.get(0), "25");
        Assert.assertEquals(age.get(1), "23");
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testSelectRowsTooLowIndexException() {
        DataFrame dfcsv = new DataFrame("src/main/resources/testselection.csv");

        // Test select rows from index -1 to index 2
        DataFrame cropped = dfcsv.selectRows(-1,2);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testSelectRowsTooHighIndexException() {
        DataFrame dfcsv = new DataFrame("src/main/resources/testselection.csv");

        // Test select rows from index 1 to index 10
        DataFrame cropped = dfcsv.selectRows(1,10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSelectRowsToException() {
        DataFrame dfcsv = new DataFrame("src/main/resources/testselection.csv");

        // Test select rows from the beginning to index -1
        DataFrame cropped = dfcsv.selectRowsTo(-2);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testSelectRowsFromException() {
        DataFrame dfcsv = new DataFrame("src/main/resources/testselection.csv");

        // Test select rows from index 10 to index the end
        DataFrame cropped = dfcsv.selectRowsTo(10);
    }

    @Test
    public void testSelectColumns() {
        DataFrame dfcsv = new DataFrame("src/main/resources/testselection.csv");

        // Test select columns
        DataFrame cropped = null;
        try {
            cropped = dfcsv.selectColumns("prenom", "age");
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        HashMap<String, ArrayList> hash = cropped.getData();

        ArrayList<String> prenom = hash.get("prenom");

        Assert.assertEquals(prenom.get(0), "jean");
        Assert.assertEquals(prenom.get(1), "merou");
        Assert.assertEquals(prenom.get(2), "quentin");
        Assert.assertEquals(prenom.get(3), "ugo");

        ArrayList<String> age = hash.get("age");

        Assert.assertEquals(age.get(0), "45");
        Assert.assertEquals(age.get(1), "12");
        Assert.assertEquals(age.get(2), "25");
        Assert.assertEquals(age.get(3), "23");
    }

    @Test(expected = NullPointerException.class)
    public void testSelectColumnsException() {
        DataFrame dfcsv = new DataFrame("src/main/resources/testselection.csv");

        // Test select columns
        DataFrame cropped = dfcsv.selectColumns("prenom", "surnom");
    }

    @Test
    public void testAdvancedSelect() {
        ArrayList<Integer> firstLvlData1 = new ArrayList<>();
        firstLvlData1.add(1);
        firstLvlData1.add(2);
        firstLvlData1.add(3);

        ArrayList<Integer> firstLvlData2 = new ArrayList<>();
        firstLvlData2.add(4);
        firstLvlData2.add(5);
        firstLvlData2.add(6);

        HashMap<String, ArrayList<Integer>> secondLvlData = new HashMap<>();
        secondLvlData.put("one", firstLvlData1);
        secondLvlData.put("two", firstLvlData2);

        ArrayList<HashMap<String, ArrayList<Integer>>> thirdLvlData = new ArrayList<>();
        thirdLvlData.add(secondLvlData);

        CoupleLabelData data1 = new CoupleLabelData(thirdLvlData, "foo");
        CoupleLabelData data2 = new CoupleLabelData(thirdLvlData, "bar");
        CoupleLabelData data3 = new CoupleLabelData(thirdLvlData, "baz");

        DataFrame advancedDateFrame = new DataFrame(data1, data2, data3);

        DataFrame cropped = advancedDateFrame.advancedSelect("foo", "two");

        ArrayList list = cropped.getData().get("two");

        Assert.assertEquals(4, list.get(0));
        Assert.assertEquals(5, list.get(1));
        Assert.assertEquals(6, list.get(2));

        // Select columns foo->two and row index 1
        cropped = advancedDateFrame.advancedSelect(1,"foo", "two");

        list = cropped.getData().get("two");

        Assert.assertEquals(5, list.get(0));

        // Select columns foo->two and row indexes from 1 to 2
        cropped = advancedDateFrame.advancedSelect(1, 2,"foo", "two");

        list = cropped.getData().get("two");

        Assert.assertEquals(5, list.get(0));
        Assert.assertEquals(6, list.get(1));
    }
}

package test.java;

import org.junit.*;
import main.java.*;

import java.util.ArrayList;
import java.util.HashMap;

public class TestSelection {

    @Test
    public void testSelectRaws() {
        DataFrame dfcsv = new DataFrame("src/main/resources/testselection.csv");

        // Test select raws from index 1 to index 2
        DataFrame cropped = null;
        try {
            cropped = dfcsv.selectRaws(1,2);
        } catch (InvalidRawIndexException e) {
            e.printStackTrace();
        }

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

        // Test select raws from beginning to index 1
        try {
            cropped = dfcsv.selectRawsTo(1);
        } catch (InvalidRawIndexException e) {
            e.printStackTrace();
        }

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

        // Test select raws from index 2 to the end
        try {
            cropped = dfcsv.selectRawsFrom(2);
        } catch (InvalidRawIndexException e) {
            e.printStackTrace();
        }

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

    @Test(expected = InvalidRawIndexException.class)
    public void testSelectRawsTooLowIndexException() {
        DataFrame dfcsv = new DataFrame("src/main/resources/testselection.csv");

        // Test select raws from index 1 to index 2
        try {
            DataFrame cropped = dfcsv.selectRaws(-1,2);
        } catch (InvalidRawIndexException e) {
            e.printStackTrace();
        }
    }

    @Test(expected = InvalidRawIndexException.class)
    public void testSelectRawsTooHighIndexException() {
        DataFrame dfcsv = new DataFrame("src/main/resources/testselection.csv");

        // Test select raws from index 1 to index 2
        try {
            DataFrame cropped = dfcsv.selectRaws(1,10);
        } catch (InvalidRawIndexException e) {
            e.printStackTrace();
        }
    }

    @Test(expected = InvalidRawIndexException.class)
    public void testSelectRawsToException() {
        DataFrame dfcsv = new DataFrame("src/main/resources/testselection.csv");

        // Test select raws from index 1 to index 2
        try {
            DataFrame cropped = dfcsv.selectRawsTo(0);
        } catch (InvalidRawIndexException e) {
            e.printStackTrace();
        }
    }

    @Test(expected = InvalidRawIndexException.class)
    public void testSelectRawsFromException() {
        DataFrame dfcsv = new DataFrame("src/main/resources/testselection.csv");

        // Test select raws from index 1 to index 2
        try {
            DataFrame cropped = dfcsv.selectRawsTo(10);
        } catch (InvalidRawIndexException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSelectColumns() {
        DataFrame dfcsv = new DataFrame("src/main/resources/testselection.csv");

        // Test select raws
        DataFrame cropped = null;
        try {
            cropped = dfcsv.selectColumns("prenom", "age");
        } catch (InvalidColumnLabelException e) {
            e.printStackTrace();
        }

        HashMap<String, ArrayList> hash = cropped.getData();

        ArrayList<String> prenom = hash.get("prenom");

        Assert.assertEquals(prenom.get(0), "jean");
        Assert.assertEquals(prenom.get(1), "merou");
        Assert.assertEquals(prenom.get(0), "quentin");
        Assert.assertEquals(prenom.get(1), "ugo");

        ArrayList<String> age = hash.get("age");

        Assert.assertEquals(age.get(0), "45");
        Assert.assertEquals(age.get(1), "12");
        Assert.assertEquals(age.get(0), "25");
        Assert.assertEquals(age.get(1), "23");
    }

    @Test(expected = InvalidColumnLabelException.class)
    public void testSelectColumnsException() {
        DataFrame dfcsv = new DataFrame("src/main/resources/testselection.csv");

        // Test select raws
        try {
            DataFrame cropped = dfcsv.selectColumns("prenom", "surnom");
        } catch (InvalidColumnLabelException e) {
            e.printStackTrace();
        }
    }
}

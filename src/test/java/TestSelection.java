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
        DataFrame cropped = dfcsv.selectRaws(1,2);

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
        cropped = dfcsv.selectRawsTo(1);

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
        cropped = dfcsv.selectRawsFrom(2);

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
}

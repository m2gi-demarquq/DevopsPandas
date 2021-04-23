package test.java;

import org.junit.*;
import main.java.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

/***
 * test des 2 constructeurs de la classe DataFrame
 */
public class TestConstructor {

    @Test
    public void test(){
        DataFrame dfcsv = new DataFrame("src/main/resources/testconst.csv");

        LinkedHashMap<String, ArrayList> hash = dfcsv.getData();

        Assert.assertTrue(hash.containsKey("nom"));
        Assert.assertTrue(hash.containsKey("prenom"));
        Assert.assertTrue(hash.containsKey("age"));

        ArrayList<String> nom = hash.get("nom");

        Assert.assertEquals(nom.get(0), "jean");
        Assert.assertEquals(nom.get(1), "merlu");

        ArrayList<String> prenom = hash.get("prenom");

        Assert.assertEquals(prenom.get(0), "jean");
        Assert.assertEquals(prenom.get(1), "merou");

        ArrayList<Integer> age = hash.get("age");

        int current = age.get(0);
        int current2 = age.get(1);

        Assert.assertEquals(current, 45);
        Assert.assertEquals(current2, 12);

        ArrayList<Double> money = hash.get("moneyyss");

        double currentm = money.get(0);
        double currentm2 = money.get(1);


        Assert.assertEquals(currentm, 0.002, 0);
        Assert.assertEquals(currentm2, 10.52, 0);


        CoupleLabelData cd1 = new CoupleLabelData(nom, "nom");
        CoupleLabelData cd2 = new CoupleLabelData(prenom, "prenom");

        ArrayList<Integer> ageint = new ArrayList();
        ageint.add(10);
        ageint.add(25);

        CoupleLabelData cd3 = new CoupleLabelData(ageint, "age");

        DataFrame df = new DataFrame(cd1, cd2, cd3);

        HashMap<String, ArrayList> hash2 = df.getData();

        Assert.assertTrue(hash.containsKey("nom"));
        Assert.assertTrue(hash.containsKey("prenom"));
        Assert.assertTrue(hash.containsKey("age"));

        ArrayList<String> nom2 = hash2.get("nom");

        Assert.assertEquals(nom2.get(0), "jean");
        Assert.assertEquals(nom2.get(1), "merlu");

        ArrayList<String> prenom2 = hash2.get("prenom");

        Assert.assertEquals(prenom2.get(0), "jean");
        Assert.assertEquals(prenom2.get(1), "merou");

        ArrayList<String> age2 = hash2.get("age");

        Assert.assertEquals(age2.get(0), 10);
        Assert.assertEquals(age2.get(1), 25);
    }
}

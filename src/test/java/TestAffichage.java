package test.java;

import main.java.DataFrame;
import org.junit.Test;
import org.junit.Assert;

public class TestAffichage {


        @Test
        public void test(){

            DataFrame dfcsv = new DataFrame("src/main/resources/testconst.csv");

            int size = dfcsv.getData().entrySet().size();

            for(int i = 0; i<1000; i++){
                Assert.assertEquals("nom,prenom,age,moneyyss,test,test2\n",dfcsv.printPart(0, 0, 0, size));
            }


            String expected = "nom,prenom,age,moneyyss,test,test2\n" +
                    "jean,jean,45,0.002,ola\n"+
                    "merlu,merou,12,10.52\n";

            Assert.assertEquals(expected, dfcsv.Print_all());

        }
}

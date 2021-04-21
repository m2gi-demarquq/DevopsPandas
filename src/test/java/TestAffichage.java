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

            String expected_until = "nom,prenom,age,moneyyss,test,test2\n" +
                    "jean,jean,45,0.002,ola\n";

            String expected_from = "jean,jean,45,0.002,ola\n"+
                    "merlu,merou,12,10.52\n";

            Assert.assertEquals(expected, dfcsv.Print_all());
            Assert.assertEquals(expected_until, dfcsv.Print_until(1));
            Assert.assertEquals(expected_from, dfcsv.Print_from(1));

            String expected_col = "prenom,age,moneyyss,test,test2\n" +
                    "jean,45,0.002,ola\n"+
                    "merou,12,10.52\n";

            String expected_col_rm_lst = "nom,prenom,age,moneyyss,test\n" +
                    "jean,jean,45,0.002,ola\n"+
                    "merlu,merou,12,10.52\n";

            Assert.assertEquals(expected_col, dfcsv.printPart(0, dfcsv.getbiggestArraysize(), 1, dfcsv.getData().keySet().size()));
            Assert.assertEquals(expected_col_rm_lst, dfcsv.printPart(0, dfcsv.getbiggestArraysize(), 0, dfcsv.getData().keySet().size() - 1));



        }
}

package test.java;

import main.java.DataFrame;
import org.junit.Test;
import org.junit.Assert;

public class TestStats {

    @Test
    public void test(){

        DataFrame dfcsv = new DataFrame("src/main/resources/testStats.csv");

        Assert.assertEquals(dfcsv.minColumn("age"), 2, 0.0);
        Assert.assertEquals(dfcsv.maxColumn("age"), 9, 0.0);

        Assert.assertEquals(dfcsv.maxColumn("prix"), 123456.0, 0.0);
        Assert.assertEquals(dfcsv.minColumn("prix"), 12.0, 0.0);

        Assert.assertEquals(dfcsv.average("annee"), 2005, 0.0);
        Assert.assertEquals(dfcsv.average("marge"), 0.2, 0.0000001);

        Assert.assertEquals(dfcsv.medianeColumn("annee"), 2005, 0.0);
        Assert.assertEquals(dfcsv.medianeColumn("marge"), 0.2, 0.0000001);

        Assert.assertNull(dfcsv.medianeColumn("polà"));
        Assert.assertNull(dfcsv.minColumn("polà"));
        Assert.assertNull(dfcsv.maxColumn("polà"));
        Assert.assertNull(dfcsv.average("polà"));

        Assert.assertNull(dfcsv.medianeColumn("test"));
        Assert.assertNull(dfcsv.minColumn("test"));
        Assert.assertNull(dfcsv.maxColumn("test"));
        Assert.assertNull(dfcsv.average("test"));








    }

}

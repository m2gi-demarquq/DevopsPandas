package main.java;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;


/***
 *
 */
public class DataFrame {

    private HashMap<String, ArrayList> data;

    public HashMap<String, ArrayList> getData() {
        return data;
    }

    /***
     *
     * @param couples chaque couple correspond à une colonne du dataframe avec un label et des données
     */
    public DataFrame (CoupleLabelData ... couples){
        this.data = new HashMap<>();
        for(CoupleLabelData couple : couples){
            this.data.put(couple.getLabel(), couple.getData());
        }
    }

    /***
     *
     * @param CSV chemin vers le fichier csv à utiliser
     */
    public DataFrame (String CSV){
        try {
            FileReader filereader = new FileReader(CSV);
            this.data = new HashMap<>();


            CSVReader csvReader = new CSVReaderBuilder(filereader).build();
            List<String[]> allData = csvReader.readAll();
            String[] labels = allData.get(0);


            for (int y = 0; y < labels.length; y++) {
                ArrayList<String> list = new ArrayList<>();
                for (int i = 1; i<allData.size(); i++) {
                    list.add(allData.get(i)[y]);
                }
                data.put(labels[y], list);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /***
     * Select the row with the index at
     * @param at
     * @return DataFrame
     */
    public DataFrame selectRow(int at) {
        DataFrame res = new DataFrame();

        for (String key : this.data.keySet()) {
            CoupleLabelData tmp = new CoupleLabelData(new ArrayList(this.data.get(key).subList(at, at + 1)), key);
            res.addColumn(tmp);
        }
        return res;
    }

    /***
     * Select the rows in between the from-th and to-th rows (included)
     * @param from
     * @param to
     * @return DataFrame
     */
    public DataFrame selectRows(int from, int to) {
        DataFrame res = new DataFrame();

        for (String key : this.data.keySet()) {
            CoupleLabelData tmp = new CoupleLabelData(new ArrayList(this.data.get(key).subList(from, to + 1)), key);
            res.addColumn(tmp);
        }
        return res;
    }

    /***
     * Select the rows in between the beginning and the to-th row (included)
     * @param to
     * @return DataFrame
     */
    public DataFrame selectRowsTo(int to) {
        DataFrame res = new DataFrame();

        for(String key : this.data.keySet()) {
            CoupleLabelData tmp = new CoupleLabelData(new ArrayList(this.data.get(key).subList(0, to + 1)), key);
            res.addColumn(tmp);
        }
        return res;
    }

    /***
     * Select the rows in between the from-th row and the end (included)
     * @param from
     * @return DataFrame
     */
    public DataFrame selectRowsFrom(int from) {
        DataFrame res = new DataFrame();

        for(String key : this.data.keySet()) {
            CoupleLabelData tmp = new CoupleLabelData(new ArrayList(this.data.get(key).subList(from, this.data.get(key).size())), key);
            res.addColumn(tmp);
        }
        return res;
    }

    /***
     * Select the columns with the label given
     * @param labels
     * @return DataFrame
     */
    public DataFrame selectColumns(String ... labels) {
        DataFrame res = new DataFrame();

        for(String label : labels) {
            CoupleLabelData tmp = new CoupleLabelData(new ArrayList(this.data.get(label)), label);
            res.addColumn(tmp);
        }
        return res;
    }

    private HashMap goDownIn(String label) {
        return (HashMap) this.data.get(label).get(0);
    }

    public DataFrame advancedSelect(String ... labels) {
        DataFrame res = new DataFrame();

        HashMap tmp = this.data;
        int depth = labels.length;
        Object[] allLabels = Arrays.stream(labels).toArray();
        int i = 0;

        while(i < depth - 1) {
            tmp = (HashMap) this.data.get(allLabels[i]).get(0);
            i++;
        }

        res.addColumn(new CoupleLabelData(new ArrayList((ArrayList) tmp.get(allLabels[i])), (String) allLabels[i]));

        return res;
    }

    public DataFrame advancedSelect(int row, String ... labels) {
        return advancedSelect(labels).selectRow(row);
    }

    public DataFrame advancedSelect(int from, int to, String ... labels) {
        return advancedSelect(labels).selectRows(from, to);
    }

    private void addColumn(CoupleLabelData couple){
        this.data.put(couple.getLabel(), couple.getData());
    }

    @Override
    public String toString() {
        return "";
    }
}

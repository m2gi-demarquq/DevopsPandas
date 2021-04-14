package main.java;

import java.io.FileReader;
import java.util.ArrayList;
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

    @Override
    public String toString() {
        return "";
    }
}

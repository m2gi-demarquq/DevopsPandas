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

    public DataFrame selectRaws(int from, int to) throws InvalidRawIndexException {
        DataFrame res = new DataFrame();

        for (String key : this.data.keySet()) {
            if(from >= 0 && from < to && to < this.data.get(key).size()) {
                CoupleLabelData tmp = new CoupleLabelData(new ArrayList(this.data.get(key).subList(from, to + 1)), key);
                res.addColumn(tmp);
            } else {
                throw new InvalidRawIndexException("Exception caught on selectRaws(" + from + ", " + to + ") :");
            }
        }
        return res;
    }

    public DataFrame selectRawsTo(int to) throws InvalidRawIndexException {
        DataFrame res = new DataFrame();

        for(String key : this.data.keySet()) {
            if(to > 0 && to < this.data.get(key).size()) {
                CoupleLabelData tmp = new CoupleLabelData(new ArrayList(this.data.get(key).subList(0, to + 1)), key);
                res.addColumn(tmp);
            } else {
                throw new InvalidRawIndexException("Exception caught on selectRawsTo(" + to +") : ");
            }
        }
        return res;
    }

    public DataFrame selectRawsFrom(int from) throws InvalidRawIndexException {
        DataFrame res = new DataFrame();

        for(String key : this.data.keySet()) {
            if(from >= 0 && from < this.data.get(key).size() - 1) {
                CoupleLabelData tmp = new CoupleLabelData(new ArrayList(this.data.get(key).subList(from, this.data.get(key).size())), key);
                res.addColumn(tmp);
            } else {
                throw new InvalidRawIndexException("Exception caught on selectRawsFrom(" + from +") : ");
            }
        }
        return res;
    }

    public DataFrame selectColumns(String ... labels) throws InvalidColumnLabelException {
        DataFrame res = new DataFrame();

        for(String label : labels) {
            if(this.data.containsKey(label)) {
                CoupleLabelData tmp = new CoupleLabelData(new ArrayList(this.data.get(label)), label);
                res.addColumn(tmp);
            } else {
                throw new InvalidColumnLabelException("Exception caught on selectColumns(" + labels +") : ");
            }
        }
        return res;
    }

    private void addColumn(CoupleLabelData couple){
        this.data.put(couple.getLabel(), couple.getData());
    }

    @Override
    public String toString() {
        return "";
    }
}

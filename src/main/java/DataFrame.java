package main.java;

import java.io.FileReader;
import java.util.*;
import java.util.Collections;
import java.util.Map.Entry;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;


/***
 *
 */
public class DataFrame {

    private LinkedHashMap<String, ArrayList> data;

    public LinkedHashMap<String, ArrayList> getData() {
        return data;
    }

    /***
     *
     * @param couples chaque couple correspond à une colonne du dataframe avec un label et des données
     */
    public DataFrame (CoupleLabelData ... couples){
        this.data = new LinkedHashMap<>();
        for(CoupleLabelData couple : couples){
            this.data.put(couple.getLabel(), couple.getData());
        }
    }

    private static String whichType(String input) {
        try {
            Integer.parseInt(input);
            return "INT";
        } catch (final NumberFormatException e) {
            try{
                Double.parseDouble(input);
                return "double";
            } catch (Exception exception) {
                return "String";
            }

        }
    }

    /***
     *
     * @param CSV chemin vers le fichier csv à utiliser
     */
    public DataFrame (String CSV){
        try {
            FileReader filereader = new FileReader(CSV);
            this.data = new LinkedHashMap<>();


            CSVReader csvReader = new CSVReaderBuilder(filereader).build();
            List<String[]> allData = csvReader.readAll();
            String[] labels = allData.get(0);

            String type = "";

            for (int y = 0; y < labels.length; y++) {
                try{
                    type = whichType(allData.get(1)[y]);
                    //the column is empty and only has a label
                } catch (IndexOutOfBoundsException e) {

                }


                switch(type){
                    case "INT":
                        ArrayList<Integer> listI = new ArrayList<>();
                        for (int i = 1; i<allData.size(); i++) {
                            try{
                                listI.add(Integer.parseInt(allData.get(i)[y]));
                            } catch (IndexOutOfBoundsException e) {
                                listI.add(null);
                            }

                        }
                        data.put(labels[y], listI);
                        break;
                    case "double":
                        ArrayList<Double> listf = new ArrayList<>();
                        for (int i = 1; i<allData.size(); i++) {
                            try{
                                listf.add(Double.parseDouble(allData.get(i)[y]));
                            } catch (IndexOutOfBoundsException e) {
                                listf.add(null);
                            }
                        }
                        data.put(labels[y], listf);
                        break;
                    case "String":
                        ArrayList<String> listS = new ArrayList<>();
                        for (int i = 1; i<allData.size(); i++) {
                            try{
                                listS.add(allData.get(i)[y]);
                            } catch (IndexOutOfBoundsException e) {
                                listS.add(null);
                            }

                        }
                        data.put(labels[y], listS);
                        break;
                }


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

    private void addColumn(CoupleLabelData couple) {
        this.data.put(couple.getLabel(), couple.getData());
    }

    @Override
    public String toString() {
        return "";
    }
  
    public int getbiggestArraysize() {
        int max = 0;
        for (Map.Entry<String, ArrayList> entry: data.entrySet()) {
            int curr = entry.getValue().size();
            if(curr > max) {
                max = curr;
            }
        }
        return max;
    }

    public String printPart(int start, int finish, int startcol, int finishcol) {
        String ret = "";
        int max = this.getbiggestArraysize();
        String[] keys = data.keySet().toArray(String[]::new);

        if (start == 0) {
            for(int col = startcol; col<finishcol; col++) {
                ret += keys[col] + ",";

            }
            // we remove the last ','
            ret = ret.substring(0, ret.length() - 1);
            ret += "\n";
            start ++;
        }

        for(int lig = start - 1; lig < finish; lig++) {
            for(int col = startcol; col < finishcol; col++) {
                Object add = data.get(keys[col]).get(lig);
                if(add == null) {
                    ret += "";
                } else {
                    ret += add + ",";
                }
            }
            ret = ret.substring(0, ret.length() - 1);
            ret += "\n";
        }
        return ret;
    }

    /***
     *
     * @return the dataframe
     */
    public String printAll() {
        return printPart(0, this.getbiggestArraysize(), 0, data.keySet().size());
    }

    /***
     *
     * @param lastLine the last line printed
     * @return the dataframe
     */
    public String printUntil(int lastLine) {
        if(lastLine > this.getbiggestArraysize()) {
            return printPart(0, this.getbiggestArraysize(), 0, data.keySet().size());
        }
        return printPart(0, lastLine, 0, data.keySet().size());
    }

    public String printFrom(int firstline) {
        if(firstline < 0) {
            return printPart(0, this.getbiggestArraysize(), 0, data.keySet().size());
        }
        return printPart(firstline, this.getbiggestArraysize(), 0, data.keySet().size());
    }

    /***
     *
     * @param label
     * @return la mediane d'une colonne
     */
    public Double average(String label){
        double ret = 0;
        ArrayList array = data.get(label);
        if(array == null){
            System.err.println("label  : "+ label + " does not exist");
            return null;
        }
        if(array.get(0) instanceof Double){
            for(int i = 0; i < array.size(); i++) {
                ret += (double) array.get(i);
            }
        }else if(array.get(0) instanceof Integer) {
            for (int i = 0; i < array.size(); i++) {
                ret += (double)(int) array.get(i);
            }
        }
        else{
            System.err.println("the column is a column of strings");
            return null;
        }

        return ret/array.size();
    }

    /***
     *
     * @param label
     * @return le max d'une colonne
     */
    public Double maxColumn (String label){
        Double ret = - Double.MAX_VALUE;
        ArrayList array = data.get(label);
        if(array == null){
            System.err.println("label  : "+ label + " does not exist");
            return null;
        }
        if(array.get(0) instanceof Double){
            for(int i = 0; i < array.size(); i++) {
                if((double)array.get(i) > ret){
                    ret = (Double)array.get(i);
                }
            }
        }else if(array.get(0) instanceof Integer){
            for(int i = 0; i < array.size(); i++) {
                if((int)array.get(i) > ret){
                    ret = (double)(int)array.get(i);
                }
            }
        }
        else{
            System.err.println("the column is a column of strings");
            return null;
        }
        return ret;
    }

    /***
     *
     * @param label
     * @return le min d'une colonne
     */
    public Double minColumn (String label){
        Double ret = Double.MAX_VALUE;
        ArrayList array = data.get(label);
        if(array == null){
            System.err.println("label  : "+ label + " does not exist");
            return null;
        }
        if(array.get(0) instanceof Double){
            for(int i = 0; i < array.size(); i++) {
                if((double)array.get(i) < ret){
                    ret = (Double)array.get(i);
                }
            }
        }else if(array.get(0) instanceof Integer){
            for(int i = 0; i < array.size(); i++) {
                if((int)array.get(i) < ret){
                    ret = (double)(int)array.get(i);
                }
            }
        }
        else{
            System.err.println("the column is a column of strings");
            return null;
        }
        return ret;
    }

    /***
     *
     * @param label
     * @return la mediane d'une colonne
     */
    public Double medianColumn(String label){
        Double ret = 0.0;

        if(data.get(label) == null){
            System.err.println("label  : "+ label + " does not exist");
            return null;
        }
        ArrayList array = (ArrayList) data.get(label).clone();

        if(array.get(0) instanceof Double){
            Collections.sort(array);
            return (double)array.get(array.size()/2);
        }
        if(array.get(0) instanceof Integer){
            Collections.sort(array);
            return (double)(int)array.get(array.size()/2);
        }else{
            System.err.println("the column is a column of strings");
            return null;
        }

    }


}

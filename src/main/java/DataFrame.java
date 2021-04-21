package main.java;

import java.io.FileReader;
import java.util.*;
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

    public int getbiggestArraysize(){
        int max = 0;
        for (Map.Entry<String, ArrayList> entry: data.entrySet()) {
            int curr = entry.getValue().size();
            if(curr > max){
                max = curr;
            }
        }
        return max;
    }

    public String printPart(int start, int finish, int startcol, int finishcol){
        String ret = "";
        int max = this.getbiggestArraysize();
        String[] keys = data.keySet().toArray(String[]::new);

        if (start == 0){
            for(int col = startcol; col<finishcol; col++){
                ret += keys[col] + ",";

            }
            // we remove the last ','
            ret = ret.substring(0, ret.length() - 1);
            ret += "\n";
            start ++;
        }

        for(int lig = start - 1; lig < finish; lig++){
            for(int col = startcol; col < finishcol; col++){
                Object add = data.get(keys[col]).get(lig);
                if(add == null){
                    ret += "";
                }else{
                    ret += add + ",";
                }

            }
            ret = ret.substring(0, ret.length() - 1);
            ret += "\n";
        }

        return ret;
    }

    /***
     * print all the dataframe
     */
    public String Print_all() {

        return printPart(0, this.getbiggestArraysize(), 0, data.keySet().size());

    }
}

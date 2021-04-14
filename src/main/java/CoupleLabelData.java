package main.java;

import java.util.ArrayList;

public class CoupleLabelData {

    private ArrayList data;
    private String label;

    public CoupleLabelData(ArrayList data, String label) {
        this.data = data;
        this.label = label;
    }

    public ArrayList getData() {
        return data;
    }

    public String getLabel() {
        return label;
    }
}

package com.joelcastro.eligetupropiaaventura.models;

import java.util.ArrayList;

/**
 * Created by joel on 13/10/14.
 */
public class AdventureHistory {

    String adventureName;
    ArrayList<Integer> nodesList;

    public String getAdventureName() {
        return adventureName;
    }

    public void setAdventureName(String adventureName) {
        this.adventureName = adventureName;
    }

    public ArrayList<Integer> getNodesList() {
        return nodesList;
    }

    public void setNodesList(ArrayList<Integer> nodesList) {
        this.nodesList = nodesList;
    }
}

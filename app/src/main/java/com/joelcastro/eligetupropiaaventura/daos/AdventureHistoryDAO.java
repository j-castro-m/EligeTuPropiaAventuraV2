package com.joelcastro.eligetupropiaaventura.daos;

import com.joelcastro.eligetupropiaaventura.models.AdventureNode;

import java.util.List;

/**
 * Created by joel on 13/10/14.
 */
public interface AdventureHistoryDAO {

    public void addAdventureNodeToHistory(String player, int idNode, String nameAdventure,String status);
    public void changeStatus(String player,String nameAdventure);
    public String checkStatus(String player,String nameAdventure);
    public List<AdventureNode> getNodesFromAdventure(String player, String nameAdventure);
}

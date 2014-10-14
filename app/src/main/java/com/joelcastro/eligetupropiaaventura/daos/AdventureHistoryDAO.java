package com.joelcastro.eligetupropiaaventura.daos;

import com.joelcastro.eligetupropiaaventura.models.AdventureNode;

import java.util.List;

/**
 * Created by joel on 13/10/14.
 */
public interface AdventureHistoryDAO {

    public String addAdventureNodeToHistory(String player, int idNode, String nameAdventure);
    public List<AdventureNode> getNodesFromAdventure(String player, String nameAdventure);
}

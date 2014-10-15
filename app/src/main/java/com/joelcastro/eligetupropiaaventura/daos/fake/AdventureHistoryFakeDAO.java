package com.joelcastro.eligetupropiaaventura.daos.fake;

import com.joelcastro.eligetupropiaaventura.daos.AdventureHistoryDAO;
import com.joelcastro.eligetupropiaaventura.models.AdventureNode;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by joel on 14/10/14.
 */
public class AdventureHistoryFakeDAO implements AdventureHistoryDAO {
    @Override
    public void addAdventureNodeToHistory(String player, int idNode, String nameAdventure) {

    }

    @Override
    public List<AdventureNode> getNodesFromAdventure(String player, String nameAdventure) {
        AdventureNodeFakeDAO nodeDAO = new AdventureNodeFakeDAO();

        List<AdventureNode> list = new ArrayList<AdventureNode>();
        list.add(nodeDAO.getNodeFromId(1));
        list.add(nodeDAO.getNodeFromId(6));
        list.add(nodeDAO.getNodeFromId(7));
        return list;
    }
}

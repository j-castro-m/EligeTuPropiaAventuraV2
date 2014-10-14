package com.joelcastro.eligetupropiaaventura.daos.fake;

import com.joelcastro.eligetupropiaaventura.daos.AdventureHistoryDAO;
import com.joelcastro.eligetupropiaaventura.models.AdventureNode;

import java.util.List;

/**
 * Created by joel on 14/10/14.
 */
public class AdventureHistoryFakeDAO implements AdventureHistoryDAO {
    @Override
    public String addAdventureNodeToHistory(String player, int idNode, String nameAdventure) {
        return null;
    }

    @Override
    public List<AdventureNode> getNodesFromAdventure(String player, String nameAdventure) {
        return null;
    }
}

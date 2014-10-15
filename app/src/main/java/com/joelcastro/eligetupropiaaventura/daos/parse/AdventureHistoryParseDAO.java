package com.joelcastro.eligetupropiaaventura.daos.parse;

import com.joelcastro.eligetupropiaaventura.daos.AdventureHistoryDAO;
import com.joelcastro.eligetupropiaaventura.daos.AdventureNodeDAO;
import com.joelcastro.eligetupropiaaventura.models.Adventure;
import com.joelcastro.eligetupropiaaventura.models.AdventureNode;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import org.androidannotations.annotations.EBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by joel on 14/10/14.
 */
@EBean
public class AdventureHistoryParseDAO implements AdventureHistoryDAO {
    @Override
    public void addAdventureNodeToHistory(String player, int idNode, String nameAdventure) {
        ParseQuery<ParseObject > query = ParseQuery.getQuery("Historic");
        String identifier = "0";
        ParseObject historicParse = new ParseObject("Historic");

        try {
            historicParse.put("idHistory", String.valueOf(query.count() + 1));
            identifier = String.valueOf(query.count() + 1);

        }catch (ParseException ex){

        }

        historicParse.put("idPlayer", player);
        historicParse.put("adventureName", nameAdventure);
        historicParse.put("idNode", idNode);
        historicParse.saveInBackground();
        //return identifier;
    }

    @Override
    public List<AdventureNode> getNodesFromAdventure(String player, String nameAdventure) {
        AdventureNodeParseDAO nodeDAO = new AdventureNodeParseDAO();
        List<AdventureNode> adventureNodeList = new ArrayList<AdventureNode>();

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Historic");
        query.whereEqualTo("idPlayer", player);
        query.whereEqualTo("adventureName", nameAdventure);
        try {
            for (ParseObject parseObject : query.find()) {
                adventureNodeList.add(nodeDAO.getNodeFromId(parseObject.getInt("idNode")));
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return adventureNodeList;
    }
}

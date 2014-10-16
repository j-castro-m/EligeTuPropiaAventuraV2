package com.joelcastro.eligetupropiaaventura.daos.parse;

import com.joelcastro.eligetupropiaaventura.daos.AdventureHistoryDAO;
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
    public void addAdventureNodeToHistory(String player, int idNode, String nameAdventure,String status) {
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
        historicParse.put("status", status);
        historicParse.saveInBackground();
        //return identifier;
    }

    @Override
    public void changeStatus(String player, String nameAdventure) {

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Historic");
        query.whereMatches("idPlayer",player);
        query.whereMatches("adventureName",nameAdventure);
        query.whereMatches("status", "Searching");
        try {
            ParseObject usuario = query.getFirst();
            for (ParseObject parseObject : query.find()) {
                usuario.put("status","Found");
                usuario.saveInBackground();
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String checkStatus(String player, String nameAdventure) {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Historic");
        query.whereMatches("idPlayer",player)
                .whereMatches("adventureName",nameAdventure)
                .whereMatches("status","Searching");
        try {
            ParseObject history = query.getFirst();
            return "Searching";
        } catch (ParseException e) {
            return "Found";
        }
    }

    @Override
    public List<AdventureNode> getNodesFromAdventure(String player, String nameAdventure) {

        List<AdventureNode> adventureNodeList = new ArrayList<AdventureNode>();

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Historic");
        query.whereEqualTo("idPlayer", player);
        query.whereEqualTo("adventureName", nameAdventure);
        try {
            for (ParseObject parseObject : query.find()) {
                adventureNodeList.add(getNodeFromId(parseObject.getInt("idNode")));
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return adventureNodeList;
    }

    private AdventureNode getNodeFromId(int idNode) {
        AdventureNode adventureNode = new AdventureNode();

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Nodes");
        query.whereEqualTo("idNode", idNode);
        try {
            for (ParseObject parseObject : query.find()) {
                adventureNode.setId(idNode);
                adventureNode.setTexto(parseObject.getString("texto"));
                adventureNode.setGPS(parseObject.getString("GPS"));
                adventureNode.setTitulo(parseObject.getString("titulo"));
                adventureNode.setSiguienteNodoId1(parseObject.getInt("SiguienteNodoId1"));
                adventureNode.setSiguienteNodoId2(parseObject.getInt("SiguienteNodoId2"));
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return adventureNode;
    }
}

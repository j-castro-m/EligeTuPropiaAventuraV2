package com.joelcastro.eligetupropiaaventura.daos.parse;

import com.joelcastro.eligetupropiaaventura.daos.AdventureDAO;
import com.joelcastro.eligetupropiaaventura.models.Adventure;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by joel on 12/10/14.
 */
public class AdventureParseDAO implements AdventureDAO{
    @Override
    public List<Adventure> getAllAdventures() {
        List<Adventure> adventuresList = new ArrayList<Adventure>();

        ParseQuery<ParseObject> query = ParseQuery.getQuery("AdventuresList");
        try {
            for (ParseObject parseObject : query.find()) {
                Adventure adventure = new Adventure();
                adventure.setNombre(parseObject.getString("nombre"));
                adventure.setDescripcion(parseObject.getString("descripcion"));
                adventure.setIdNodoInicial(parseObject.getInt("idnodoinicial"));
                adventure.setIdNodoActual(parseObject.getInt("idnodoactual"));
                adventuresList.add(adventure);
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return adventuresList;
    }

    @Override
    public Adventure getAdventureFromName(String adventureName) {

        Adventure adventure = new Adventure();

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Adventures");
        query.whereEqualTo("name", adventureName);
        try {
            for (ParseObject parseObject : query.find()) {
                adventure.setNombre(parseObject.getString("nombre"));
                adventure.setDescripcion(parseObject.getString("descripcion"));
                adventure.setIdNodoInicial(parseObject.getInt("idnodoinicial"));
                adventure.setIdNodoActual(parseObject.getInt("idnodoactual"));
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return adventure;

    }

    @Override
    public List<Adventure> getAdventureListFromPlayer(String idPlayer) {

        List<Adventure> adventuresList = new ArrayList<Adventure>();

        ParseQuery<ParseObject> query = ParseQuery.getQuery("AdventuresAndPlayerList");
        query.whereEqualTo("idplayer", idPlayer);
        try {
            for (ParseObject parseObject : query.find()) {
                Adventure adventure = new Adventure();
                adventure.setNombre(parseObject.getString("nombre"));
                adventure.setDescripcion(parseObject.getString("descripcion"));
                adventure.setIdNodoInicial(parseObject.getInt("idnodoinicial"));
                adventure.setIdNodoActual(parseObject.getInt("idnodoactual"));
                adventuresList.add(adventure);
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return adventuresList;

    }


}

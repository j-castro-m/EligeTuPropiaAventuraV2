package com.joelcastro.eligetupropiaaventura.daos.parse;

import com.joelcastro.eligetupropiaaventura.daos.AdventureNodeDAO;
import com.joelcastro.eligetupropiaaventura.models.Adventure;
import com.joelcastro.eligetupropiaaventura.models.AdventureNode;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import org.androidannotations.annotations.EBean;

/**
 * Created by joel on 14/10/14.
 */
@EBean
public class AdventureNodeParseDAO implements AdventureNodeDAO {
    @Override
    public AdventureNode getNodeFromId(int idNode) {
        AdventureNode adventureNode = new AdventureNode();

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Adventures");
        query.whereEqualTo("id", idNode);
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

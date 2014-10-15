package com.joelcastro.eligetupropiaaventura.daos.SQLite;

import com.joelcastro.eligetupropiaaventura.daos.AdventureDAO;
import com.joelcastro.eligetupropiaaventura.models.Adventure;

import org.androidannotations.annotations.EBean;

import java.util.List;

/**
 * Created by joel on 12/10/14.
 */
@EBean
public class AdventureSQLiteDAO implements AdventureDAO {
    @Override
    public List<Adventure> getAllAdventures() {
        return null;
    }

    @Override
    public List<Adventure> getAdventureListFromPlayer(String idPlayer) {
        return null;
    }

    @Override
    public Adventure getAdventureFromName(String adventureName) {
        return null;
    }


}

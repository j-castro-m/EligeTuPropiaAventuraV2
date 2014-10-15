package com.joelcastro.eligetupropiaaventura.daos;

import com.joelcastro.eligetupropiaaventura.models.Adventure;

import java.util.List;

/**
 * Created by joel on 12/10/14.
 */
public interface AdventureDAO {

    public List<Adventure> getAllAdventures();
    public List<Adventure> getAdventureListFromPlayer(String idPlayer);
    public Adventure getAdventureFromName(String adventureName);
    public void delAdventureFromPlayer(String adventureName);
    //public void editAdventure(Adventure adventure);
}

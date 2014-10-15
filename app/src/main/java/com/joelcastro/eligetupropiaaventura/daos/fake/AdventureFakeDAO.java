package com.joelcastro.eligetupropiaaventura.daos.fake;

import com.joelcastro.eligetupropiaaventura.daos.AdventureDAO;
import com.joelcastro.eligetupropiaaventura.models.Adventure;

import org.androidannotations.annotations.EBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by joel on 12/10/14.
 */
@EBean(scope = EBean.Scope.Singleton)
public class AdventureFakeDAO implements AdventureDAO{

    @Override
    public List<Adventure> getAllAdventures() {

        List<Adventure> adventuresList = new ArrayList<Adventure>();
        adventuresList.add(new Adventure("Aventura Inicial","Aventura de prueba inicial del sistema",1,1));
        adventuresList.add(new Adventure("Aventura Inicial 2","Aventura de prueba inicial del sistema 2",2,2));
        adventuresList.add(new Adventure("Aventura Inicial 3","Aventura de prueba inicial del sistema 3",3,3));
        adventuresList.add(new Adventure("Aventura Inicial 4","Aventura de prueba inicial del sistema 4",4,4));
        adventuresList.add(new Adventure("Aventura Inicial 5","Aventura de prueba inicial del sistema 5",5,5));
        return adventuresList;
    }


    @Override
    public Adventure getAdventureFromName(String adventureName) {
        if(adventureName.equals("Aventura de prueba inicial del sistema")){
            return new Adventure("Aventura Inicial","Aventura de prueba inicial del sistema",1,6);
        }else if(adventureName.equals("Aventura de prueba inicial del sistema 2")){
            return new Adventure("Aventura Inicial 2","Aventura de prueba inicial del sistema 2",2,8);
        }else if(adventureName.equals("Aventura de prueba inicial del sistema 3")){
            return new Adventure("Aventura Inicial 3","Aventura de prueba inicial del sistema 3",3,10);
        }
        return new Adventure("Aventura Inicial 3","Aventura de prueba inicial del sistema 3",3,10);
    }

    @Override
    public List<Adventure> getAdventureListFromPlayer(String idPlayer) {
        List<Adventure> adventuresList = new ArrayList<Adventure>();
        adventuresList.add(new Adventure("Aventura Inicial","Aventura de prueba inicial del sistema",1,6));
        adventuresList.add(new Adventure("Aventura Inicial 2","Aventura de prueba inicial del sistema 2",2,8));
        adventuresList.add(new Adventure("Aventura Inicial 3","Aventura de prueba inicial del sistema 3",3,10));
        return adventuresList;
    }

}

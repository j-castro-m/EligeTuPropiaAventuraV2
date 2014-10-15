package com.joelcastro.eligetupropiaaventura.daos;

import com.joelcastro.eligetupropiaaventura.daos.SQLite.AdventureHistorySQLiteDAO;
import com.joelcastro.eligetupropiaaventura.daos.SQLite.AdventureNodeSQLiteDAO;
import com.joelcastro.eligetupropiaaventura.daos.SQLite.AdventureSQLiteDAO;
import com.joelcastro.eligetupropiaaventura.daos.SQLite.UserSQLiteDAO;
import com.joelcastro.eligetupropiaaventura.daos.fake.AdventureFakeDAO;
import com.joelcastro.eligetupropiaaventura.daos.fake.AdventureHistoryFakeDAO;
import com.joelcastro.eligetupropiaaventura.daos.fake.AdventureNodeFakeDAO;
import com.joelcastro.eligetupropiaaventura.daos.fake.UserFakeDAO;
import com.joelcastro.eligetupropiaaventura.daos.parse.AdventureHistoryParseDAO;
import com.joelcastro.eligetupropiaaventura.daos.parse.AdventureNodeParseDAO;
import com.joelcastro.eligetupropiaaventura.daos.parse.AdventureParseDAO;
import com.joelcastro.eligetupropiaaventura.daos.parse.UserParseDAO;
import com.joelcastro.eligetupropiaaventura.models.AdventureNode;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;


/**
 * Created by joel on 12/10/14.
 */
@EBean(scope = EBean.Scope.Singleton)
public class DAOFactory {


    static int FAKE_DAO = 1;
    static int SQLITEDAO = 2;
    static int PARSEDAO = 3;

    int SELECTED = FAKE_DAO;

    @Bean
    UserFakeDAO userFakeDAO;
    @Bean
    AdventureFakeDAO adventureFakeDAO;
    @Bean
    AdventureNodeFakeDAO adventureNodeFakeDAO;
    @Bean
    AdventureHistoryFakeDAO adventureHistoryFakeDAO;


    @Bean
    UserSQLiteDAO userSQLiteDAO;
    @Bean
    AdventureSQLiteDAO adventureSQLiteDAO;
    @Bean
    AdventureNodeSQLiteDAO adventureNodeSQLiteDAO;
    @Bean
    AdventureHistorySQLiteDAO adventureHistorySQLiteDAO;

    @Bean
    UserParseDAO userParseDAO;
    @Bean
    AdventureParseDAO adventureParseDAO;
    @Bean
    AdventureNodeParseDAO adventureNodeParseDAO;
    @Bean
    AdventureHistoryParseDAO adventureHistoryParseDAO;

    UserDAO userDAOSelected;
    AdventureDAO adventureDAOSelected;
    AdventureNodeDAO adventureNodeDAOSelected;
    AdventureHistoryDAO adventureHistoryDAOSelected;

    @AfterInject
    void initDAOs(){
        if (SELECTED == FAKE_DAO){
            userDAOSelected = userFakeDAO;
            adventureDAOSelected = adventureFakeDAO;
            adventureNodeDAOSelected = adventureNodeFakeDAO;
            adventureHistoryDAOSelected = adventureHistoryFakeDAO;
        } else if(SELECTED == SQLITEDAO){
            userDAOSelected = userSQLiteDAO;
            adventureDAOSelected = adventureSQLiteDAO;
            adventureNodeDAOSelected = adventureNodeSQLiteDAO;
            adventureHistoryDAOSelected = adventureHistorySQLiteDAO;
        }else if(SELECTED == PARSEDAO){
            userDAOSelected = userParseDAO;
            adventureDAOSelected = adventureParseDAO;
            adventureNodeDAOSelected = adventureNodeParseDAO;
            adventureHistoryDAOSelected = adventureHistoryParseDAO;
        }
    }

    public UserDAO getUserDAO(){
        return userDAOSelected;
    }
    public AdventureDAO getAdventureDAO(){
        return adventureDAOSelected;
    }
    public AdventureNodeDAO getAdventureNodeDAO(){
        return adventureNodeDAOSelected;
    }
    public AdventureHistoryDAO getAdventureHistoryDAO(){
        return adventureHistoryDAOSelected;
    }


}

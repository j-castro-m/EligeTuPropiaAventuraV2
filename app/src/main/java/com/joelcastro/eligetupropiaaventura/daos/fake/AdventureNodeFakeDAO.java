package com.joelcastro.eligetupropiaaventura.daos.fake;

import com.joelcastro.eligetupropiaaventura.daos.AdventureNodeDAO;
import com.joelcastro.eligetupropiaaventura.models.AdventureNode;

import org.androidannotations.annotations.EBean;

/**
 * Created by joel on 12/10/14.
 */
@EBean(scope = EBean.Scope.Singleton)
public class AdventureNodeFakeDAO implements AdventureNodeDAO {
    @Override
    public AdventureNode getNodeFromId(int idNode) {
        return new AdventureNode(idNode,"Fake Text String for node "+idNode,"0.6666,0.88888","Node"+idNode+" Title",idNode+1,idNode+2);
    }
}
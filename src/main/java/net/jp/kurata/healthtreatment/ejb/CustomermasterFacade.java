/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.jp.kurata.healthtreatment.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import net.jp.kurata.healthtreatment.entity.Customermaster;

/**
 *
 * @author yosh
 */
@Stateless
public class CustomermasterFacade extends AbstractFacade<Customermaster> {

    @PersistenceContext(unitName = "net.jp.kurata_HealthTreatment_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CustomermasterFacade() {
        super(Customermaster.class);
    }
    
}

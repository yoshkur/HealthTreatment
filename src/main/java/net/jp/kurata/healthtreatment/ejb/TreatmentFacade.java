/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.jp.kurata.healthtreatment.ejb;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import net.jp.kurata.healthtreatment.entity.Customermaster;
import net.jp.kurata.healthtreatment.entity.Treatment;
import net.jp.kurata.healthtreatment.entity.Treatment_;
import net.jp.kurata.healthtreatment.jsf.treatment.TreatmentSearchCondition;

/**
 *
 * @author yosh
 */
@Stateless
public class TreatmentFacade extends AbstractFacade<Treatment> {

    @PersistenceContext(unitName = "net.jp.kurata_HealthTreatment_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TreatmentFacade() {
        super(Treatment.class);
    }

    public List<Customermaster> findRequestAll(TreatmentSearchCondition condition) {
        CriteriaBuilder cb = this.getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Customermaster> cq = cb.createQuery(Customermaster.class);
        Root<Customermaster> root = cq.from(Customermaster.class);
        cq = this.getSearchQuery(condition, cb, cq, root);
        this.setOrderby(condition, cb, cq, root);
        Query q = this.getEntityManager().createQuery(cq);
        return q.getResultList();
    }

    public List<Customermaster> findRequestRange(int[] range, TreatmentSearchCondition condition) {
        CriteriaBuilder cb = this.getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Customermaster> cq = cb.createQuery(Customermaster.class);
        Root<Customermaster> root = cq.from(Customermaster.class);
        cq = this.getSearchQuery(condition, cb, cq, root);
        this.setOrderby(condition, cb, cq, root);
        Query q = this.getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int countRequest(TreatmentSearchCondition condition) {
        CriteriaBuilder cb = this.getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<Customermaster> root = cq.from(Customermaster.class);
        cq = this.getSearchQuery(condition, cb, cq, root);

        cq.select(cb.count(root));
        Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    private CriteriaQuery getSearchQuery(TreatmentSearchCondition condition, CriteriaBuilder cb, CriteriaQuery cq, Root root) {
        Predicate predicate;
        cq.select(root).where(cb.equal(root.get(Treatment_.recordvalid), true));
        predicate = cq.getRestriction();
        if (condition.getChiefcomplaint() != null) {
            cq.select(root).where(predicate, cb.like(root.get(Treatment_.chiefcomplaint).as(String.class), "%" + condition.getChiefcomplaint() + "%"));
            predicate = cq.getRestriction();
        }
        return cq;
    }

    private void setOrderby(TreatmentSearchCondition condition, CriteriaBuilder cb, CriteriaQuery cq, Root root) {
        if (condition.getOrderBy() != null) {
            if (condition.getAsc()) {
                cq.orderBy(cb.asc(root.get(condition.getOrderBy())));
            } else {
                cq.orderBy(cb.desc(root.get(condition.getOrderBy())));
            }
        }
    }
}

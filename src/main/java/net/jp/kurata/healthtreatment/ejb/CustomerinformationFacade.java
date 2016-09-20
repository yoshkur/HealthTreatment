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
import net.jp.kurata.healthtreatment.entity.Customerinformation;
import net.jp.kurata.healthtreatment.entity.Customerinformation_;
import net.jp.kurata.healthtreatment.jsf.customerinformation.CustomerInformationSearchCondition;

/**
 *
 * @author yosh
 */
@Stateless
public class CustomerinformationFacade extends AbstractFacade<Customerinformation> {

    @PersistenceContext(unitName = "net.jp.kurata_HealthTreatment_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CustomerinformationFacade() {
        super(Customerinformation.class);
    }

    public List<Customerinformation> findRequestAll(CustomerInformationSearchCondition condition) {
        CriteriaBuilder cb = this.getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Customerinformation> cq = cb.createQuery(Customerinformation.class);
        Root<Customerinformation> root = cq.from(Customerinformation.class);
        cq = this.getSearchQuery(condition, cb, cq, root);
        this.setOrderby(condition, cb, cq, root);
        Query q = this.getEntityManager().createQuery(cq);
        return q.getResultList();
    }

    public List<Customerinformation> findRequestRange(int[] range, CustomerInformationSearchCondition condition) {
        CriteriaBuilder cb = this.getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Customerinformation> cq = cb.createQuery(Customerinformation.class);
        Root<Customerinformation> root = cq.from(Customerinformation.class);
        cq = this.getSearchQuery(condition, cb, cq, root);
        this.setOrderby(condition, cb, cq, root);
        Query q = this.getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int countRequest(CustomerInformationSearchCondition condition) {
        CriteriaBuilder cb = this.getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<Customerinformation> root = cq.from(Customerinformation.class);
        cq = this.getSearchQuery(condition, cb, cq, root);

        cq.select(cb.count(root));
        Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    private CriteriaQuery getSearchQuery(CustomerInformationSearchCondition condition, CriteriaBuilder cb, CriteriaQuery cq, Root root) {
        Predicate predicate;
        cq.select(root).where(cb.equal(root.get(Customerinformation_.recordvalid), true));
        predicate = cq.getRestriction();
        if (condition.getJob() != null) {
            cq.select(root).where(predicate, cb.like(root.get(Customerinformation_.job).as(String.class), "%" + condition.getJob() + "%"));
            predicate = cq.getRestriction();
        }
        return cq;
    }

    private void setOrderby(CustomerInformationSearchCondition condition, CriteriaBuilder cb, CriteriaQuery cq, Root root) {
        if (condition.getOrderBy() != null) {
            if (condition.getAsc()) {
                cq.orderBy(cb.asc(root.get(condition.getOrderBy())));
            } else {
                cq.orderBy(cb.desc(root.get(condition.getOrderBy())));
            }
        }
    }
}

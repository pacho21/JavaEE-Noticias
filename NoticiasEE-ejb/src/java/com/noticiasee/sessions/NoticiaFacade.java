/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.noticiasee.sessions;

import com.noticiasee.entities.Noticia;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author admin
 */
@Stateless
public class NoticiaFacade extends AbstractFacade<Noticia> {

    @PersistenceContext(unitName = "NoticiasEE-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public NoticiaFacade() {
        super(Noticia.class);
    }

    public Noticia getByDate(Date d) {
        return (Noticia) getEntityManager().createNamedQuery("Noticia.findByPosted").getSingleResult();
    }

    public List<Noticia> getLastById() {
        String sql = "SELECT n FROM Noticia n ORDER BY n.id DESC";
        return getEntityManager().createQuery(sql).setMaxResults(3).getResultList();
    }

    public List<Noticia> getMoreById(int last) {
        String sql = "SELECT n FROM Noticia n ORDER BY n.id DESC";
        return getEntityManager().createQuery(sql).setMaxResults(3).setFirstResult(last).getResultList();
    }

    public Noticia getById(int id) {
        return (Noticia) getEntityManager().createNamedQuery("Noticia.findById").setParameter("id", id).getSingleResult();
    }

}

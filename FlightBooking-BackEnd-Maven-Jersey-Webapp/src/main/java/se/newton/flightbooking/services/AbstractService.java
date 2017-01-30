package se.newton.flightbooking.services;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;

import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import se.newton.flightbooking.HibernateUtil;

import se.newton.flightbooking.services.interfaces.IService;

public class AbstractService<T, ID extends Serializable> implements IService<T, ID> {

    private Class<T> persistentClass;
    private Session session;

    public AbstractService() {
        this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public Class<T> getPersistentClass() {
        return persistentClass;
    }

    protected Session getSession() {
        if (this.session == null) 
            this.session = HibernateUtil.getSession();

        return this.session;
    }

    protected List<T> findByCriteria(Criterion... criterion) {
        Criteria crit = this.getSession().createCriteria(this.getPersistentClass());

        for (Criterion c : criterion) {
            crit.add(c);
        }
        
        return (List<T>) crit.list();
    }

    @Override
    public void setSession(Session session) {
        this.session = session;
    }

    @Override
    public T findById(ID id) {
        //return (T) getSession().load(this.getPersistentClass(), id);
        return (T) getSession().get(this.getPersistentClass(), id);
    }

    @Override
    public List<T> findAll() {
        return this.findByCriteria();
    }

    @Override
    public T save(T entity) {
        this.getSession().saveOrUpdate(entity);
        return entity;
    }

    @Override
    public void delete(T entity) {
        this.getSession().delete(entity);
    }

    @Override
    public void flush() {
        this.getSession().flush();
    }

    @Override
    public void clear() {
        this.getSession().clear();
    }
}

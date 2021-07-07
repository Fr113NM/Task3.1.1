package org.crud.repositories;

import org.crud.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository{
    @PersistenceContext
    EntityManager entityManager;

    private RoleRepository roleRepository;

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
    @Override
    public <S extends User> List<S> findAll(Example<S> example) {
        return entityManager.createQuery("SELECT DISTINCT u FROM User u JOIN FETCH u.roles")
                .getResultList();
    }
    @Override
    public User findByName(String name) {
        final User user = entityManager
                .createQuery ( "SELECT u FROM User u JOIN FETCH u.roles WHERE u.firstName = :name", User.class )
                .setParameter ( "name", name )
                .getSingleResult ();
        return user;
    }
    @Override
    public <S extends User> S saveAndFlush(S s) {
        entityManager.persist(s);
    }
    @Override
    public User getOne(Long integer) {
        return entityManager
                .createQuery("SELECT u FROM User u JOIN FETCH u.roles WHERE u.id = :id", User.class)
                .setParameter("id", integer)
                .getSingleResult();
    }


}

package org.crud.repositories;

import org.crud.entities.Role;
import org.crud.entities.User;
import org.springframework.data.domain.Example;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class RoleRepositoryImpl implements RoleRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public <S extends Role> List<S> findAll(Example<S> example) {
        return (List<S>) entityManager.createQuery ( "FROM Role", Role.class )
                .getResultList ();
    }

    public Role getOne(Long role) {
            return entityManager
                    .createQuery("SELECT u FROM User u JOIN FETCH u.roles WHERE u.id = :id", User.class)
                    .setParameter("id", role)
                    .getSingleResult();
    }
}

package org.crud.repositories;

import org.crud.entities.Role;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {
    @Override
    <S extends Role> List<S> findAll(Example<S> example);

    @Override
    Role getOne(Long role);
}

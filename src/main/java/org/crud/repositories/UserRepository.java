package org.crud.repositories;

import org.crud.entities.User;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByName(String name);

    @Override
    <S extends User> S saveAndFlush(S s);

    @Override
    User getOne(Long integer);

    @Override
    <S extends User> List<S> findAll(Example<S> example);


}

package org.factoriaf5.bftp2project4grupo6.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


    @Repository
    public interface GameRepository extends CrudRepository<Game, Long> { }

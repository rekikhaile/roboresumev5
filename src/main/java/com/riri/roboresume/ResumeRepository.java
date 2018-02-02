package com.riri.roboresume;

import org.springframework.data.repository.CrudRepository;

public interface ResumeRepository extends CrudRepository<Resume, Long>{
    Iterable <Resume> findAllByNameContainingIgnoreCase(String search);
}

package com.riri.roboresume;

import org.springframework.data.repository.CrudRepository;

public interface AchievementRepository extends CrudRepository<Achievement,Long>{
    Iterable <Achievement> findAllByTitleContainingIgnoreCase(String search);
}

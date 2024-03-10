package com.hoarse.auction.web.repository.hoarse;

import com.hoarse.auction.web.entity.horse.Horse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HoarseRepository extends JpaRepository<Horse, Long> {
   Horse findByName(String name);

   Horse findByuniqueNum(String uniqueNum);

   Optional<Horse> findById(Long hoarseId);
}

package com.hoarse.auction.web.repository.hoarse;

import com.hoarse.auction.web.entity.hoarse.Hoarse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HoarseRepository extends JpaRepository<Hoarse, Long> {
   Hoarse findByName(String name);

   Hoarse findByuniqueNum(String uniqueNum);

   Optional<Hoarse> findById(Long hoarseId);
}

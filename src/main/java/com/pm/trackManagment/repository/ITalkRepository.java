package com.pm.trackManagment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pm.trackManagment.repository.domain.TalkDomain;

@Repository
public interface ITalkRepository extends JpaRepository<TalkDomain, Long> {

	TalkDomain findByName(String name);

}

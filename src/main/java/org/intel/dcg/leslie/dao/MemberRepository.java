package org.intel.dcg.leslie.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import org.intel.dcg.leslie.domain.Member;

public interface MemberRepository extends CrudRepository<Member, Integer> {

    List<Member> findByLastName(String lastName);

}

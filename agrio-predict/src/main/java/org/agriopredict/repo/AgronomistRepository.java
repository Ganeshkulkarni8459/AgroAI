package org.agriopredict.repo;

import java.util.List;
import java.util.Optional;

import org.agriopredict.entity.Agronomist;
import org.agriopredict.entity.RegisterUser;
import org.agriopredict.entity.UserNote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface AgronomistRepository extends JpaRepository<Agronomist,Long>{
	
	Optional<Agronomist> findByMobileNo(String mobileNo);

	List<Agronomist> findByAddress(String address);

}

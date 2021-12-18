package innova.pacs.api.model.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import innova.pacs.api.model.DoctorProfile;

public interface IDoctorProfileRepository extends PagingAndSortingRepository<DoctorProfile, Long> {
	DoctorProfile findByUserId(@Param("userId") Long userId);	
}

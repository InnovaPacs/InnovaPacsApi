package innova.pacs.api.model.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import innova.pacs.api.model.DiagnosticPdfConfigiration;

public interface IDiagnosticPdfConfigirationRepository extends PagingAndSortingRepository<DiagnosticPdfConfigiration, Long> {
	List<DiagnosticPdfConfigiration> findByUserId(@Param("userId") Long userId);
}

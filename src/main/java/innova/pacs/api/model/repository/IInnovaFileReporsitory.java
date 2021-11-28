package innova.pacs.api.model.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import innova.pacs.api.model.InnovaFile;

public interface IInnovaFileReporsitory extends PagingAndSortingRepository<InnovaFile, Long> {

}

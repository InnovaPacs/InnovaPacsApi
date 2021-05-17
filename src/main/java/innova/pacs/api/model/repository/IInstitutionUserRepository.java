package innova.pacs.api.model.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import innova.pacs.api.model.InstitutionUser;

public interface IInstitutionUserRepository  extends PagingAndSortingRepository<InstitutionUser, Integer>{

}

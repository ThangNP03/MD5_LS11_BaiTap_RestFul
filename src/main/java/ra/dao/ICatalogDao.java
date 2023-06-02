package ra.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import ra.model.Catalog;

public interface ICatalogDao extends PagingAndSortingRepository<Catalog, Long> {
}

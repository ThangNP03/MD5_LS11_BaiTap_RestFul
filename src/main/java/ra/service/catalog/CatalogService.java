package ra.service.catalog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.dao.ICatalogDao;
import ra.model.Catalog;

import java.util.Optional;
@Service
public class CatalogService implements ICatalogService{
    @Autowired
    private ICatalogDao catalogDao;
    @Override
    public Iterable<Catalog> findAll() {
        return catalogDao.findAll();
    }

    @Override
    public Optional<Catalog> findById(Long id) {
        return catalogDao.findById(id);
    }

    @Override
    public Catalog save(Catalog catalog) {
        return catalogDao.save(catalog);
    }

    @Override
    public void delete(Long id) {
        catalogDao.existsById(id);
    }
}

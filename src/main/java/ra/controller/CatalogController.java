package ra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.model.Catalog;
import ra.service.catalog.ICatalogService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/catalog")
@CrossOrigin(origins = "*")
public class CatalogController {
    @Autowired
    private ICatalogService catalogService;


    @GetMapping("")
    public ResponseEntity<Iterable<Catalog>> findAllCatalog() {
        List<Catalog> catalogs = (List<Catalog>) catalogService.findAll();
        if (catalogs.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(catalogs, HttpStatus.OK);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<Catalog> findCatalogById(@PathVariable Long id){
        Optional<Catalog> catalogOptional= catalogService.findById(id);
        if (!catalogOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(catalogOptional.get(),HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Catalog> findByBlogId(@PathVariable Long id) {
        Optional<Catalog> catalogOptional = catalogService.findById(id);
        return catalogOptional.map(
                        catalog -> new ResponseEntity<>(catalog, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/create")
    public ResponseEntity<Catalog> saveBlog(@RequestBody Catalog catalogOptional) {
        return new ResponseEntity<>(catalogService.save(catalogOptional), HttpStatus.CREATED);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<Catalog> updateCustomer(@PathVariable Long id, @RequestBody Catalog catalog) {
        Optional<Catalog> customerOptional = catalogService.findById(id);
        if (!customerOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catalog.setCatalogId(customerOptional.get().getCatalogId());
        return new ResponseEntity<>(catalogService.save(catalog), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Catalog> deleteCustomer(@PathVariable Long id) {
        Optional<Catalog> customerOptional = catalogService.findById(id);
        if (!customerOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catalogService.delete(id);
        return new ResponseEntity<>(customerOptional.get(), HttpStatus.NO_CONTENT);
    }

}

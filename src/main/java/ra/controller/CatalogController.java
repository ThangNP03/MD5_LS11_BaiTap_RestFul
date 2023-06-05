package ra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.model.Catalog;
import ra.model.ResponOject;
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
    public ResponseEntity<ResponOject> getAllCatalog() {
        List<Catalog> catalogs = (List<Catalog>) catalogService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(new ResponOject("ok", "Query Successfully!", catalogs));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponOject> getCatalogById(@PathVariable Long id) {
        Optional<Catalog> catalogOptional = catalogService.findById(id);
        return catalogOptional.isPresent()
                ? ResponseEntity.status(HttpStatus.OK).body(
                new ResponOject("ok", "Query Successfully!", catalogOptional))
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponOject("fail", "Cannot find catalog with id = " + id, ""));
    }

    @PostMapping("")
    public ResponseEntity<ResponOject> postForCatalog(@RequestBody Catalog catalog) {
        Catalog newCatalog = catalogService.save(catalog);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponOject("ok", "Post catalog successfully!", newCatalog));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponOject> putForCatalog(@RequestBody Catalog catalog, @PathVariable Long id) {
        Optional<Catalog> catalogOptional = catalogService.findById(id);
        return catalogOptional.isPresent()
                ? ResponseEntity.status(HttpStatus.OK).body(
                new ResponOject("ok", "Update catalog successfully!", catalogService.save(catalog)))
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponOject("fail", "Cannot find catalog with id = " + id, ""));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponOject> deleteForCatalog(@PathVariable Long id) {
        Optional<Catalog> catalogOptional = catalogService.findById(id);
        return catalogOptional.isPresent()
                ? ResponseEntity.status(HttpStatus.OK).body(
                new ResponOject("ok", "Delete catalog successfully!", ""))
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponOject("fail", "Cannot find catalog with id = " + id, ""));
    }

}

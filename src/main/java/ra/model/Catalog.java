package ra.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Catalog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long catalogId;
    private String catalogName;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "catalog", fetch = FetchType.EAGER)
    private List<Blog> blogs;

    public Catalog(Long catalogId, String catalogName, List<Blog> blogs) {
        this.catalogId = catalogId;
        this.catalogName = catalogName;
        this.blogs = blogs;
    }

    public Catalog() {

    }

    public Long getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(Long catalogId) {
        this.catalogId = catalogId;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public List<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<Blog> blogs) {
        this.blogs = blogs;
    }
}

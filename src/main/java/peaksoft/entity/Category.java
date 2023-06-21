package peaksoft.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static jakarta.persistence.CascadeType.*;

@Getter
@Setter
@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_gen")
    @SequenceGenerator(name = "category_gen",sequenceName = "category_seq",allocationSize = 1)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "category", cascade = {REFRESH,REFRESH,DETACH,MERGE})
    private List<Subcategory> subcategories;

}
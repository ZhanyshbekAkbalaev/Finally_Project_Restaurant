package peaksoft.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

import static jakarta.persistence.CascadeType.*;

@Getter
@Setter
@Entity
@Table(name = "subcategories")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Subcategory {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "subcategory_gen")
    @SequenceGenerator(name = "subcategory_gen",sequenceName = "subcategory_seq",allocationSize = 1)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "subcategory", cascade = {MERGE, REFRESH, DETACH})
    private List<MenuItem> menuItems;

    @ManyToOne(cascade = {MERGE, REFRESH, DETACH})
    private Category category;

}
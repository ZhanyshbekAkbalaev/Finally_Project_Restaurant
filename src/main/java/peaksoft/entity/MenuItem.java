package peaksoft.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static jakarta.persistence.CascadeType.*;

@Getter
@Setter
@Entity
@Table(name = "menuItems")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "menuItem_gen")
    @SequenceGenerator(name = "menuItem_gen",sequenceName = "menuItem_seq",allocationSize = 1)
    private Long id;
    private String name;
    private String image;
    private int price;
    private String description;
    private Boolean isVegetarian;

    @ManyToOne(cascade = {REFRESH,DETACH, MERGE})
    private Restaurant restaurant;

    @ManyToMany(cascade = {PERSIST, MERGE, REFRESH, DETACH})
    private List<Cheque> cheques;

    @OneToOne(mappedBy = "menuItem", cascade = {PERSIST, MERGE, REFRESH, DETACH})
    private StopList stopList;

    @ManyToOne(cascade = {MERGE, REFRESH, DETACH})
    private Subcategory subcategory;

}
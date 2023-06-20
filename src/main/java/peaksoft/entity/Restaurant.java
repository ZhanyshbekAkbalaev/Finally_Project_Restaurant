package peaksoft.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "restaurants")
@Getter
@Setter
@NoArgsConstructor
public class Restaurant {
    @Id
    @SequenceGenerator(name = "restaurant_id_gen",sequenceName = "restaurant_id_seq",allocationSize = 1)
    @GeneratedValue(generator = "restaurant_id_gen",strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private String location;
    private String restType;
    private int numberOfEmployees;
    private int service;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private List<User> users;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private List<MenuItem> menuItems;
}

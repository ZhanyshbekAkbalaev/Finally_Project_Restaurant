package peaksoft.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static jakarta.persistence.CascadeType.*;

@Getter
@Setter
@Entity
@Table(name = "cheque")
public class Cheque {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cheque_seq")
    @SequenceGenerator(name = "cheque_seq")
    private Long id;
    private BigDecimal priceAverage;

    @ManyToOne(cascade = {PERSIST,MERGE,REFRESH,DETACH})
    private User user;

    @ManyToMany(mappedBy = "cheques", cascade = {PERSIST, MERGE, REFRESH, DETACH})
    private List<MenuItem> menuItems;

}
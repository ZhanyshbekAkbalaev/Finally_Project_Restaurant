package peaksoft.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "stop_list")
public class StopList {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stop_list_gen")
    @SequenceGenerator(name = "stop_list_gen",sequenceName = "stop_list_seq",allocationSize = 1)
    private Long id;
    private String reason;
    private LocalDate date;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    private MenuItem menuItem;

}
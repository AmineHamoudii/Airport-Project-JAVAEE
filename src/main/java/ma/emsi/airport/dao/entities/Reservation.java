package ma.emsi.airport.dao.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Data
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Date date;
    private Double prix;
    @ManyToOne
    private Reservation reservation;
    @ManyToOne
    private Passager passager;
    @ManyToOne
    private Vol vol;
    @ManyToOne
    private Client client;
}

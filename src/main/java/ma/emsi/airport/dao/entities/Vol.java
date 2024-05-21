package ma.emsi.airport.dao.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Entity
@Data
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Vol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Date jourdepart;
    private  Date heuredepart;
    private Date jourarrive;
    private  Date heurearrive;
    @ManyToOne
    private Compagnie compagnie;
    @ManyToOne
    private Aeroport aeroportDeparts;
    @ManyToOne
    private Aeroport aeroportArrives;
    @OneToMany(mappedBy ="vol", fetch = FetchType.LAZY)
    private Collection<Reservation> reservations = new ArrayList<>();
    @ManyToOne
    private Escale escale;
}

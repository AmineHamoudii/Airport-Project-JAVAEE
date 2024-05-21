package ma.emsi.airport.dao.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Aeroport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nom;
    private String pays;
    private String ville ;
    @OneToMany(mappedBy ="aeroportArrives", fetch = FetchType.LAZY)
    private Collection<Vol> volsArrives;
    @OneToMany(mappedBy ="aeroportDeparts", fetch = FetchType.LAZY)
    private Collection<Vol> volsDepart;
    @ManyToOne
    private Escale escale;
}

package ma.emsi.airport.dao.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Collection;

@Entity
@Data
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Escale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer nombre;
    @OneToMany(mappedBy ="escale", fetch = FetchType.LAZY)
    private Collection<Vol> vols;
    @OneToMany(mappedBy ="escale", fetch = FetchType.LAZY)
    private Collection<Aeroport> aeroports= new ArrayList<>();
}

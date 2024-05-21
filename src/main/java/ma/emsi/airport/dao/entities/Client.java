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
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nom;
    private  String prenom;
    private int age;
    @OneToMany(mappedBy ="client", fetch = FetchType.LAZY)
    private Collection<Reservation> reservations= new ArrayList<>();
}

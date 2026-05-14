package ee.mihkel.veebipood.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date created;
    private double total;

    // kui kustutame tellimuse, siis kõik temaga seonduvad tellimuse read kustuvad

    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderRow> orderRows;

    @ManyToOne
    private Person person;
}

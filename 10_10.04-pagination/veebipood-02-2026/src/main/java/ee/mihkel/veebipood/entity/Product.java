package ee.mihkel.veebipood.entity;

// import javax.persistence.Entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
//@Table(name = "toode")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

//    @Column(name = "desc")
    private String description;
    private double price;
    private int stock;
    private boolean active; // isActive panna ei saa kui kasutan Lomboki Gettereid, Settereid

    // parempoolne tähistab kas ma panen selle annotatsiooni singularile või listile
    // @OneToOne
    // @ManyToOne
    // @OneToMany
    // @ManyToMany

    @ManyToOne
    private Category category;
}

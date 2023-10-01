package hellojpa;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
<<<<<<< HEAD
@Setter
=======
>>>>>>> origin/main
@Entity
@Table(name = "ORDERS")
public class Order {

<<<<<<< HEAD
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int orderAmount;
=======
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // db 종류에 따라 autoincrement를 위임
    @Column(name = "ORDER_ID")
    private Long id;

    private int orderAmount;

>>>>>>> origin/main
    @Embedded
    private Address address;

    @ManyToOne
<<<<<<< HEAD
    @JoinColumn(name = "PRODUCT_ID")
=======
    @JoinColumn(name = "product")
>>>>>>> origin/main
    private Product product;

}

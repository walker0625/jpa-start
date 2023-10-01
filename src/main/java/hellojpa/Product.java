package hellojpa;

import lombok.Getter;
<<<<<<< HEAD
import lombok.Setter;
=======
>>>>>>> origin/main

import javax.persistence.*;

@Getter
<<<<<<< HEAD
@Setter
@Entity
public class Product {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
=======
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // db 종류에 따라 autoincrement를 위임
    @Column(name = "PRODUCT_ID")
    private Long id;

>>>>>>> origin/main
    private String name;
    private int price;
    private int stockAmount;

}

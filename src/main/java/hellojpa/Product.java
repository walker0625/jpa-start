package hellojpa;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // db 종류에 따라 autoincrement를 위임
    @Column(name = "PRODUCT_ID")
    private Long id;

    private String name;
    private int price;
    private int stockAmount;

}

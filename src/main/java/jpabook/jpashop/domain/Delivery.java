package jpabook.jpashop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Delivery {

    @Id @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery")
    private Order order;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING) // Default => ORDINAL (컬럼이 1,2,3,4.. 숫자로 들어감) => 중간에 다른 형태가 생기면 망함 => 따라서 String으로
    private DeliveryStatus status; // READY, COMP
}

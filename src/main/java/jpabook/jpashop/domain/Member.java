package jpabook.jpashop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id") // 컬럼 어노테이션을 주지 않으면 id 그대로 사용됨
    private Long id;

    private String name;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member")  // member가 여러개의 주문을 할 수 있기 때문에 일대다 관계 // mappedBy => 연관관계의 주인 X
    // 여기에 값을 변경해도 FK값이 변하지 않는다
    private List<Order> orders = new ArrayList<>();

}

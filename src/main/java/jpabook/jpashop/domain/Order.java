package jpabook.jpashop.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders") // 관례로 order가 되어버릴 수 있기 때문에 order로 지정
public class Order {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne // Order와 Member => 다대일 관계
    @JoinColumn(name = "member_id") // Mapping을 무엇으로 할 것인지 => foreign key id가 memeer_id가 됨
    // 이 값을 변경하면 member class의 member값이 변경됨
    private Member member;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne   // 주로 Order를 먼저 보고 Delivery를 보기 때문에 Orders에 FK 넣는 것을 선호 => 연관관계의 주인 Orders (Delivery에 넣어도 됨)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status; // 주문 상태 [ORDER, CANCEL]
}

// order에 입장에서 member를 변경할 수 있고, 반대로 member의 입장에서 order를 바꿀 수 있는데(양방향 참조)
// 그렇게 되면 JPA 입장에서 무엇을 보고 확인을 해야하는지 혼동 => 그래서 둘 중의 하나를 변경하기 위한 규칙을 정함 => 연관관계
// 연관관계의 주인은 FK가 있는 곳으로 설정
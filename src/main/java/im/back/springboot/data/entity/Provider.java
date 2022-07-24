package im.back.springboot.data.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "provider")
public class Provider extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    /*
    cascade 는 외래키로 참조하는 Entity 의 영속 상태가 변경되었을 시,
    매핑된 Entity 에 대해 동일한 영속 상태로 변경되도록 함

    여기서는 Provider Entity 의 영속 상태가 PERSIST 되었을 시 Product 또한 PERSIST 함

    orphanRemoval 은 고아 객체를 자동으로 제거하는 기능
    고아 객체란 부모 엔티티와 연관관계가 끊어진 객체를 의미
    */
    @OneToMany(mappedBy = "provider", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST, orphanRemoval = true)
    @ToString.Exclude
    private List<Product> productList = new ArrayList<>();



}

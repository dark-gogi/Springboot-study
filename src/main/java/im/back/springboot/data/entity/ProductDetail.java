package im.back.springboot.data.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@Table(name = "product_detail")
public class ProductDetail extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    /*
    참조할 객체를 외래키로 지정할 column 명을 name 으로 설정
     */
    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

}

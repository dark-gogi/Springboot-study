package im.back.springboot.data.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product")
public class Product {

    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_name", nullable = false)
    private String name;

    @Column(name = "product_price", nullable = false)
    private Integer price;

    @Column(name = "product_stock", nullable = false)
    private Integer stock;

    @Column(name = "product_create_at")
    private LocalDateTime createAt;

    @Column(name = "product_update_at")
    private LocalDateTime updateAt;

}

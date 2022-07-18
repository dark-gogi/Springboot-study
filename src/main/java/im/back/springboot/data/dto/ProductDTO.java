package im.back.springboot.data.dto;

import lombok.*;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    private String name;
    private int price;
    private int stock;



}

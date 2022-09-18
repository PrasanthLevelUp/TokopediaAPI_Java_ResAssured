package pojo;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Order {

    private String order_id;
    private String order_description;
    private String order_status;
    private String last_updated_timestamp;
    private boolean special_order;

}

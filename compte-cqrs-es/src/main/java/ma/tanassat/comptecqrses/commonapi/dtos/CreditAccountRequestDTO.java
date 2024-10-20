package ma.tanassat.comptecqrses.commonapi.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class CreditAccountRequestDTO {
    private String id;
    private double amount;
    private String currency;
}

package clean.it.backend.orders;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateOrderDto {
    @Size(max = 64)
    private String notes;

    @NotNull
    @Min(1)
    private Long customerId;

    @NotNull
    @Min(1)
    private Long textileTypeId;

    @NotNull
    @Min(1)
    private Long cleaningTypeId;
}

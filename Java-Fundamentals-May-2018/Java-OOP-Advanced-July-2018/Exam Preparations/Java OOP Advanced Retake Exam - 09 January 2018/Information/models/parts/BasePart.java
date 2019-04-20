package panzer.models.parts;

import panzer.contracts.Part;
import panzer.models.BaseEntity;

import java.math.BigDecimal;

public abstract class BasePart extends BaseEntity {

    //Model – a string.
    //Weight – a floating-point number.
    //Price – a decimal number.

    protected BasePart(String model, Double weight, BigDecimal price) {
        super(model, weight, price);
    }
}

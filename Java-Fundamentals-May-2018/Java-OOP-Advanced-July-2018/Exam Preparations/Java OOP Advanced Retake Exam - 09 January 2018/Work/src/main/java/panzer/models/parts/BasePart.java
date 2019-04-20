package panzer.models.parts;

import panzer.contracts.Part;

import java.math.BigDecimal;

public abstract class BasePart implements Part {

    //Model – a string.
    //Weight – a floating-point number.
    //Price – a decimal number.
    private String model;
    private Double weight;
    private BigDecimal price;

    protected BasePart(String model, Double weight, BigDecimal price) {
        this.model = model;
        this.weight = weight;
        this.price = price;
    }

    @Override
    public double getWeight() {
        return 0;
    }

    @Override
    public BigDecimal getPrice() {
        return null;
    }

    @Override
    public String getModel() {
        return null;
    }
}

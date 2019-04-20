package panzer.models;

import panzer.contracts.Part;

import java.math.BigDecimal;

public  class BaseEntity implements Part {

    //Model – a string.
    //Weight – a floating-point number.
    //Price – a decimal number.
    private String model;
    private Double weight;
    private BigDecimal price;

    protected BaseEntity(String model, Double weight, BigDecimal price) {
        this.model = model;
        this.weight = weight;
        this.price = price;
    }

    @Override
    public double getWeight() {
        return this.weight;
    }

    @Override
    public BigDecimal getPrice() {
        return this.price;
    }

    @Override
    public String getModel() {
        return this.model;
    }
}

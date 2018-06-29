package p03_CarShopExtended;

public interface Rentable extends Car {
    Integer minRentDays = null;
    Double pricePerDay = null;

    Integer getMinRentDays();

    Double getPricePerDay();
}

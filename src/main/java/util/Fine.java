package util;

public enum Fine {
    AMOUNT(400.0);

    private final Double fee;

    Fine(Double fee) {
        this.fee = fee;
    }

    public Double getFee() {
        return fee;
    }
}

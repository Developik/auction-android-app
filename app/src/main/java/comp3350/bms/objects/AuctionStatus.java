package comp3350.bms.objects;

public enum AuctionStatus {
    NOT_STARTED("NOT STARTED", 0),
    IN_PROGRESS("IN PROGRESS", 1),
    COMPLETED("COMPLETED", 2);

    private String status;
    private int value;

    AuctionStatus(final String status, final int value) {
        this.status = status;
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public String getStatus() {
        return this.status;
    }

    @Override
    public String toString() {
        return this.getStatus();
    }
}
package gorest;

public enum Status {

    ACTIVE("Active"),
    INACTIVE("Inactive");

    private final String status;

    Status(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}

package com.example.csym025;

public class rentDataClass {
    private String customerId;
    private String customerName;
    private String customerPhone;
    private String customerEmail;
    private String propertyId;
    private String propertyType;
    private String propertyPostCode;
    private String rentedDate;
    private String endOfTenancy;

    public rentDataClass(String customerId, String customerName, String customerPhone, String customerEmail, String propertyId, String propertyType, String propertyPostCode, String rentedDate, String endOfTenancy) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerPhone = customerPhone;
        this.customerEmail = customerEmail;
        this.propertyId = propertyId;
        this.propertyType = propertyType;
        this.propertyPostCode = propertyPostCode;
        this.rentedDate = rentedDate;
        this.endOfTenancy = endOfTenancy;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public String getPropertyId() {
        return propertyId;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public String getPropertyPostCode() {
        return propertyPostCode;
    }

    public String getRentedDate() {
        return rentedDate;
    }

    public String getEndOfTenancy() {
        return endOfTenancy;
    }
}

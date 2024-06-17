package com.group4.petcenter.Controllers.Saler;

import java.math.BigDecimal;
import java.util.List;

public class Service {
    private String serviceId;
    private String serviceName;
    private BigDecimal price; // Thay đổi từ BigDecimal thành int
    private List<Billdetail> billdetailist;
    
    public Service(String serviceId, String serviceName, BigDecimal price) {
        this.serviceId = serviceId;
        this.serviceName = serviceName;
        this.price = price;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<Billdetail> getBilldetailist() {
        return billdetailist;
    }

    public void setBilldetailist(List<Billdetail> billdetailist) {
        this.billdetailist = billdetailist;
    }
}

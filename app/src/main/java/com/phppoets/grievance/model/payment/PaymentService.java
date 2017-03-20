package com.phppoets.grievance.model.payment;

/**
 * Created by user on 3/20/2017.
 */
public class PaymentService {
    String serviceName, serviceId, sampleDataDec;

    public PaymentService(String serviceName, String serviceId, String sampleDataDec) {
        this.serviceName = serviceName;
        this.serviceId = serviceId;
        this.sampleDataDec = sampleDataDec;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getSampleDataDec() {
        return sampleDataDec;
    }

    public void setSampleDataDec(String sampleDataDec) {
        this.sampleDataDec = sampleDataDec;
    }
}

package com.hdfcbank.offerengine.domain.port.out;

public interface PayloadLogPort {

    void save(String apiName, String httpMethod, String requestPath, String requestPayload, String responsePayload);
}

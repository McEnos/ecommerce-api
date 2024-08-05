package com.example.orderservice.stubs;

import lombok.experimental.UtilityClass;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

@UtilityClass
public class InventoryStubs {
    public static void stubInventoryCall(String skuCode, Integer quantity) {
        stubFor(get(urlEqualTo("inventory-service/api/inventory?skuCode=" + skuCode + "&quantity=" + quantity))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("true")));
    }
}

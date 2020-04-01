//package com.chrisgya.beerservice.service.inventory;
//
//import com.chrisgya.beerservice.model.dto.BeerInventoryDto;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//import java.util.UUID;
//
//@RequiredArgsConstructor
//@Component
//public class InventoryServiceFeignClientFailOver implements InventoryServiceFeignClient {
//
//    private final InventoryFailoverFeignClient failOverFeignClient;
//
//    @Override
//    public ResponseEntity<List<BeerInventoryDto>> getOnHandInventory(UUID beerId) {
//        return failOverFeignClient.getOnHandInventory();
//    }
//}
//

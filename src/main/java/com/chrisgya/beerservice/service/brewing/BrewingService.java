package com.chrisgya.beerservice.service.brewing;

import com.chrisgya.beerservice.config.JmsConfig;
import com.chrisgya.beerservice.entity.Beer;
import com.chrisgya.beerservice.model.customMapper.BeerMapper;
import com.chrisgya.beerservice.model.event.BrewBeerEvent;
import com.chrisgya.beerservice.repository.BeerRepository;
import com.chrisgya.beerservice.service.inventory.BeerInventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BrewingService {
    private final BeerRepository beerRepository;
    private final BeerInventoryService beerInventoryService;
    private final JmsTemplate jmsTemplate;
    private final BeerMapper beerMapper;

    //  @Scheduled(fixedRate = 5000) //every 5 seconds
    @Scheduled(fixedRateString = "${fixedRate.low_inventory}")
    public void checkForLowInventory() {
        List<Beer> beers = beerRepository.findAll();

        beers.forEach(beer -> {
            Integer invQOH = beerInventoryService.getOnHandInventory(beer.getId());
            log.debug("Checking Inventory for: " + beer.getBeerName() + " / " + beer.getId());
            log.debug("Min Onhand is: " + beer.getMinOnHand());
            log.debug("Inventory is: " + invQOH);

            if (beer.getMinOnHand() >= invQOH) {
                jmsTemplate.convertAndSend(JmsConfig.BREWING_REQUEST_QUEUE, new BrewBeerEvent(beerMapper.beerToBeerDto(beer)));
            }
        });

    }
}


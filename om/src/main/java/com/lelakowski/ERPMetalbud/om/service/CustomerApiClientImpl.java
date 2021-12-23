package com.lelakowski.ERPMetalbud.om.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerApiClientImpl implements CustomerApiClient{

     private final RestTemplate rt;

     public Long getCustomerIdByCaption(String caption){
          return rt.getForObject("http://localhost:8085/customers/caption/" + caption, Long.class);
     }
}

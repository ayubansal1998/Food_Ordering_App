
/*
 *
 *  * Copyright (c) Crio.Do 2019. All rights reserved
 *
 */

package com.crio.qeats.services;

import com.crio.qeats.dto.Restaurant;
import com.crio.qeats.exchanges.GetRestaurantsRequest;
import com.crio.qeats.exchanges.GetRestaurantsResponse;
import com.crio.qeats.repositoryservices.RestaurantRepositoryService;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class RestaurantServiceImpl implements RestaurantService {

  private final Double peakHoursServingRadiusInKms = 3.0;
  private final Double normalHoursServingRadiusInKms = 5.0;
  @Autowired
  private RestaurantRepositoryService restaurantRepositoryService;


  // TODO: CRIO_TASK_MODULE_RESTAURANTSAPI - Implement findAllRestaurantsCloseby.
  // Check RestaurantService.java file for the interface contract.
  @Override
  public GetRestaurantsResponse findAllRestaurantsCloseBy(
      GetRestaurantsRequest getRestaurantsRequest, LocalTime currentTime) {
    LocalTime time1 = LocalTime.parse("08:00:00");
    LocalTime time2 = LocalTime.parse("10:00:00");
    LocalTime time3 = LocalTime.parse("13:00:00");
    LocalTime time4 = LocalTime.parse("14:00:00");
    LocalTime time5 = LocalTime.parse("19:00:00");
    LocalTime time6 = LocalTime.parse("21:00:00");
    if ((currentTime.compareTo(time1) >= 0 && currentTime.compareTo(time2) <= 0)
        || (currentTime.compareTo(time3) >= 0 && currentTime.compareTo(time4) <= 0)
        || (currentTime.compareTo(time5) >= 0 && currentTime.compareTo(time6) <= 0)) {
      GetRestaurantsResponse getRestaurantsResponse = new GetRestaurantsResponse(
          restaurantRepositoryService.findAllRestaurantsCloseBy(
              getRestaurantsRequest.getLatitude(),
              getRestaurantsRequest.getLongitude(), currentTime, peakHoursServingRadiusInKms));
      return getRestaurantsResponse;
    } else {
      GetRestaurantsResponse getRestaurantsResponse = new GetRestaurantsResponse(
          restaurantRepositoryService.findAllRestaurantsCloseBy(
              getRestaurantsRequest.getLatitude(),
              getRestaurantsRequest.getLongitude(), currentTime, normalHoursServingRadiusInKms));
      return getRestaurantsResponse;
    }
  }


}


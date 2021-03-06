package com.mapper;

import com.entities.LocationEntity;
import com.input.LocationInput;
import com.model.LocationJson;
import com.service.LocationService;

public class LocationMapper {

    public static LocationEntity locationToEntity(LocationInput location) {
        return LocationEntity.builder()
                .country(location.getCountry())
                .city(location.getCity())
                .build();
    }

    public static LocationJson entityToLocation(LocationEntity entity) {
        return LocationJson.builder()
                .id(entity.getId())
                .country(entity.getCountry())
                .city(entity.getCity())
                .weather(LocationService.weather(entity))
                .build();
    }

    public static LocationEntity updateLocation(LocationEntity entity, LocationInput location){
        entity.setCountry(location.getCountry());
        entity.setCity(location.getCity());
        return entity;
    }
}

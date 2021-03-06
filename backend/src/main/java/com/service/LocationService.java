package com.service;

import com.entities.EventEntity;
import com.entities.LocationEntity;
import com.input.LocationInput;
import com.mapper.LocationMapper;
import com.model.LocationJson;
import com.repository.ConferenceRepository;
import com.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import java.util.Map;

@Service
public class LocationService {

    private LocationRepository locationRepository;
    private ConferenceRepository eventRepository;

    @Autowired
    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public static String weather(LocationEntity location) {
        String REST_URI = "http://api.weatherstack.com/current?access_key=7d6080ab5727f0fe0479c1a898b780ec";

        Client client = ClientBuilder.newClient();
        Map result = client
                .target(REST_URI)
                .queryParam("query", location.getCity())
                .request(MediaType.APPLICATION_JSON)
                .get(Map.class);

        Map<String, Object> currentValues = (Map<String, Object>) result.get("current");

        String weather = "Temperature: " + currentValues.get("temperature") + " celsius degrees " +
                ", Description: " + currentValues.get("weather_descriptions") +
                ", feels like: " + currentValues.get("feelslike");


        return weather;
    }

    @Transactional
    public LocationJson addLocation(int eventId, LocationInput location) {
        EventEntity event = eventRepository.findById(eventId).orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "Event not found!"));
        LocationEntity locationEntity = LocationMapper.locationToEntity(location);

        locationRepository.save(locationEntity);
        event.setLocation(locationEntity);
        return LocationMapper.entityToLocation(locationEntity);
    }



}

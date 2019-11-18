package com.graphql.spqr;

import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import org.springframework.stereotype.Service;

import java.util.List;

@GraphQLApi
@Service
public class LocationService {
	private final LocationRepository locationRepository;
	LocationService(LocationRepository locationRepository) {
		this.locationRepository = locationRepository;
	}

	@GraphQLQuery
	public List<Location> getLocations(){
		return (List<Location>) this.locationRepository.findAll();
	}
}
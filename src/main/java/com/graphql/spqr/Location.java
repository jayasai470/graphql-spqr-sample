package com.graphql.spqr;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

import java.util.Date;

@Data
public class Location {
	@Id
	private String id;
	private Date measuredAt;
	private GeoJsonPoint loc;

}
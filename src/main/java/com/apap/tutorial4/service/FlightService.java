package com.apap.tutorial4.service;

import com.apap.tutorial4.model.FlightModel;

public interface FlightService {
	void addFlight(FlightModel flight);
	public FlightModel getFlightById(long id);
	public void deleteFlight(FlightModel flight);
}

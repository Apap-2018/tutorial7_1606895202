package com.apap.tutorial6.controller;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.apap.tutorial6.model.FlightModel;
import com.apap.tutorial6.model.PilotModel;
import com.apap.tutorial6.service.FlightService;
import com.apap.tutorial6.service.PilotService;
import com.apap.tutorial7.rest.Setting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * FlightController
 */
@RestController
@RequestMapping("flight")
public class FlightController {
	
		@Autowired
		private FlightService flightService;

		@Bean
		public RestTemplate restFlight() {
			return new RestTemplate();
		}
		
		@Autowired 
		RestTemplate restTemplate;	

		@PostMapping(value = "/add")
		private FlightModel addFlightSubmit(@RequestBody FlightModel flight) {
			return flightService.addFlight(flight);
		}

		@GetMapping(value = "/view/{flightId}")
		private FlightModel viewFlight(@PathVariable ("flightId") long flightId, Model model) {
			return flightService.findById(flightId);
		}

		@DeleteMapping(value = "/{flightId}")
		private String deleteFlight(@PathVariable("flightId") long flightId, Model model) {
			FlightModel flight = flightService.findById(flightId);
			flightService.deleteByFlightNumber(flight.getFlightNumber());
			return "flight has been delete";
		}
		
		@PutMapping(value = "/update/{id}")
		private String updateFlightSubmit(
				@PathVariable (value = "id") long id,
				@RequestParam("flightNumber") String flightNumber,
				@RequestParam("origin") String origin,
				@RequestParam("destination") String destination,
				@RequestParam("time") String time) {
			FlightModel flight = (FlightModel) flightService.findById(id);
			if(flight.equals(null)) {
				return "Couldn't find flight";
			}
			flight.setFlightNumber(flightNumber);
			flight.setOrigin(origin);
			flight.setDestination(destination);
			flight.setTime(time);
			flightService.addFlight(flight);
			return "flight update success";

		}

		@GetMapping("/all")
		private List<FlightModel> viewAllFlight(Model model){
			return flightService.viewAllFlight();

		}
		
		@GetMapping(value="/airport/{kota}") 
	    public String getAirport(@PathVariable("kota") String kota) throws Exception{
	    	String path = Setting.flightUrl + "&term=" + kota;
	    	return restTemplate.getForEntity(path, String.class).getBody();
	    }
}
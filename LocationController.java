package com.psa.locationweb6.Controller;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.psa.locationweb6.Repository.LocationRepo;
import com.psa.locationweb6.entities.Location;


@Controller
public class LocationController {
	
	@Autowired
	LocationRepo locationrepo;
	
	@RequestMapping("/showLocation")
	public String showLocation() {
		return "addLocation";
	}
	@RequestMapping("/saveLoc")
	public String saveLoc(@ModelAttribute("location")Location location,ModelMap modelmap) {
		locationrepo.save(location);
		modelmap.addAttribute("msg", "Location is saved");
		return "addLocation";
	}
		
		@RequestMapping("/savelist")
		public String saveList(ModelMap modelmap){
			List<Location> locations = locationrepo.findAll();
			modelmap.addAttribute("locations",locations);
			return "displayLocation";
			
		}
		@RequestMapping("/deleteLocation")
		public String deleteLocation(@RequestParam("id")Long id,ModelMap modelmap) {
			locationrepo.deleteById(id);
			List<Location> locations = locationrepo.findAll();
			modelmap.addAttribute("locations",locations);
			return "displayLocation";
		}
		
		
		
		@RequestMapping("/updateLocation")
		public String updateLocation(@RequestParam("id") Long id, ModelMap modelmap){
			Optional<Location> findById = locationrepo.findById(id);
			Location location = findById.get();
			modelmap.addAttribute("id",location.getId());
			modelmap.addAttribute("code",location.getCode());
			modelmap.addAttribute("name",location.getName());
			modelmap.addAttribute("type",location.getType());
			return "editLocation";
		}
		
		@RequestMapping("/updatesLocation")
		public String updateLocation(@ModelAttribute("location")Location location,ModelMap modelmap) {
			locationrepo.save(location);
			modelmap.addAttribute("mm", "Location is Updated");
			List<Location> locations = locationrepo.findAll();
			modelmap.addAttribute("locations",locations);
			return "displayLocation";
		
		}
	
			
		
}

	

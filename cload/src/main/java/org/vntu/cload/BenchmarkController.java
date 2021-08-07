package org.vntu.cload;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BenchmarkController {

	@GetMapping("/cload")
  public String loading(@RequestParam(required = false, defaultValue = "localhost") String address, @RequestParam(required=false, defaultValue="8181") String port, @RequestParam(required = false, defaultValue = "50") String series, @RequestParam(required = false, defaultValue = "1") String requests, Model model) {

    boolean isNumeric, isLoad = true;

    Benchmark benchmark = new Benchmark();
    benchmark.setAddress(address);
    benchmark.setPort(port);
    benchmark.setSeries(series);
    benchmark.setRequests(requests);

  	model.addAttribute("address", benchmark.getAddress());

    isNumeric = port.chars().allMatch(Character::isDigit);
    if (isNumeric == false) {
  	  model.addAttribute("port", "Error: the string 'port' contains a non-numeric sequence!");
      isLoad = false;
    }
    else 
  	  model.addAttribute("port", benchmark.getPort());

    isNumeric = series.chars().allMatch(Character::isDigit);
    if (isNumeric == false) {
  	  model.addAttribute("port", "Error: the string 'series' contains a non-numeric sequence!");
      isLoad = false;
    }
    else 
  	  model.addAttribute("series", benchmark.getSeries());

    isNumeric = requests.chars().allMatch(Character::isDigit);
    if (isNumeric == false) {
  	  model.addAttribute("port", "Error: the string 'requests' contains a non-numeric sequence!");
      isLoad = false;
    }
    else 
  	model.addAttribute("requests", benchmark.getRequests());

    if (isLoad == true) {
      benchmark.buildBenchmark();
      return "results";
    }
    else
  		return "false_results";
	}

}


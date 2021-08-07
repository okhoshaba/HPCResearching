package org.vntu.cload;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BenchmarkController {

	@GetMapping("/cload")
//  public String loading(@RequestParam(required = false, defaultValue = "localhost") String address, @RequestParam(required=false, defaultValue="8181") String port, @RequestParam(required = false, defaultValue = "50") String series, @RequestParam(required = false, defaultValue = "1") String requests, Model model) {
  public String loading(@RequestParam(required = false, defaultValue = "localhost") String address, @RequestParam(required=false, defaultValue="8181") String port, @RequestParam(required = false, defaultValue = "50") String series, @RequestParam(required = false, defaultValue = "1") String requests, @RequestParam(required=false, defaultValue="3") String durationTime, @RequestParam(required=false, defaultValue="data.txt") String fileName, Model model) {

    boolean isNumeric, isLoad = true;

    InputData inputData = new InputData();
    inputData.setAddress(address);
    inputData.setPort(port);
    inputData.setSeries(series);
    inputData.setRequests(requests);
    inputData.setDurationTime(durationTime);
    inputData.setFileName(fileName);

  	model.addAttribute("address", inputData.getAddress());

    isNumeric = port.chars().allMatch(Character::isDigit);
    if (isNumeric == false) {
  	  model.addAttribute("port", "Error: the string 'port' contains a non-numeric sequence!");
      isLoad = false;
    }
    else 
  	  model.addAttribute("port", inputData.getPort());

    isNumeric = series.chars().allMatch(Character::isDigit);
    if (isNumeric == false) {
  	  model.addAttribute("port", "Error: the string 'series' contains a non-numeric sequence!");
      isLoad = false;
    }
    else 
  	  model.addAttribute("series", inputData.getSeries());

    isNumeric = requests.chars().allMatch(Character::isDigit);
    if (isNumeric == false) {
  	  model.addAttribute("port", "Error: the string 'requests' contains a non-numeric sequence!");
      isLoad = false;
    }
    else 
  	model.addAttribute("requests", inputData.getRequests());

  	model.addAttribute("durationTime", inputData.getDurationTime());
  	model.addAttribute("fileName", inputData.getFileName());

    if (isLoad == true) {
      inputData.buildBenchmark();
      return "results";
    }
    else
  		return "false_results";
	}

}


package org.vntu.cload;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BenchmarkController {

	@GetMapping("/cload")
  public String loading(@RequestParam(required = false, defaultValue = "localhost") String address, @RequestParam(required=false, defaultValue="8181") String port, @RequestParam(required = false, defaultValue = "50") String series, @RequestParam(required = false, defaultValue = "1") String requests, @RequestParam(required=false, defaultValue="3") String durationTime, @RequestParam(required=false, defaultValue="data.txt") String fileName, Model model) {

    boolean isNumeric, isLoad = true;

    DataForRunBenchmark dataForRunBenchmark = new DataForRunBenchmark();
    dataForRunBenchmark.setAddress(address);
    dataForRunBenchmark.setPort(port);
    dataForRunBenchmark.setRequests(requests);
    dataForRunBenchmark.setFileName(fileName);

    InputData inputData = new InputData();
    inputData.setSeries(series);
    inputData.setDurationTime(durationTime);

  	model.addAttribute("address", dataForRunBenchmark.getAddress());

    isNumeric = port.chars().allMatch(Character::isDigit);
    if (isNumeric == false) {
  	  model.addAttribute("port", "Error: the string 'port' contains a non-numeric sequence!");
      isLoad = false;
    }
    else 
  	  model.addAttribute("port", dataForRunBenchmark.getPort());

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
  	  model.addAttribute("requests", dataForRunBenchmark.getRequests());

    isNumeric = durationTime.chars().allMatch(Character::isDigit);
    if (isNumeric == false) {
  	  model.addAttribute("port", "Error: the string 'durationTime' contains a non-numeric sequence!");
      isLoad = false;
    }
    else 
  	  model.addAttribute("durationTime", inputData.getDurationTime());

    model.addAttribute("fileName", dataForRunBenchmark.getFileName());

    if (isLoad == true) {
      inputData.buildBenchmark(dataForRunBenchmark);
      return "results";
    }
    else
  		return "false_results";
	}

}


package org.vntu.cload;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
/**
* The class Benchmark application represents the HPCResearching Program Environment
* @author Oleksandr Khoshaba
*/ 
public class BenchmarkApplication {

/** 
 *
 * The method Main calls the class {@link BenchmarkApplication}
 *
 * @param args  the args for input {@link String}
*/
  public static void main(String[] args) {
		SpringApplication.run(BenchmarkApplication.class, args);
	}

}

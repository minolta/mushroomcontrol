package me.pixka.het.t;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import me.pixka.device.s.Dht22Service;
import me.pixka.device.s.DisplayService;


@Component
public class HetTask implements CommandLineRunner {

	@Autowired
	private Dht22Service dht22service;
	@Autowired
	private DisplayService displayService;
	@Override
	public void run(String... arg0) throws Exception {

		dht22service.read();
		
		
		
		
		displayService.printRunstatus();
		displayService.printDsvalue();
		displayService.checkLCDon();
		
		
		
		
		
	}

}

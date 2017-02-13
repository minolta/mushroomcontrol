package me.pixka.het.c;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import me.pixka.c.HttpControl;
import me.pixka.data.ISODateAdapter;
import me.pixka.device.d.Deviceconfig;
import me.pixka.device.s.DeviceconfigService;

@Controller
public class HetControl {
	private static Gson g = new GsonBuilder().registerTypeAdapter(Date.class, new ISODateAdapter()).create();
	@Autowired
	private DeviceconfigService service;

	@Autowired
	private HttpControl http;

	/**
	 * ใช้หา Deviceconfig ที่มีความเชื้อช่วงนี
	 * 
	 * @param h
	 * @return
	 * @throws IOException
	 */
	public Deviceconfig findByH(Long deviceid, BigDecimal h) throws IOException {

		String url = "http://61.19.255.23:3333/finddeviceconfigbyh/" + deviceid + "/" + h;
		String re = http.get(url);
		return g.fromJson(re, Deviceconfig.class);
	}

	public Deviceconfig findByH(String mac, BigDecimal h) throws IOException {
		String url = "http://61.19.255.23:3333/finddeviceconfigbymach/" + mac + "/" + h;
		String re = http.get(url);
		return g.fromJson(re, Deviceconfig.class);
	}

}

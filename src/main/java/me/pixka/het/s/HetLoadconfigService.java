package me.pixka.het.s;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import me.pixka.c.HttpControl;
import me.pixka.c.PiDevice;
import me.pixka.data.ISODateAdapter;
import me.pixka.device.d.Deviceconfig;

@Service
public class HetLoadconfigService {

	private static Gson g = new GsonBuilder().registerTypeAdapter(Date.class, new ISODateAdapter()).create();

	@Autowired
	private HttpControl http;

	@Autowired
	private PiDevice pi;

	@Async
	public void loadallconfigs() {

		try {

			String url = "http://localhost:3333/loadconfigs/" + pi.wifi();
			String re = http.get(url);

			Type listType = new TypeToken<ArrayList<Deviceconfig>>() {
			}.getType();

			List<Deviceconfig> list = new Gson().fromJson(re, listType);

			for (Deviceconfig item : list) {
				System.out.println(item);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

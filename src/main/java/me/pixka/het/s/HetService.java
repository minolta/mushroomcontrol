package me.pixka.het.s;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

import me.pixka.c.DHT22;
import me.pixka.c.PiDevice;
import me.pixka.device.d.Deviceconfig;
import me.pixka.het.c.HetControl;

@Service
public class HetService {

	@Autowired
	private DHT22 dht22;
	@Autowired
	private HetControl hc;

	@Autowired
	private PiDevice pi;
	private BigDecimal t, h;

	@Async
	public void run() {

		while (true) {
			try {

				dht22.read();

				h = dht22.getH();
				t = dht22.getT();

				Deviceconfig hdcf = hc.findByH(pi.wifi(), h);

				if (hdcf != null) {
					// run by h config

				}

			} catch (Exception e) {

			}
		}
	}

	private GpioPinDigitalOutput pin0 = null;
	private GpioPinDigitalOutput pin1 = null;
	private GpioPinDigitalOutput pin2 = null;
	private GpioPinDigitalOutput pin3 = null;
	private GpioPinDigitalOutput pin4 = null;
	private GpioPinDigitalOutput pin5 = null;
	private GpioPinDigitalOutput pin6 = null;
	private GpioPinDigitalOutput pin7 = null;
	private GpioController gpio = GpioFactory.getInstance();

	public void initIO() {
		pin0 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_00, "io0", PinState.HIGH);
		pin1 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "io1", PinState.HIGH);
		pin2 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_02, "io2", PinState.HIGH);
		pin3 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_03, "io3", PinState.HIGH);
		pin4 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_04, "io4", PinState.HIGH);
		pin5 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_05, "io5", PinState.HIGH);
		pin6 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_06, "io6", PinState.HIGH);
		pin7 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_07, "io7", PinState.HIGH);
	}

	public void run(Deviceconfig run) {

		try {
			Long runtime = run.getRuntime();

			pin0.setState(run.getPort1open());
			pin1.setState(run.getPort2open());
			pin2.setState(run.getPort3open());
			pin3.setState(run.getPort4open());
			pin4.setState(run.getPort5open());
			pin5.setState(run.getPort6open());
			pin6.setState(run.getPort7open());
			pin7.setState(run.getPort8open());

			Thread.sleep(runtime);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

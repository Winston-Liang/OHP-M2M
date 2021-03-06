package com.ain.coapserver.coapresources;

import static org.eclipse.californium.core.coap.CoAP.ResponseCode.CONTENT;
import static org.eclipse.californium.core.coap.MediaTypeRegistry.TEXT_PLAIN;

import java.util.Timer;
import java.util.TimerTask;

import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.coap.CoAP.Type;
import org.eclipse.californium.core.coap.MediaTypeRegistry;
import org.eclipse.californium.core.server.resources.CoapExchange;

import com.ain.ihepcd.transaction.ParserDevice10420;

public class D10420PCD01Resource extends CoapResource{

	
	private int dataCf = MediaTypeRegistry.TEXT_PLAIN;
	private static int TIME_SPAN = 5 * 1000;
	private Timer timer;
	private ParserDevice10420 m_device_10420;
	
	public D10420PCD01Resource(String name, ParserDevice10420 device_10420) {
		super(name);
		this.m_device_10420 = device_10420;
		setObservable(true);
		getAttributes().setTitle("D10420 PCD01");
		getAttributes().addResourceType("D10420 PCD01");
		getAttributes().setObservable();
		setObserveType(Type.CON);
		
		timer = new Timer();
		timer.schedule(new TimeTask(), 0, TIME_SPAN);
		
	}
	
	private class TimeTask extends TimerTask{
		@Override
		public void run(){
			dataCf = TEXT_PLAIN;
			changed();
		}		
	}
	
	@Override
	public void handleDELETE(CoapExchange exchange) {
		// TODO Auto-generated method stub
		super.handleDELETE(exchange);
	}
	@Override
	public void handleGET(CoapExchange exchange) {
		// TODO Auto-generated method stub
		
		exchange.setMaxAge(5);
		exchange.respond(CONTENT, m_device_10420.m_Msg.toString(),dataCf);
	}
	@Override
	public void handlePOST(CoapExchange exchange) {
		// TODO Auto-generated method stub
		super.handlePOST(exchange);
	}
	@Override
	public void handlePUT(CoapExchange exchange) {
		// TODO Auto-generated method stub
		super.handlePUT(exchange);
	}

}

package com.daniel.medicaldevice.spo2;

import org.eclipse.om2m.commons.obix.Contract;
import org.eclipse.om2m.commons.obix.Int;
import org.eclipse.om2m.commons.obix.Obj;
import org.eclipse.om2m.commons.obix.Op;
import org.eclipse.om2m.commons.obix.Uri;
import org.eclipse.om2m.commons.obix.io.ObixEncoder;

public class Mapper {
	public static String getSensorDescriptorRep(String sclId, String appId,
			String ipuId) {
		Obj obj = new org.eclipse.om2m.commons.obix.Obj();

		Op opGet = new Op();
		opGet.setName("GET");
		opGet.setHref(new Uri(sclId + "/applications/" + appId
				+ "/containers/DATA/contentInstances/latest/content"));
		opGet.setIs(new Contract("retrieve"));
		obj.add(opGet);

		Op opGetDirect = new Op();
		opGetDirect.setName("GET(Direct)");
		opGetDirect.setHref(new Uri(sclId + "/applications/" + appId + "/"
				+ ipuId));
		opGetDirect.setIs(new Contract("retrieve"));
		obj.add(opGetDirect);

		return ObixEncoder.toString(obj);
	}

	public static String getSensorDataRep(int value) {
		Obj obj = new Obj();
		obj.add(new Int("data", value));
		return ObixEncoder.toString(obj);
	}
}

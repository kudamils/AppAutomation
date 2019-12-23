package apps.util;

import id.aldochristiaan.salad.util.LogUtil;
import staid.openstf.Device;
import staid.openstf.StaidOpenSTF;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Properties;

public class WaitDeviceStf {

    private static final String DEVICE_SERIAL = "deviceSerial";
    private static final String DEVICE_REMOTE_URL = "deviceRemoteUrl";

    private static HashMap<String, String> waitUntilDeviceReady() {
        String targetDevice = "";
        HashMap<String, String> devicesInfo = new HashMap<>();
        try {
            StaidOpenSTF staidOpenSTF = new StaidOpenSTF(System.getProperty("stfUrl"), System.getProperty("stfToken"));
            Device stfDevice = new Device(staidOpenSTF);
            targetDevice = stfDevice.waitAvailableDevice();
            LogUtil.info("Set Property stfDeviceSerial: " + targetDevice);
            stfDevice.setSerial(targetDevice);
            stfDevice.connectToSTFDevice();

            //Store Udid & Remote Device Url
            devicesInfo.put(DEVICE_SERIAL, targetDevice);
            devicesInfo.put(DEVICE_REMOTE_URL, stfDevice.getRemoteDeviceUrl(targetDevice));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return devicesInfo;
    }

    public static void checkPropUsingSTF(Properties capabilitiesProperties) {
        String targetDeviceFromCli = System.getProperty("stfDeviceSerial") != null ? System.getProperty("stfDeviceSerial") : "";
        if (targetDeviceFromCli.equalsIgnoreCase("random") && capabilitiesProperties.getProperty("udid") == null) {
            HashMap<String, String> devices = waitUntilDeviceReady();
            storeToCapabilities(capabilitiesProperties, devices.get(DEVICE_SERIAL), devices.get(DEVICE_REMOTE_URL));
        }
    }

    private static void storeToCapabilities(Properties properties, String deviceUdid, String remoteConnectUdid) {
        try (OutputStream output = new FileOutputStream("capabilities.properties")) {

            // set the properties value
            // set udid to capabilitis.properties to prevent missmatch device
            properties.setProperty("stfDeviceSerial", deviceUdid);
            properties.setProperty("udid", remoteConnectUdid);

            // save properties to project root folder
            properties.store(output, null);

        } catch (IOException io) {
            io.printStackTrace();
        }
    }

}
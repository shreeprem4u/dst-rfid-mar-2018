//Unmodified of code given by Vimal
package com.example.sdksamples;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.json.simple.JSONObject;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import com.impinj.octane.*;
import AlgmImple.*;


public class TxPowerRamp {
	
    public static double p;

	public static void main(String[] args) {
        try {
            String hostname = System.getProperty(SampleProperties.hostname);

            if (hostname == null) {
                throw new Exception("Must specify the '"
                        + SampleProperties.hostname + "' property");
            }

            ImpinjReader reader = new ImpinjReader();

            // Connect
            System.out.println("Connecting to " + hostname);
            reader.connect(hostname);

            // get the features
            FeatureSet features = reader.queryFeatureSet();

            // Get the default settings
            Settings settings = reader.queryDefaultSettings();



            
            // send a tag report for every tag read
            settings.getReport().setMode(ReportMode.Individual);
            settings.getReport().setIncludePeakRssi(true);
            
            ReportConfig report = settings.getReport();
            report.setIncludeAntennaPortNumber(true);
            //report.setIncludeFirstSeenTime(true);
            
            // set just one specific antenna for this example
            AntennaConfigGroup ac = settings.getAntennas();

            ac.disableAll();
            ac.getAntenna((short) 1).setEnabled(true);
            ac.getAntenna((short) 2).setEnabled(true);
            ac.getAntenna((short) 3).setEnabled(true);
            ac.getAntenna((short) 4).setEnabled(true);
            // connect a listener
            //reader.setTagReportListener(new TagReportListenerImplementation());
            
            //double pp = TagReportListenerImplementation.data();
            
            
            
            for (TxPowerTableEntry t : features.getTxPowers()) {
            	p = t.Dbm;
                System.out.println("Setting power to " + t.Dbm);
                ac.getAntenna((short) 1).setIsMaxTxPower(false);
                ac.getAntenna((short) 2).setIsMaxTxPower(false);
                ac.getAntenna((short) 3).setIsMaxTxPower(false);
                ac.getAntenna((short) 4).setIsMaxTxPower(false);
                
                ac.getAntenna((short) 1).setTxPowerinDbm(t.Dbm);
                ac.getAntenna((short) 2).setTxPowerinDbm(t.Dbm);
                ac.getAntenna((short) 3).setTxPowerinDbm(t.Dbm);
                ac.getAntenna((short) 4).setTxPowerinDbm(t.Dbm);
             //   Thread.sleep(1000);
               
              
                // Apply the new settings
                reader.applySettings(settings);
                reader.start();
                
                	
                  reader.setTagReportListener(new TagReportListenerImplementation());
                  
                  String ms = TagReportListenerImplementation.data();
                  //System.out.println("msg" + ms);
                  
                  
                
                //MainDemo.main(args);

                // Start the reader
                

               try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    // ignore this since its just an example and just keep going
                }
                reader.stop();
            }
            
            

            System.out.println("Disconnecting from " + hostname);
            reader.disconnect();

            System.out.println("Done");
        } catch (OctaneSdkException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace(System.out);
        }
         
/*
        HashMap<String, String> people = TagReportListenerImplementation.getdata();
        Set set = people.entrySet();
        Iterator iterator = set.iterator();
        while(iterator.hasNext()) {
           Map.Entry mentry = (Map.Entry)iterator.next();
           System.out.print("key is: "+ mentry.getKey() + " & Value is: ");
           System.out.println(mentry.getValue());
           String ab = mentry.getKey().toString();
           String ac = mentry.getValue().toString();
           
  */         

   //        JSONObject obj = new JSONObject();
           //obj.put("name", "rfid");
   //        obj.put("EPC", ab);
   //        obj.put("EPC", ac);
  //         
   //        try (BufferedWriter file=new BufferedWriter(new FileWriter("/home/researcher/test.json", true))){
   //            file.write(obj.toJSONString());
   //            file.write("\n");
    //           file.flush();
              
               
     //      } catch (IOException e) {
    //           e.printStackTrace();
   //        }
           
     //   }
        
        //MainDemo m = new MainDemo();
   
    }
	
    public static double power()
    {
        return p;
    }
    

}



/*
package com.example.sdksamples;

import com.impinj.octane.*;

public class TxPowerRamp {

    public static void main(String[] args) {
        try {
            String hostname = System.getProperty(SampleProperties.hostname);

            if (hostname == null) {
                throw new Exception("Must specify the '"
                        + SampleProperties.hostname + "' property");
            }

            ImpinjReader reader = new ImpinjReader();

            // Connect
            System.out.println("Connecting to " + hostname);
            reader.connect(hostname);

            // get the features
            FeatureSet features = reader.queryFeatureSet();

            // Get the default settings
            Settings settings = reader.queryDefaultSettings();

            // send a tag report for every tag read
            settings.getReport().setMode(ReportMode.Individual);
            settings.getReport().setIncludePeakRssi(true);

            // set just one specific antenna for this example
            AntennaConfigGroup ac = settings.getAntennas();

            ac.disableAll();
            ac.getAntenna((short) 1).setEnabled(true);

            // connect a listener
            reader.setTagReportListener(new TagReportListenerImplementation());

            for (TxPowerTableEntry t : features.getTxPowers()) {
                System.out.println("Setting power to " + t.Dbm);
                ac.getAntenna((short) 1).setIsMaxTxPower(false);
                ac.getAntenna((short) 1).setTxPowerinDbm(t.Dbm);
                // Apply the new settings
                reader.applySettings(settings);

                // Start the reader
                reader.start();

                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    // ignore this since its just an example and just keep going
                }
                reader.stop();
            }

            System.out.println("Disconnecting from " + hostname);
            reader.disconnect();

            System.out.println("Done");
        } catch (OctaneSdkException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace(System.out);
        }
    }
}
*/
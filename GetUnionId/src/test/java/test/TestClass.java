package test;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.junit.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.*;

public class TestClass {
    @Test
    public void testJSONObjcet(){
        Map<String, String> REQUEST_DATA = new HashMap<String, String>();
        REQUEST_DATA.put("strLocNo","1");
        REQUEST_DATA.put("wmsid","1");
        REQUEST_DATA.put("strRemark", "备注");
        REQUEST_DATA.put("strLicense_Plate", "粤B11223");
//			REQUEST_DATA.put("strLocNo",driver.getWmsid());
//			REQUEST_DATA.put("wmsid",driver.getWmsid());
//			REQUEST_DATA.put("strLicense_Plate",driver.getPlateNo());
        REQUEST_DATA.put("strParameter", "123");
        REQUEST_DATA.put("strOwner_No", "001");
        JSONObject sz = JSONObject.fromObject(REQUEST_DATA);
        System.out.println(sz);
    }

    @Test
    public void testMaxJson(){
        String xml="{\"cardlist\":[{\"occudate\":\"2018-01-30 00:00:00.0\",\"memberid\":\"5880790185482290848\",\"totalpoints\":\"-\",\"sheettypename\":\"消费增加积分\",\"cardno\":\"5880790185482290848\",\"points\":\"59.00\",\"directflag\":\"1\",\"paymoney\":\"59.46\",\"serialid\":\"89257\"},{\"occudate\":\"2018-01-30 00:00:00.0\",\"memberid\":\"6968408767626049233\",\"totalpoints\":\"-\",\"sheettypename\":\"消费增加积分\",\"cardno\":\"6968408767626049233\",\"points\":\"69.00\",\"directflag\":\"1\",\"paymoney\":\"69.00\",\"serialid\":\"89258\"},{\"occudate\":\"2018-01-30 00:00:00.0\",\"memberid\":\"6765230216312877864\",\"totalpoints\":\"-\",\"sheettypename\":\"消费增加积分\",\"cardno\":\"6765230216312877864\",\"points\":\"6.00\",\"directflag\":\"1\",\"paymoney\":\"6.66\",\"serialid\":\"89259\"},{\"occudate\":\"2018-01-30 00:00:00.0\",\"memberid\":\"6553217431057305059\",\"totalpoints\":\"-\",\"sheettypename\":\"消费增加积分\",\"cardno\":\"6553217431057305059\",\"points\":\"4.00\",\"directflag\":\"1\",\"paymoney\":\"4.50\",\"serialid\":\"89260\"},{\"occudate\":\"2018-01-30 00:00:00.0\",\"memberid\":\"6631570290445990063\",\"totalpoints\":\"-\",\"sheettypename\":\"消费增加积分\",\"cardno\":\"6631570290445990063\",\"points\":\"385.00\",\"directflag\":\"1\",\"paymoney\":\"385.85\",\"serialid\":\"89261\"},{\"occudate\":\"2018-01-30 00:00:00.0\",\"memberid\":\"6863681959899555505\",\"totalpoints\":\"-\",\"sheettypename\":\"消费增加积分\",\"cardno\":\"6863681959899555505\",\"points\":\"34.00\",\"directflag\":\"1\",\"paymoney\":\"33.70\",\"serialid\":\"89262\"},{\"occudate\":\"2018-01-30 00:00:00.0\",\"memberid\":\"5846397137675781793\",\"totalpoints\":\"-\",\"sheettypename\":\"消费增加积分\",\"cardno\":\"5846397137675781793\",\"points\":\"92.00\",\"directflag\":\"1\",\"paymoney\":\"92.08\",\"serialid\":\"89263\"},{\"occudate\":\"2018-01-30 00:00:00.0\",\"memberid\":\"5785643524036003488\",\"totalpoints\":\"-\",\"sheettypename\":\"消费增加积分\",\"cardno\":\"5785643524036003488\",\"points\":\"55.00\",\"directflag\":\"1\",\"paymoney\":\"55.59\",\"serialid\":\"89264\"},{\"occudate\":\"2018-01-30 00:00:00.0\",\"memberid\":\"5785642572166366880\",\"totalpoints\":\"-\",\"sheettypename\":\"消费增加积分\",\"cardno\":\"5785642572166366880\",\"points\":\"208.00\",\"directflag\":\"1\",\"paymoney\":\"208.60\",\"serialid\":\"89265\"},{\"occudate\":\"2018-01-30 00:00:00.0\",\"memberid\":\"5846397095967295137\",\"totalpoints\":\"-\",\"sheettypename\":\"消费增加积分\",\"cardno\":\"5846397095967295137\",\"points\":\"18.00\",\"directflag\":\"1\",\"paymoney\":\"18.05\",\"serialid\":\"89266\"},{\"occudate\":\"2018-01-30 00:00:00.0\",\"memberid\":\"5846396558851212961\",\"totalpoints\":\"-\",\"sheettypename\":\"消费增加积分\",\"cardno\":\"5846396558851212961\",\"points\":\"2.00\",\"directflag\":\"1\",\"paymoney\":\"12.70\",\"serialid\":\"89267\"},{\"occudate\":\"2018-01-30 00:00:00.0\",\"memberid\":\"6942281651511709312\",\"totalpoints\":\"-\",\"sheettypename\":\"消费增加积分\",\"cardno\":\"6942281651511709312\",\"points\":\"10.00\",\"directflag\":\"1\",\"paymoney\":\"9.80\",\"serialid\":\"89268\"},{\"occudate\":\"2018-01-30 00:00:00.0\",\"memberid\":\"6564818928685668391\",\"totalpoints\":\"-\",\"sheettypename\":\"消费增加积分\",\"cardno\":\"6564818928685668391\",\"points\":\"184.00\",\"directflag\":\"1\",\"paymoney\":\"184.51\",\"serialid\":\"89269\"},{\"occudate\":\"2018-01-30 00:00:00.0\",\"memberid\":\"6767611999251670450\",\"totalpoints\":\"-\",\"sheettypename\":\"消费增加积分\",\"cardno\":\"6767611999251670450\",\"points\":\"71.00\",\"directflag\":\"1\",\"paymoney\":\"101.73\",\"serialid\":\"89270\"},{\"occudate\":\"2018-01-30 00:00:00.0\",\"memberid\":\"5785643342094763680\",\"totalpoints\":\"-\",\"sheettypename\":\"消费增加积分\",\"cardno\":\"5785643342094763680\",\"points\":\"19.00\",\"directflag\":\"1\",\"paymoney\":\"19.30\",\"serialid\":\"89271\"},{\"occudate\":\"2018-01-30 00:00:00.0\",\"memberid\":\"5773952177226024609\",\"totalpoints\":\"-\",\"sheettypename\":\"消费增加积分\",\"cardno\":\"5773952177226024609\",\"points\":\"70.00\",\"directflag\":\"1\",\"paymoney\":\"130.80\",\"serialid\":\"89272\"},{\"occudate\":\"2018-01-30 00:00:00.0\",\"memberid\":\"6802806257600275377\",\"totalpoints\":\"-\",\"sheettypename\":\"消费增加积分\",\"cardno\":\"6802806257600275377\",\"points\":\"64.00\",\"directflag\":\"1\",\"paymoney\":\"64.57\",\"serialid\":\"89273\"},{\"occudate\":\"2018-01-30 00:00:00.0\",\"memberid\":\"6681127320474960941\",\"totalpoints\":\"-\",\"sheettypename\":\"消费增加积分\",\"cardno\":\"6681127320474960941\",\"points\":\"65.00\",\"directflag\":\"1\",\"paymoney\":\"65.40\",\"serialid\":\"89274\"},{\"occudate\":\"2018-01-30 00:00:00.0\",\"memberid\":\"6760963064282086616\",\"totalpoints\":\"-\",\"sheettypename\":\"消费增加积分\",\"cardno\":\"6760963064282086616\",\"points\":\"187.00\",\"directflag\":\"1\",\"paymoney\":\"187.12\",\"serialid\":\"89275\"},{\"occudate\":\"2018-01-30 00:00:00.0\",\"memberid\":\"5785642694988433056\",\"totalpoints\":\"-\",\"sheettypename\":\"消费增加积分\",\"cardno\":\"5785642694988433056\",\"points\":\"84.00\",\"directflag\":\"1\",\"paymoney\":\"84.61\",\"serialid\":\"89276\"},{\"occudate\":\"2018-01-30 00:00:00.0\",\"memberid\":\"5785643414059583136\",\"totalpoints\":\"-\",\"sheettypename\":\"消费增加积分\",\"cardno\":\"5785643414059583136\",\"points\":\"50.00\",\"directflag\":\"1\",\"paymoney\":\"50.37\",\"serialid\":\"89277\"},{\"occudate\":\"2018-01-30 00:00:00.0\",\"memberid\":\"6460934830888701606\",\"totalpoints\":\"-\",\"sheettypename\":\"消费增加积分\",\"cardno\":\"6460934830888701606\",\"points\":\"28.00\",\"directflag\":\"1\",\"paymoney\":\"28.50\",\"serialid\":\"89278\"},{\"occudate\":\"2018-01-30 00:00:00.0\",\"memberid\":\"6939361766498479490\",\"totalpoints\":\"-\",\"sheettypename\":\"消费增加积分\",\"cardno\":\"6939361766498479490\",\"points\":\"264.00\",\"directflag\":\"1\",\"paymoney\":\"265.79\",\"serialid\":\"89279\"},{\"occudate\":\"2018-01-30 00:00:00.0\",\"memberid\":\"5766476560960685728\",\"totalpoints\":\"-\",\"sheettypename\":\"消费增加积分\",\"cardno\":\"5766476560960685728\",\"points\":\"16.00\",\"directflag\":\"1\",\"paymoney\":\"16.89\",\"serialid\":\"89280\"},{\"occudate\":\"2018-01-30 00:00:00.0\",\"memberid\":\"6636038611513995262\",\"totalpoints\":\"-\",\"sheettypename\":\"消费增加积分\",\"cardno\":\"6636038611513995262\",\"points\":\"15.00\",\"directflag\":\"1\",\"paymoney\":\"15.20\",\"serialid\":\"89281\"},{\"occudate\":\"2018-01-30 00:00:00.0\",\"memberid\":\"6695834564957432013\",\"totalpoints\":\"-\",\"sheettypename\":\"消费增加积分\",\"cardno\":\"6695834564957432013\",\"points\":\"41.00\",\"directflag\":\"1\",\"paymoney\":\"53.63\",\"serialid\":\"89282\"},{\"occudate\":\"2018-01-30 00:00:00.0\",\"memberid\":\"5766476535552050849\",\"totalpoints\":\"-\",\"sheettypename\":\"消费增加积分\",\"cardno\":\"5766476535552050849\",\"points\":\"77.00\",\"directflag\":\"1\",\"paymoney\":\"77.15\",\"serialid\":\"89283\"},{\"occudate\":\"2018-01-30 00:00:00.0\",\"memberid\":\"5766476704922961569\",\"totalpoints\":\"-\",\"sheettypename\":\"消费增加积分\",\"cardno\":\"5766476704922961569\",\"points\":\"49.00\",\"directflag\":\"1\",\"paymoney\":\"49.90\",\"serialid\":\"89284\"},{\"occudate\":\"2018-01-30 00:00:00.0\",\"memberid\":\"6499758089205521979\",\"totalpoints\":\"-\",\"sheettypename\":\"消费增加积分\",\"cardno\":\"6499758089205521979\",\"points\":\"4.00\",\"directflag\":\"1\",\"paymoney\":\"4.99\",\"serialid\":\"89285\"},{\"occudate\":\"2018-01-30 00:00:00.0\",\"memberid\":\"5785642586798524064\",\"totalpoints\":\"-\",\"sheettypename\":\"消费增加积分\",\"cardno\":\"5785642586798524064\",\"points\":\"50.00\",\"directflag\":\"1\",\"paymoney\":\"50.00\",\"serialid\":\"89286\"},{\"occudate\":\"2018-01-30 00:00:00.0\",\"memberid\":\"6839238251435362229\",\"totalpoints\":\"-\",\"sheettypename\":\"消费增加积分\",\"cardno\":\"6839238251435362229\",\"points\":\"5.00\",\"directflag\":\"1\",\"paymoney\":\"5.50\",\"serialid\":\"89287\"},{\"occudate\":\"2018-01-30 00:00:00.0\",\"memberid\":\"6466742113967096667\",\"totalpoints\":\"-\",\"sheettypename\":\"消费增加积分\",\"cardno\":\"6466742113967096667\",\"points\":\"30.00\",\"directflag\":\"1\",\"paymoney\":\"30.00\",\"serialid\":\"89288\"},{\"occudate\":\"2018-01-30 00:00:00.0\",\"memberid\":\"6644610639766583691\",\"totalpoints\":\"-\",\"sheettypename\":\"消费增加积分\",\"cardno\":\"6644610639766583691\",\"points\":\"37.00\",\"directflag\":\"1\",\"paymoney\":\"37.44\",\"serialid\":\"89289\"},{\"occudate\":\"2018-01-30 00:00:00.0\",\"memberid\":\"5785642874962216608\",\"totalpoints\":\"-\",\"sheettypename\":\"消费增加积分\",\"cardno\":\"5785642874962216608\",\"points\":\"15.00\",\"directflag\":\"1\",\"paymoney\":\"15.05\",\"serialid\":\"89290\"},{\"occudate\":\"2018-01-30 00:00:00.0\",\"memberid\":\"5785642639358920353\",\"totalpoints\":\"-\",\"sheettypename\":\"消费增加积分\",\"cardno\":\"5785642639358920353\",\"points\":\"31.00\",\"directflag\":\"1\",\"paymoney\":\"31.70\",\"serialid\":\"89291\"},{\"occudate\":\"2018-01-30 00:00:00.0\",\"memberid\":\"6587046227765967837\",\"totalpoints\":\"-\",\"sheettypename\":\"消费增加积分\",\"cardno\":\"6587046227765967837\",\"points\":\"43.00\",\"directflag\":\"1\",\"paymoney\":\"43.50\",\"serialid\":\"89292\"},{\"occudate\":\"2018-01-30 00:00:00.0\",\"memberid\":\"5785643185018603169\",\"totalpoints\":\"-\",\"sheettypename\":\"消费增加积分\",\"cardno\":\"5785643185018603169\",\"points\":\"27.00\",\"directflag\":\"1\",\"paymoney\":\"27.43\",\"serialid\":\"89293\"},{\"occudate\":\"2018-01-30 00:00:00.0\",\"memberid\":\"5785643352741873313\",\"totalpoints\":\"-\",\"sheettypename\":\"消费增加积分\",\"cardno\":\"5785643352741873313\",\"points\":\"94.00\",\"directflag\":\"1\",\"paymoney\":\"94.40\",\"serialid\":\"89294\"},{\"occudate\":\"2018-01-30 00:00:00.0\",\"memberid\":\"6505607556412573768\",\"totalpoints\":\"-\",\"sheettypename\":\"消费增加积分\",\"cardno\":\"6505607556412573768\",\"points\":\"8.00\",\"directflag\":\"1\",\"paymoney\":\"8.90\",\"serialid\":\"89295\"},{\"occudate\":\"2018-01-30 00:00:00.0\",\"memberid\":\"6914234786686031237\",\"totalpoints\":\"-\",\"sheettypename\":\"消费增加积分\",\"cardno\":\"6914234786686031237\",\"points\":\"0.00\",\"directflag\":\"1\",\"paymoney\":\"10.44\",\"serialid\":\"89296\"},{\"occudate\":\"2018-01-30 00:00:00.0\",\"memberid\":\"6766577298428779850\",\"totalpoints\":\"-\",\"sheettypename\":\"消费增加积分\",\"cardno\":\"6766577298428779850\",\"points\":\"249.00\",\"directflag\":\"1\",\"paymoney\":\"265.36\",\"serialid\":\"89297\"},{\"occudate\":\"2018-01-30 00:00:00.0\",\"memberid\":\"6893945533733387833\",\"totalpoints\":\"-\",\"sheettypename\":\"消费增加积分\",\"cardno\":\"6893945533733387833\",\"points\":\"57.00\",\"directflag\":\"1\",\"paymoney\":\"57.89\",\"serialid\":\"89298\"},{\"occudate\":\"2018-01-30 00:00:00.0\",\"memberid\":\"5773952158248371872\",\"totalpoints\":\"-\",\"sheettypename\":\"消费增加积分\",\"cardno\":\"5773952158248371872\",\"points\":\"29.00\",\"directflag\":\"1\",\"paymoney\":\"29.70\",\"serialid\":\"89299\"},{\"occudate\":\"2018-01-30 00:00:00.0\",\"memberid\":\"5785642843326023328\",\"totalpoints\":\"-\",\"sheettypename\":\"消费增加积分\",\"cardno\":\"5785642843326023328\",\"points\":\"149.00\",\"directflag\":\"1\",\"paymoney\":\"179.04\",\"serialid\":\"89300\"},{\"occudate\":\"2018-01-30 00:00:00.0\",\"memberid\":\"5785642622335157920\",\"totalpoints\":\"-\",\"sheettypename\":\"消费增加积分\",\"cardno\":\"5785642622335157920\",\"points\":\"99.00\",\"directflag\":\"1\",\"paymoney\":\"99.80\",\"serialid\":\"89301\"},{\"occudate\":\"2018-01-30 00:00:00.0\",\"memberid\":\"6985153445608142143\",\"totalpoints\":\"-\",\"sheettypename\":\"消费增加积分\",\"cardno\":\"6985153445608142143\",\"points\":\"2208.00\",\"directflag\":\"1\",\"paymoney\":\"2208.00\",\"serialid\":\"89302\"},{\"occudate\":\"2018-01-30 00:00:00.0\",\"memberid\":\"5766476693259912865\",\"totalpoints\":\"-\",\"sheettypename\":\"消费增加积分\",\"cardno\":\"5766476693259912865\",\"points\":\"15.00\",\"directflag\":\"1\",\"paymoney\":\"15.01\",\"serialid\":\"89303\"},{\"occudate\":\"2018-01-30 00:00:00.0\",\"memberid\":\"5854907105683932832\",\"totalpoints\":\"-\",\"sheettypename\":\"消费增加积分\",\"cardno\":\"5854907105683932832\",\"points\":\"12.00\",\"directflag\":\"1\",\"paymoney\":\"11.90\",\"serialid\":\"89304\"},{\"occudate\":\"2018-01-30 00:00:00.0\",\"memberid\":\"6489982616585968050\",\"totalpoints\":\"-\",\"sheettypename\":\"消费增加积分\",\"cardno\":\"6489982616585968050\",\"points\":\"54.00\",\"directflag\":\"1\",\"paymoney\":\"54.67\",\"serialid\":\"89305\"},{\"occudate\":\"2018-01-30 00:00:00.0\",\"memberid\":\"5845060783448491680\",\"totalpoints\":\"-\",\"sheettypename\":\"消费增加积分\",\"cardno\":\"5845060783448491680\",\"points\":\"147.00\",\"directflag\":\"1\",\"paymoney\":\"147.39\",\"serialid\":\"89306\"},{\"occudate\":\"2018-01-30 00:00:00.0\",\"memberid\":\"5785642677531870880\",\"totalpoints\":\"-\",\"sheettypename\":\"消费增加积分\",\"cardno\":\"5785642677531870880\",\"points\":\"92.00\",\"directflag\":\"1\",\"paymoney\":\"92.00\",\"serialid\":\"89307\"},{\"occudate\":\"2018-01-30 00:00:00.0\",\"memberid\":\"6976456280446727967\",\"totalpoints\":\"-\",\"sheettypename\":\"消费增加积分\",\"cardno\":\"6976456280446727967\",\"points\":\"26.00\",\"directflag\":\"1\",\"paymoney\":\"26.65\",\"serialid\":\"89308\"},{\"occudate\":\"2018-01-30 00:00:00.0\",\"memberid\":\"5845060556580488864\",\"totalpoints\":\"-\",\"sheettypename\":\"消费增加积分\",\"cardno\":\"5845060556580488864\",\"points\":\"93.00\",\"directflag\":\"1\",\"paymoney\":\"93.90\",\"serialid\":\"89309\"},{\"occudate\":\"2018-01-30 00:00:00.0\",\"memberid\":\"5880789779623872160\",\"totalpoints\":\"-\",\"sheettypename\":\"消费增加积分\",\"cardno\":\"5880789779623872160\",\"points\":\"278.00\",\"directflag\":\"1\",\"paymoney\":\"278.53\",\"serialid\":\"89310\"},{\"occudate\":\"2018-01-30 00:00:00.0\",\"memberid\":\"5785642918006982304\",\"totalpoints\":\"-\",\"sheettypename\":\"消费增加积分\",\"cardno\":\"5785642918006982304\",\"points\":\"8324.00\",\"directflag\":\"1\",\"paymoney\":\"4162.00\",\"serialid\":\"89311\"},{\"occudate\":\"2018-01-30 00:00:00.0\",\"memberid\":\"6612695465837292605\",\"totalpoints\":\"-\",\"sheettypename\":\"消费增加积分\",\"cardno\":\"6612695465837292605\",\"points\":\"22.00\",\"directflag\":\"1\",\"paymoney\":\"21.80\",\"serialid\":\"89312\"},{\"occudate\":\"2018-01-30 00:00:00.0\",\"memberid\":\"6883908862459587009\",\"totalpoints\":\"-\",\"sheettypename\":\"消费增加积分\",\"cardno\":\"6883908862459587009\",\"points\":\"299.00\",\"directflag\":\"1\",\"paymoney\":\"299.00\",\"serialid\":\"89313\"},{\"occudate\":\"2018-01-30 00:00:00.0\",\"memberid\":\"6977017546168939458\",\"totalpoints\":\"-\",\"sheettypename\":\"消费增加积分\",\"cardno\":\"6977017546168939458\",\"points\":\"12.00\",\"directflag\":\"1\",\"paymoney\":\"12.00\",\"serialid\":\"89314\"},{\"occudate\":\"2018-01-30 00:00:00.0\",\"memberid\":\"6402674453394876588\",\"totalpoints\":\"-\",\"sheettypename\":\"消费增加积分\",\"cardno\":\"6402674453394876588\",\"points\":\"0.00\",\"directflag\":\"1\",\"paymoney\":\"2.30\",\"serialid\":\"89315\"},{\"occudate\":\"2018-01-30 00:00:00.0\",\"memberid\":\"5773952059896530593\",\"totalpoints\":\"-\",\"sheettypename\":\"消费增加积分\",\"cardno\":\"5773952059896530593\",\"points\":\"160.00\",\"directflag\":\"1\",\"paymoney\":\"160.13\",\"serialid\":\"89316\"},{\"occudate\":\"2018-01-30 00:00:00.0\",\"memberid\":\"6648312381180073029\",\"totalpoints\":\"-\",\"sheettypename\":\"消费增加积分\",\"cardno\":\"6648312381180073029\",\"points\":\"3.00\",\"directflag\":\"1\",\"paymoney\":\"3.80\",\"serialid\":\"89317\"},{\"occudate\":\"2018-01-30 00:00:00.0\",\"memberid\":\"5858709406502061728\",\"totalpoints\":\"-\",\"sheettypename\":\"消费增加积分\",\"cardno\":\"5858709406502061728\",\"points\":\"35.00\",\"directflag\":\"1\",\"paymoney\":\"35.64\",\"serialid\":\"89318\"},{\"occudate\":\"2018-01-30 00:00:00.0\",\"memberid\":\"5773952020718061217\",\"totalpoints\":\"-\",\"sheettypename\":\"消费增加积分\",\"cardno\":\"5773952020718061217\",\"points\":\"6.00\",\"directflag\":\"1\",\"paymoney\":\"6.80\",\"serialid\":\"89319\"},{\"occudate\":\"2018-01-30 00:00:00.0\",\"memberid\":\"6635689786920227103\",\"totalpoints\":\"-\",\"sheettypename\":\"消费增加积分\",\"cardno\":\"6635689786920227103\",\"points\":\"64.00\",\"directflag\":\"1\",\"paymoney\":\"64.24\",\"serialid\":\"89320\"},{\"occudate\":\"2018-01-30 00:00:00.0\",\"memberid\":\"6765178002512898930\",\"totalpoints\":\"-\",\"sheettypename\":\"消费增加积分\",\"cardno\":\"6765178002512898930\",\"points\":\"15.00\",\"directflag\":\"1\",\"paymoney\":\"15.70\",\"serialid\":\"89321\"},{\"occudate\":\"2018-01-30 00:00:00.0\",\"memberid\":\"5845060859836008096\",\"totalpoints\":\"-\",\"sheettypename\":\"消费增加积分\",\"cardno\":\"5845060859836008096\",\"points\":\"83.00\",\"directflag\":\"1\",\"paymoney\":\"83.55\",\"serialid\":\"89322\"},{\"occudate\":\"2018-01-30 00:00:00.0\",\"memberid\":\"5785642705775527585\",\"totalpoints\":\"-\",\"sheettypename\":\"消费增加积分\",\"cardno\":\"5785642705775527585\",\"points\":\"6.00\",\"directflag\":\"1\",\"paymoney\":\"6.00\",\"serialid\":\"89323\"},{\"occudate\":\"2018-01-30 00:00:00.0\",\"memberid\":\"6765047277029513610\",\"totalpoints\":\"-\",\"sheettypename\":\"消费增加积分\",\"cardno\":\"6765047277029513610\",\"points\":\"66.00\",\"directflag\":\"1\",\"paymoney\":\"66.48\",\"serialid\":\"89324\"},{\"occudate\":\"2018-01-30 00:00:00.0\",\"memberid\":\"6644660937491056342\",\"totalpoints\":\"-\",\"sheettypename\":\"消费增加积分\",\"cardno\":\"6644660937491056342\",\"points\":\"46.00\",\"directflag\":\"1\",\"paymoney\":\"46.43\",\"serialid\":\"89325\"},{\"occudate\":\"2018-01-30 00:00:00.0\",\"memberid\":\"5766476874054862496\",\"totalpoints\":\"-\",\"sheettypename\":\"消费增加积分\",\"cardno\":\"5766476874054862496\",\"points\":\"87.00\",\"directflag\":\"1\",\"paymoney\":\"87.90\",\"serialid\":\"89326\"},{\"occudate\":\"2018-01-30 00:00:00.0\",\"memberid\":\"5766476608659687072\",\"totalpoints\":\"-\",\"sheettypename\":\"消费增加积分\",\"cardno\":\"5766476608659687072\",\"points\":\"109.00\",\"directflag\":\"1\",\"paymoney\":\"109.30\",\"serialid\":\"89327\"},{\"occudate\":\"2018-01-30 00:00:00.0\",\"memberid\":\"5785643602269210273\",\"totalpoints\":\"-\",\"sheettypename\":\"消费增加积分\",\"cardno\":\"5785643602269210273\",\"points\":\"99.00\",\"directflag\":\"1\",\"paymoney\":\"99.00\",\"serialid\":\"89328\"},{\"occudate\":\"2018-01-30 00:00:00.0\",\"memberid\":\"6976441871586424386\",\"totalpoints\":\"-\",\"sheettypename\":\"消费增加积分\",\"cardno\":\"6976441871586424386\",\"points\":\"9.00\",\"directflag\":\"1\",\"paymoney\":\"9.00\",\"serialid\":\"89329\"},{\"occudate\":\"2018-01-30 00:00:00.0\",\"memberid\":\"6941716513675126887\",\"totalpoints\":\"-\",\"sheettypename\":\"消费增加积分\",\"cardno\":\"6941716513675126887\",\"points\":\"54.00\",\"directflag\":\"1\",\"paymoney\":\"54.40\",\"serialid\":\"89330\"},{\"occudate\":\"2018-01-30 00:00:00.0\",\"memberid\":\"6802809835727463464\",\"totalpoints\":\"-\",\"sheettypename\":\"消费增加积分\",\"cardno\":\"6802809835727463464\",\"points\":\"64.00\",\"directflag\":\"1\",\"paymoney\":\"64.39\",\"serialid\":\"89331\"},{\"occudate\":\"2018-01-30 00:00:00.0\",\"memberid\":\"6985177262946629283\",\"totalpoints\":\"-\",\"sheettypename\":\"消费增加积分\",\"cardno\":\"6985177262946629283\",\"points\":\"1215.00\",\"directflag\":\"1\",\"paymoney\":\"1215.40\",\"serialid\":\"89332\"},{\"occudate\":\"2018-01-30 00:00:00.0\",\"memberid\":\"5785642581175207584\",\"totalpoints\":\"-\",\"sheettypename\":\"消费增加积分\",\"cardno\":\"5785642581175207584\",\"points\":\"2.00\",\"directflag\":\"1\",\"paymoney\":\"2.69\",\"serialid\":\"89333\"},{\"occudate\":\"2018-01-30 00:00:00.0\",\"memberid\":\"5785642924282840736\",\"totalpoints\":\"-\",\"sheettypename\":\"消费增加积分\",\"cardno\":\"5785642924282840736\",\"points\":\"15.00\",\"directflag\":\"1\",\"paymoney\":\"15.80\",\"serialid\":\"89334\"},{\"occudate\":\"2018-01-30 00:00:00.0\",\"memberid\":\"6856591152201705845\",\"totalpoints\":\"-\",\"sheettypename\":\"消费增加积分\",\"cardno\":\"6856591152201705845\",\"points\":\"0.00\",\"directflag\":\"1\",\"paymoney\":\"3.00\",\"serialid\":\"89335\"},{\"occudate\":\"2018-01-30 00:00:00.0\",\"memberid\":\"5845060857636554400\",\"totalpoints\":\"-\",\"sheettypename\":\"消费增加积分\",\"cardno\":\"5845060857636554400\",\"points\":\"26.00\",\"directflag\":\"1\",\"paymoney\":\"26.68\",\"serialid\":\"89336\"},{\"occudate\":\"2018-01-30 00:00:00.0\",\"memberid\":\"6394068716158549988\",\"totalpoints\":\"-\",\"sheettypename\":\"消费增加积分\",\"cardno\":\"6394068716158549988\",\"points\":\"11.00\",\"directflag\":\"1\",\"paymoney\":\"11.00\",\"serialid\":\"89337\"},{\"occudate\":\"2018-01-30 00:00:00.0\",\"memberid\":\"5766476452211492512\",\"totalpoints\":\"-\",\"sheettypename\":\"消费增加积分\",\"cardno\":\"5766476452211492512\",\"points\":\"21.00\",\"directflag\":\"1\",\"paymoney\":\"21.50\",\"serialid\":\"89338\"},{\"occudate\":\"2018-01-30 00:00:00.0\",\"memberid\":\"5881616868976854689\",\"totalpoints\":\"-\",\"sheettypename\":\"消费增加积分\",\"cardno\":\"5881616868976854689\",\"points\":\"252.00\",\"directflag\":\"1\",\"paymoney\":\"252.00\",\"serialid\":\"89339\"},{\"occudate\":\"2018-01-30 00:00:00.0\",\"memberid\":\"5773952155074660000\",\"totalpoints\":\"-\",\"sheettypename\":\"消费增加积分\",\"cardno\":\"5773952155074660000\",\"points\":\"123.00\",\"directflag\":\"1\",\"paymoney\":\"160.00\",\"serialid\":\"89340\"},{\"occudate\":\"2018-01-30 00:00:00.0\",\"memberid\":\"6614121575972184463\",\"totalpoints\":\"-\",\"sheettypename\":\"消费增加积分\",\"cardno\":\"6614121575972184463\",\"points\":\"11.00\",\"directflag\":\"1\",\"paymoney\":\"11.07\",\"serialid\":\"89341\"}]}";
        JSONObject  dataJson=JSONObject.fromObject(xml);
        List<Point> pointsList=new ArrayList<Point>();
        JSONArray data=dataJson.getJSONArray("cardlist");
        for(int j=0;j<data.size();j++)
        {
            JSONObject info=data.getJSONObject(j);
            Point p=(Point)JSONObject.toBean(info,Point.class);
            if(p!=null&&p.getCardno()!=null)
            {//没有问题的数据分装（有问的数据自动过滤）
                pointsList.add(p);
            }
            //cList.add(p);
        }

        System.out.println(pointsList.size());
    }


@Test
    public void TestXmlAnnotation(){
    String[] test = new String[]{"0"};
    System.out.println(test[0]);
    }

    @Test
    public void testXml(){
        String dataXml = "<template>\n" +
                "    <template_id>qEF5Co4QqyAUGXQqAVUa3plshrMqA6Gd8b9QR985oQc</template_id>\n" +
                "    <touser>oj7MDv2KSGkzlU95Nd91o-l3jEaI</touser>\n" +
                "    <url>http://www.baidu.com</url>\n" +
                "    <data>\n" +
                "        <first>\n" +
                "            <value>尊敬的会员，您好：</value>\n" +
                "            <color/>\n" +
                "        </first>\n" +
                "        <keyword1>\n" +
                "            <value>消费增加积分</value>\n" +
                "            <color/>\n" +
                "        </keyword1>\n" +
                "        <keyword2>\n" +
                "            <value>58.00</value>\n" +
                "            <color/>\n" +
                "        </keyword2>\n" +
                "        <keyword3>\n" +
                "            <value>58.00</value>\n" +
                "            <color/>\n" +
                "        </keyword3>\n" +
                "        <keyword4>\n" +
                "            <value>0</value>\n" +
                "            <color/>\n" +
                "        </keyword4>\n" +
                "        <keyword5>\n" +
                "            <value>0.00</value>\n" +
                "            <color/>\n" +
                "        </keyword5>\n" +
                "        <remark>\n" +
                "            <value>顶顶顶顶</value>\n" +
                "            <color/>\n" +
                "        </remark>\n" +
                "    </data>\n" +
                "</template>";
        String xml="";
        XmlFormat mesXml=new XmlFormat("params");
        mesXml.addInnerNode("imn");
        mesXml.setText("getHostImNumber");
        mesXml.addSiblingNode("bId");
        mesXml.setText("getServiceId");
        mesXml.addSiblingNode("msgId");
        mesXml.setText("getMsgId");
        mesXml.addSiblingNode("uno");
        mesXml.setText("OPENID");
        //mesXml.setText("o1yl_jk0tpZcgxDAtRpMSJHhk2KA");//测试数据
        mesXml.addSiblingNode("layout");
        mesXml.setText("123");

        xml=mesXml.getXmlString().substring(mesXml.getXmlString().indexOf("?>")+2, mesXml.getXmlString().length());
        String sb=new String(xml);
        xml=sb.substring(0, sb.indexOf("</layout>")-3)+dataXml+sb.substring(sb.indexOf("</layout>"), sb.length());
        System.out.println(xml);
    }


    @Test
    public void testXML2(){
        XmlFormat format = new XmlFormat("request");
        format.addAttribute("keep-time", "30");
        format.addInnerNode("message");
        format.addAttribute("hostel", "hostTel");
        format.addAttribute("hostimnumber", "hostImNumber");
        format.addAttribute("sendtype", "sendType");
        format.addAttribute("session-id", "sessionId");
        format.addAttribute("imtype", "imType");
        format.addAttribute("imnumber", "imNumber");
        format.addAttribute("kfid", "kfId");
        format.addAttribute("msg_type", "msgType");
        format.addAttribute("bitmap_flag", "bitmapFlag");

        format.setText("hhhhhhhhhh");

        String input = format.getXmlString();

        System.out.println(input);
    }

    @Test
    public void testXml3(){
        String xmlValue="<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<template>\n" +
                "    <template_id>qEF5Co4QqyAUGXQqAVUa3plshrMqA6Gd8b9QR985oQc</template_id>\n" +
                "    <touser>openId</touser>\n" +
                "    <url>http://114.215.133.74/test/impl/actThird!toActOauth.action?actUrl=hrwjcard&amp;appidType=1&amp;thirdParams=t</url>\n" +
                "    <data>\n" +
                "        <first>\n" +
                "            <value>尊敬的会员，您好：</value>\n" +
                "            <color />\n" +
                "        </first>\n" +
                "        <keyword1>\n" +
                "            <value>?????</value>\n" +
                "            <color />\n" +
                "        </keyword1>\n" +
                "        <keyword2>\n" +
                "            <value>?????</value>\n" +
                "            <color />\n" +
                "        </keyword2>\n" +
                "        <keyword3>\n" +
                "            <value>?????</value>\n" +
                "            <color />\n" +
                "        </keyword3>\n" +
                "        <keyword4>\n" +
                "            <value>?????</value>\n" +
                "            <color />\n" +
                "        </keyword4>\n" +
                "        <keyword5>\n" +
                "            <value>?????</value>\n" +
                "            <color />\n" +
                "        </keyword5>\n" +
                "        <remark>\n" +
                "            <value>感谢您的惠顾！</value>\n" +
                "            <color>#FF0000</color>\n" +
                "        </remark>\n" +
                "    </data>\n" +
                "</template>";

        String openId="oj7MDv2KSGkzlU95Nd91o-l3jEaI";

        Point p = new Point();
        p.setCardno("86500000088886");
        p.setDirectflag("1");
        p.setMemberid("6323241953128420386");
        p.setOccudate("2017-12-01 18:49:53.0");
        p.setPaymoney("58.00");
        p.setPoints("58.00");
        p.setSerialid("1259");
        p.setSheettypename("消费增加积分");
        p.setTotalpoints("0.00");

        String xml="";
        xmlValue =xmlValue.substring(xmlValue.indexOf("?>")+2,  xmlValue.length());
        System.out.println("----->xmlValue = " +xmlValue) ;
        try {
            Document doc = DocumentHelper.parseText(xmlValue);
            Element rootElt = doc.getRootElement(); // 获取根节点



            //用户openid替换
            Element dataElement0 = rootElt.element("touser");//获得根结点下面的子节点
            dataElement0.setText(openId);
            //dataElement0.setText("o1yl_jk0tpZcgxDAtRpMSJHhk2KA");//测试数据

            //头信息中文乱码修改
            Element dataElement = rootElt.element("data");//获得根结点下面的子节点
            Element firstElement = dataElement.element("first");//获得根结点下面的子节点的子节点
            Element valueElement0 = firstElement.element("value");//获得根结点下面的子节点的子节点的子节点

            valueElement0.setText(valueElement0.getText());


            //交易类型
            Element kw1Element = dataElement.element("keyword1");//获得根结点下面的子节点的子节点
            Element valueElement1 = kw1Element.element("value");//获得根结点下面的子节点的子节点的子节点
            valueElement1.setText(p.getSheettypename());

            //交易金额
            Element kw2Element = dataElement.element("keyword2");//获得根结点下面的子节点的子节点
            Element valueElement2 = kw2Element.element("value");//获得根结点下面的子节点的子节点的子节点
            valueElement2.setText(p.getPaymoney());
            //valueElement2.setText("--");//由于ngc系统没有获取到用户的积分金额，现在暂时用“--”代替
            if(p.getDirectflag().equals("1"))
            {
                //积分获得
                Element kw3Element = dataElement.element("keyword3");//获得根结点下面的子节点的子节点
                Element valueElement3 = kw3Element.element("value");//获得根结点下面的子节点的子节点的子节点
                valueElement3.setText(p.getPoints());
                //积分消减
                Element kw4Element = dataElement.element("keyword4");//获得根结点下面的子节点的子节点
                Element valueElement4 = kw4Element.element("value");//获得根结点下面的子节点的子节点的子节点
                valueElement4.setText("0");

            }else if(p.getDirectflag().equals("-1"))
            {
                //积分获得
                Element kw3Element = dataElement.element("keyword3");//获得根结点下面的子节点的子节点
                Element valueElement3 = kw3Element.element("value");//获得根结点下面的子节点的子节点的子节点
                valueElement3.setText("0");
                //积分消减
                Element kw4Element = dataElement.element("keyword4");//获得根结点下面的子节点的子节点
                Element valueElement4 = kw4Element.element("value");//获得根结点下面的子节点的子节点的子节点
                valueElement4.setText(p.getPoints());

            }else
            {
                //积分获得
                Element kw3Element = dataElement.element("keyword3");//获得根结点下面的子节点的子节点
                Element valueElement3 = kw3Element.element("value");//获得根结点下面的子节点的子节点的子节点
                valueElement3.setText("数据获取异常");
                //积分消减
                Element kw4Element = dataElement.element("keyword4");//获得根结点下面的子节点的子节点
                Element valueElement4 = kw4Element.element("value");//获得根结点下面的子节点的子节点的子节点
                valueElement4.setText("数据获取异常");
            }


            //积分余额
            Element kw5Element = dataElement.element("keyword5");//获得根结点下面的子节点的子节点
            Element valueElement5 = kw5Element.element("value");//获得根结点下面的子节点的子节点的子节点
            valueElement5.setText(p.getTotalpoints());
            xml=doc.asXML();
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println(xml.substring(xml.indexOf("?>")+2,  xml.length()));



    }




    @Test
    public void testAdress(){
        //生产地址
         String mcIp = "10.165.51.36";
        String mcPort = "7003";
        String ifName = "send_msg";
        String params = "";
        String token="CF3D1ECA2CC2B61210740B3F2289B64F";
        String addr = "http://" + mcIp + ":" + mcPort
                + "/mc/" + ifName + "?" + params
                + "&access-token=" + token;

        System.out.println(addr);
    }


    @Test
    public void createXmlToNav() {
//		Document docxml = DocumentHelper.createDocument();
//		docxml.setXMLEncoding("utf-8");
//		Element response = docxml.addElement("response");
//		Element result = response.addElement("result");
//		result.addElement("code").addText(code);
//		result.addElement("reason").addText(reason);
//		Element pros = response.addElement("properties");
//		Element se = pros.addElement("session");
//		Element pro = se.addElement("property");
//		pro.addAttribute("key", "codeResult");
//		pro.addAttribute("value", "000");
//		return docxml.asXML();
        String code = "0";
        String reason = "成功";
        Map<String,String> map = new HashMap<>();
        map.put("result", "今天您已经签到，请明天再来！");
        XmlForNavReturn service = new XmlForNavReturn();
        service.addInnerNode("result");
        service.addLeafNode("code", code);
        service.addLeafNode("reason", reason);
        service.returnToParent();
        service.addInnerNode("properties");
        service.addInnerNode("session");
        if (map != null && !map.isEmpty()) {
            Set<String> set = map.keySet();
            for (String key : set) {
                service.addInnerNode("property");
                service.addAttribute("key", key.toString());
                service.addAttribute("value", map.get(key).toString());
                service.returnToParent();
            }
        }
        System.out.println(service.getXmlString());
    }

//    @Test
//    public void testTemplate() throws IOException {
//        HttpUtils hUtils =new HttpUtils();
//        String url="http://crvweixin.crv.com.cn/Saasif/weixin/templateMsg.do?hosttel=12109";
//        String xml = "<data><params><imn>gh_de42836a07d5</imn><bId>2H020000000008</bId><msgId>161</msgId><uno>o1yl_jqVhV1i_GTY0dormzWBVRQY</uno><layout><?xmlversion=\"1.0\"encoding=\"GBK\"?><template><template_id>qEF5Co4QqyAUGXQqAVUa3plshrMqA6Gd8b9QR985oQc</template_id><touser>o1yl_jqVhV1i_GTY0dormzWBVRQY</touser><url>http://www.baidu.com</url><topcolor>#660099</topcolor><data><first><value>？？？？？？？？</value><color/></first><keyword1><value>？？？？</value><color/></keyword1><keyword2><value>？？？？</value><color/></keyword2><keyword3><value>？？？？</value><color/></keyword3><keyword4><value>？？？？</value><color/></keyword4><keyword5><value>？？？？</value><color/></keyword5><remark><value>？？？？</value><color/></remark></data></template></layout></params></data>";
//        String xmlString= hUtils.postMethod(xml, url, "UTF-8");
//        System.out.println(xmlString);
//    }

    @Test
    public void trimXml(){
        String desc = "Err=801002,Msg=ErrorID:106005,Message:code=E1B00105,desc=merchantCode:1651200000001;storeCode:205584";
        String msg = desc.substring(
                desc.lastIndexOf("=") + 1, desc.length());
        System.out.println(msg);
    }


    @Test
    public void getUrl() throws UnsupportedEncodingException {
        EncodeUrl.getUrl("http://crvweixin.crv.com.cn/CrvInterface/crv/code!setCode1.action","menDian");
    }

    @Test
    public void getJosn(){
        Point point = new Point();
        point.setCardno("86500000088886");
        point.setDirectflag("1");
        point.setMemberid("6323241953128420386");
        point.setOccudate("2017-12-01 18:49:53.0");
        point.setPaymoney("58.00");
        point.setPoints("58.00");
        point.setSerialid("1259");
        point.setSheettypename("消费增加积分");
        point.setTotalpoints("0.00");

        JSONObject jsonObject = JSONObject.fromObject(point);

        System.out.println(jsonObject.toString());

    }

    @Test
    public void jiexiJson() {
        String jsonString = "{\"cardlist\":[{\"cardno\":\"86500000088886\",\"directflag\":\"1\",\"memberid\":\"6323241953128420386\",\"occudate\":\"2017-12-01 18:49:53.0\",\"paymoney\":\"58.00\",\"points\":\"58.00\",\"serialid\":\"1259\",\"sheettypename\":\"消费增加积分\",\"totalpoints\":\"0.00\"}]}";

        jsonString = jsonString.replaceAll( "\"" ,"\'");
        System.out.println(jsonString);

    }

    @Test
    public void getIp() throws IOException, ParseException {
        System.out.println("{\"memberid\":\"CDE5FB5137F3B92E1E4D3A6623CEAEE9F36FE1B17AC9065842F0955015031D7B\",\"openid\":\"EBBDC6B922EC724B0847FA66C2FCC4C3636A14743E06F1F2496A85944D6401EC\",\"sysId\":\"crv\",\"transactionUuid\":\"e71993e6-1539-4a5a-864c-9b271b0aa3fa\"}");
    }


    public int getAfterDays(long now, long last)
    {
        Date nowDate = new Date(now);
        Date lastDate = new Date(last);

        Calendar calendar = Calendar.getInstance();

        calendar.setTime(nowDate);

        int nowDay = calendar.get(6);

        calendar.setTime(lastDate);

        int lastDay = calendar.get(6);

        return nowDay - lastDay;
    }


}


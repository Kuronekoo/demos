package weather.client;

import weather.api.ArrayOfString;
import weather.api.WeatherWS;
import weather.api.WeatherWSSoap;

import java.util.List;

public class WeatherClient {
    public static void main(String[] args) {
//      创建一个工厂对象
        WeatherWS weatherWS = new WeatherWS();
//      获取一块肥皂
        WeatherWSSoap weatherWSSoap = weatherWS.getWeatherWSSoap();
//      获取天气信息
        ArrayOfString weather = weatherWSSoap.getWeather("深圳", null);
//      获取天气信息List
        List<String> weatherList = weather.getString();
        for (int i = 0;i<weatherList.size();i++){
            System.out.println( i + ". " + weatherList.get(i) );
        }

    }
}

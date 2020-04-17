package ru.dlts.primefaces;

import org.json.simple.parser.ParseException;
import ru.dlts.primefaces.entity.Info;
import ru.dlts.primefaces.entity.MainRussianInfo;
import ru.dlts.primefaces.entity.RussianStat;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

@Named
@ApplicationScoped
public class InfoService {
    public Info searchInfo(){
        Info info = new Info();
        info.setRussianStat(getRussianStat());
        info.setMainRussianInfo(getMainRassianInfo());
        return info;
    }


    private List<RussianStat> getRussianStat(){
        JSONParser parser = new JSONParser();
        List<RussianStat> russianStatsList = new ArrayList<>();

        try {
            URL url = new URL("https://virusinfo.herokuapp.com/info"); // URL to Parse
            URLConnection yc = url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));

            String inputLine = null;
            while ((inputLine = in.readLine()) != null) {
                JSONArray a = (JSONArray) parser.parse(inputLine);
                // Loop through each item
                for (Object o : a) {
                    RussianStat russianStat = new RussianStat();
                    JSONObject jsonObject = (JSONObject) o;

                    russianStat.setLocation((String)jsonObject.get("location"));
                    russianStat.setHealed((String)jsonObject.get("healed"));
                    russianStat.setStick((String)jsonObject.get("stick"));
                    russianStat.setDie(((String)jsonObject.get("die")));

                    russianStatsList.add(russianStat);
                }
            }
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }
        return russianStatsList;
    }

    private MainRussianInfo getMainRassianInfo(){
        JSONParser parser = new JSONParser();
        MainRussianInfo mainRussianInfo = new MainRussianInfo();

        try {
            URL url = new URL("https://virusinfo.herokuapp.com"); // URL to Parse
            URLConnection yc = url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));

            String inputLine = in.readLine();
            Object obj = parser.parse(inputLine);
            JSONObject jsonObject = (JSONObject) obj;

            mainRussianInfo.setActive((Long)jsonObject.get("active"));
            mainRussianInfo.setDate((String)jsonObject.get("date"));
            mainRussianInfo.setDie((Long)jsonObject.get("die"));
            mainRussianInfo.setDiePlus((Long)jsonObject.get("diePlus"));
            mainRussianInfo.setHealed((Long)jsonObject.get("healed"));
            mainRussianInfo.setHealedPlus((Long)jsonObject.get("healedPlus"));
            mainRussianInfo.setInfected((Long)jsonObject.get("infected"));
            mainRussianInfo.setInfectedPlus((Long)jsonObject.get("infectedPlus"));
            mainRussianInfo.setTestsCount((String)jsonObject.get("testsCount"));
            mainRussianInfo.setTestsPlus((String)jsonObject.get("testsPlus"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return mainRussianInfo;
    }
}
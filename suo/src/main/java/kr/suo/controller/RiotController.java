package kr.suo.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import kr.suo.config.VersionCheck;
import kr.suo.riot.array.MiniComparator;
import kr.suo.riot.vo.LeagueItemDTO;
import kr.suo.riot.vo.LeagueListDTO;
import kr.suo.vo.LeagueEntrydto;
import kr.suo.vo.Summoner;



@Controller
public class RiotController {

	final static String API_KEY = "RGAPI-e6d41b01-fac1-4f11-8bb1-bf2c1b7e28c4";
	
	
	
	
	@RequestMapping("/main/ranking")
	public String rankChallenger(Model model, HttpServletRequest request) {
		BufferedReader br = null;
		LeagueListDTO leagueListDTO = null;
		Gson gson = new Gson();
		List<LeagueItemDTO> list = new ArrayList<>();
		try {
			String urlstr = "https://kr.api.riotgames.com/lol/league/v4/challengerleagues/by-queue/RANKED_SOLO_5x5?api_key="+API_KEY;
			URL url = new URL(urlstr);
			HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
			urlConnection.setRequestMethod("GET");
			br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(),"UTF-8"));
			String result="";
			String line;
			while((line = br.readLine()) !=null) {
				result = result + line;
			}
			JsonParser jsonParser = new JsonParser();
			JsonObject k = (JsonObject) jsonParser.parse(result);
			leagueListDTO = gson.fromJson(result, LeagueListDTO.class);
			System.out.println("");
			System.out.println("==자바객체로 복원==");
			System.out.println(leagueListDTO.getName());
			System.out.println(leagueListDTO.getTier());
			System.out.println(leagueListDTO.getLeagueId());
			System.out.println(leagueListDTO.getQueue());
			for(LeagueItemDTO dto : leagueListDTO.getEntries()) {
				list.add(dto);
			}
			MiniComparator comp = new MiniComparator();
			Collections.sort(list,comp);
			model.addAttribute("rank", list);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return "/main/ranking";
		
		
	}
	
	 @RequestMapping(value="/Main", method=RequestMethod.GET)
	    public String searchSummoner(Model model, HttpServletRequest httpServletRequest, HttpServletResponse response) throws IOException {

	        response.setContentType("text/html;charset=utf-8");
	        PrintWriter out = response.getWriter();
	        VersionCheck.checkVersion();
	        BufferedReader br = null;
	        String SummonerName = httpServletRequest.getParameter("title");
	        Summoner temp= null;
	        LeagueEntrydto[] leagueInfo = null;
	        try{
	            String urlstr = "https://kr.api.riotgames.com/lol/summoner/v4/summoners/by-name/"+
	                    SummonerName.replace(" ", "")		+"?api_key=" + API_KEY;
	            URL url = new URL(urlstr);
	            HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection();
	            urlconnection.setRequestMethod("GET");
	            br = new BufferedReader(new InputStreamReader(urlconnection.getInputStream(),"UTF-8"));
	            String result = "";
	            String line;
	            while((line = br.readLine()) != null) { 
	                result = result + line;
	            }
	            JsonParser jsonParser = new JsonParser();
	            JsonObject k = (JsonObject) jsonParser.parse(result);

	            int profileIconId = k.get("profileIconId").getAsInt();
	            String name = k.get("name").getAsString();
	            String puuid = k.get("puuid").getAsString();
	            long summonerLevel = k.get("summonerLevel").getAsLong();
	            long revisionDate = k.get("revisionDate").getAsLong();
	            String id = k.get("id").getAsString();
	            String accountId = k.get("accountId").getAsString();
	            temp = new Summoner(profileIconId,name,puuid,summonerLevel,revisionDate,id,accountId);

	        }catch(Exception e){
	            System.out.println(e.getMessage());
	        }

	        String[] leagueName = null;

	        try{
	            String urlstr = "https://kr.api.riotgames.com/lol/league/v4/entries/by-summoner/"+
	                    temp.getId()		+"?api_key="+API_KEY;
	            URL url = new URL(urlstr);
	            HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection();
	            urlconnection.setRequestMethod("GET");
	            br = new BufferedReader(new InputStreamReader(urlconnection.getInputStream(),"UTF-8")); 
	            String result = "";
	            String line;
	            while((line = br.readLine()) != null) { 
	                result = result + line;
	            }
	            JsonParser jsonParser = new JsonParser();
	            JsonArray arr = (JsonArray) jsonParser.parse(result);
	            leagueInfo = new LeagueEntrydto[arr.size()];
	            leagueName = new String[arr.size()];
	            for(int i=0; i<arr.size(); i++) {
	                JsonObject k =  (JsonObject) arr.get(i);
	                int wins = k.get("wins").getAsInt();
	                int losses = k.get("losses").getAsInt();
	                String rank = k.get("rank").getAsString();
	                String tier = k.get("tier").getAsString();
	                String queueType = k.get("queueType").getAsString();
	                int leaguePoints = k.get("leaguePoints").getAsInt();
	                String leagueId = k.get("leagueId").getAsString();
	                leagueInfo[i] = new LeagueEntrydto(queueType, wins, losses, leagueId, rank,tier, leaguePoints);
	                urlstr = "https://kr.api.riotgames.com/lol/league/v4/leagues/"+
	                        leagueInfo[i].getLeagueId()		+"?api_key="+API_KEY;

	                url = new URL(urlstr);
	                urlconnection = (HttpURLConnection) url.openConnection();
	                urlconnection.setRequestMethod("GET");
	                br = new BufferedReader(new InputStreamReader(urlconnection.getInputStream(),"UTF-8")); 
	                result = "";
	                line ="";
	                while((line = br.readLine()) != null) {
	                    result = result + line;
	                }
	                jsonParser = new JsonParser();
	                k = (JsonObject) jsonParser.parse(result);
	                leagueName[i] = k.get("name").getAsString();
	            }

	        }catch(Exception e){
	            System.out.println(e.getMessage());
	        }



	            model.addAttribute("summoner", temp);
	            model.addAttribute("profileImgURL", "http://ddragon.leagueoflegends.com/cdn/" + VersionCheck.profileiconVersion + "/img/profileicon/" + temp.getProfileIconId() + ".png");


	        if(leagueInfo.length == 0 ) {
	            model.addAttribute("leagueInfo", null);
	            model.addAttribute("tierImgURL", "/images/emblems/Emblem_UnRank.png");
	        }else{
	            model.addAttribute("leagueInfo", leagueInfo);
	            model.addAttribute("tierImgURL", "/images/emblems/Emblem_" + leagueInfo[0].getTier() + ".png");
	            System.out.print(leagueInfo[0]);
	        }
	            model.addAttribute("leagueName", leagueName);
	            return "main/main";

	    }

	
}

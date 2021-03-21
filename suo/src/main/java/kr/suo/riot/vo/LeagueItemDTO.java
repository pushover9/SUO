package kr.suo.riot.vo;

import lombok.Data;

@Data
public class LeagueItemDTO {
	
	private boolean freshBlood;
	private boolean inactive;
	private boolean veteran;
	private boolean hotStreak;
	private String summonerName;
	private String rank;
	private String summonerId;
	private int wins;
	private int leaguePoints;
	private int losses;
	
	private MiniSeriesDTO miniSeries;
}

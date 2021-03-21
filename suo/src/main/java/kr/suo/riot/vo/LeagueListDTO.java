package kr.suo.riot.vo;

import java.util.List;

import lombok.Data;

@Data
public class LeagueListDTO {
	
	private String leagueId;
	private String tier;
	private String name;
	private String queue;
	
	private List<LeagueItemDTO> entries;
}

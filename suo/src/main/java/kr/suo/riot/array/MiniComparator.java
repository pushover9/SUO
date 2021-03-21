package kr.suo.riot.array;

import java.util.Comparator;

import kr.suo.riot.vo.LeagueItemDTO;

public class MiniComparator implements Comparator<LeagueItemDTO> {

	@Override
	public int compare(LeagueItemDTO o1, LeagueItemDTO o2) {
		int firstValue = o1.getLeaguePoints();
		int secondValue = o2.getLeaguePoints();
		//order by descending
		if(firstValue > secondValue) {
			return -1;
		}else if(firstValue<secondValue) {
			return 1;
		}else {
			return 0;
		}
	}

}

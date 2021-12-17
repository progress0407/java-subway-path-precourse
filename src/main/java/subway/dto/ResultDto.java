package subway.dto;

import java.util.List;

public class ResultDto {
	private int shortestDistance;
	private int shortestTime;
	private List<String> stationNames;

	public ResultDto(int shortestDistance, int shortestTime, List<String> stationNames) {
		this.shortestDistance = shortestDistance;
		this.shortestTime = shortestTime;
		this.stationNames = stationNames;
	}

	public int getShortestDistance() {
		return shortestDistance;
	}

	public int getShortestTime() {
		return shortestTime;
	}

	public List<String> getStationNames() {
		return stationNames;
	}

	@Override
	public String toString() {
		return "ResultDto{" +
			"shortestDistance=" + shortestDistance +
			", shortestTime=" + shortestTime +
			'}';
	}
}

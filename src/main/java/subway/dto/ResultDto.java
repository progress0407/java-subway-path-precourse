package subway.dto;

public class ResultDto {
	private int shortestDistance;
	private int shortestTime;

	public ResultDto(int shortestDistance, int shortestTime) {
		this.shortestDistance = shortestDistance;
		this.shortestTime = shortestTime;
	}

	public int getShortestDistance() {
		return shortestDistance;
	}

	public void setShortestDistance(int shortestDistance) {
		this.shortestDistance = shortestDistance;
	}

	public int getShortestTime() {
		return shortestTime;
	}

	public void setShortestTime(int shortestTime) {
		this.shortestTime = shortestTime;
	}
}

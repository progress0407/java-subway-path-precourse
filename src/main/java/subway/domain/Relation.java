package subway.domain;

import java.util.Objects;

public class Relation {
	private Line line;
	private Station stationOne;
	private Station stationAnother;
	private int distance;
	private int time;

	public Relation(Line line, Station stationOne, Station stationAnother, int distance, int time) {
		this.line = line;
		this.stationOne = stationOne;
		this.stationAnother = stationAnother;
		this.distance = distance;
		this.time = time;
	}

	public Station getStationOne() {
		return stationOne;
	}

	public Station getStationAnother() {
		return stationAnother;
	}

	public Line getLine() {
		return line;
	}

	public int getDistance() {
		return distance;
	}

	public int getTime() {
		return time;
	}

	public boolean equals(String nameOne, String nameAnother) {
		String stationNameOne = stationOne.getName();
		String stationNameAnother = stationAnother.getName();
		return (stationNameOne.equals(nameOne) && stationNameAnother.equals(nameAnother))
			|| (stationNameAnother.equals(nameOne) && stationNameOne.equals(nameAnother));
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Relation))
			return false;
		Relation relation = (Relation)o;
		return getDistance() == relation.getDistance() && getTime() == relation.getTime() && Objects.equals(
			getLine(), relation.getLine()) && Objects.equals(getStationOne(), relation.getStationOne())
			&& Objects.equals(getStationAnother(), relation.getStationAnother());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getLine(), getStationOne(), getStationAnother(), getDistance(), getTime());
	}

	@Override
	public String toString() {
		return "Relation{" +
			"line=" + line +
			", stationOne=" + stationOne +
			", stationAnother=" + stationAnother +
			", distance=" + distance +
			", time=" + time +
			'}';
	}
}

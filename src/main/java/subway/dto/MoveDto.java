package subway.dto;

public class MoveDto {
	private String mode;
	private String sourceStationName;
	private String targetStationName;

	public MoveDto(String mode, String sourceStationName, String targetStationName) {
		this.mode = mode;
		this.sourceStationName = sourceStationName;
		this.targetStationName = targetStationName;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getSourceStationName() {
		return sourceStationName;
	}

	public void setSourceStationName(String sourceStationName) {
		this.sourceStationName = sourceStationName;
	}

	public String getTargetStationName() {
		return targetStationName;
	}

	public void setTargetStationName(String targetStationName) {
		this.targetStationName = targetStationName;
	}

	@Override
	public String toString() {
		return "MoveDto{" +
			"mode='" + mode + '\'' +
			", sourceStationName='" + sourceStationName + '\'' +
			", targetStationName='" + targetStationName + '\'' +
			'}';
	}
}

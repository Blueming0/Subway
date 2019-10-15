import java.util.ArrayList;
import java.util.List;

public class Path {
	private Station startStation;
    private Station endStation;
    private List<Station> allPassStations=new ArrayList<>();//所有已经过的站点
    private int distance=0;
	  
    public Station getStartStation() {
		return startStation;
	}

	public void setStartStation(Station startStation) {
		this.startStation = startStation;
	}

	public Station getEndStation() {
		return endStation;
	}

	public void setEndStation(Station endStation) {
		this.endStation = endStation;
	}

	public List<Station> getAllPassStations() {
		return allPassStations;
	}

	public void setAllPassStations(List<Station> allPassStations) {
		this.allPassStations = allPassStations;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public Path(Station startStation,Station endStation) {
    	this.startStation=startStation;
    	this.endStation=endStation;
    }

    public Path() {
    	// TODO Auto-generated constructor stub
    }
}
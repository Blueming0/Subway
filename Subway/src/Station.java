import java.util.*;

public class Station {
    private String stationName;
    private String lineName;
    private List<Station> allLinkStations=new ArrayList<>();//所有相邻站点
    public static LinkedHashSet<List<Station>> allLines=new LinkedHashSet<>();//所有地铁线路
    
    public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public String getLineName() {
		return lineName;
	}

	public void setLineName(String lineName) {
		this.lineName = lineName;
	}

	public List<Station> getAllLinkStations() {
		return allLinkStations;
	}

	public void setAllLinkStations(List<Station> allLinkStations) {
		this.allLinkStations = allLinkStations;
	}

	public Station(String stationName,String lineName) {
    	this.stationName=stationName;
    	this.lineName=lineName;
    }
	
	public boolean equals(Object object) {
    	if(this==object)
    		return true;
    	else if(object instanceof Station) {
    		Station station = (Station) object;
            if(station.getStationName().equals(this.getStationName())){
                return true;
            }
            else {
                return false;
            }
        }
    	else {
            return false;
        }
    }
	
	public static Station getStationByName(String name,LinkedHashSet<List<Station>> allLines) {
    	Station result=null;
    	for(List<Station> line:allLines) {
    		for(Station station:line) {
    			if(station.getStationName().equals(name)) {
    				return station;
    			}
    		}
    	}
    	return result;
    }

//	获取所有相邻
	public static List<Station> getAllLinkStations(Station station) {
		List<Station> allLinkedStaions = new ArrayList<Station>();
		for (List<Station> line:allLines) {
			for (int i=0;i<line.size(); i++) {
				if (station.equals(line.get(i))) {
//    				起始站点
					if (i==0) {
						allLinkedStaions.add(line.get(i+1));
    				} 
//    				中间的站点
					else if (i<(line.size()-1)) {
						allLinkedStaions.add(line.get(i+1));
						allLinkedStaions.add(line.get(i-1));
    				} 
//    				终点
					else {
						allLinkedStaions.add(line.get(i-1));
    				}
    			}
    		}
    	}
		return allLinkedStaions;
    }
}
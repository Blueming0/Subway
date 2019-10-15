import java.util.*;

public class Dijkstra {
	private static List<Station> outList = new ArrayList<>();//记录已经分析过的站点
	private static HashMap<Station, Path> path = new HashMap<>();
      
//	  计算最短路径（相邻站点距离默认都为1）
     public static Path getShortestPath(Station startStation, Station endStation) { 
    	 //添加起始站点
    	 if (!outList.contains(startStation)) {
    		 outList.add(startStation);
         }
    	  
        //第一次用起始站点的相邻站点初始化
         if (path.isEmpty()) {
        	 List<Station> allLinkStations = Station.getAllLinkStations(startStation);
        	 for (Station station : allLinkStations) {
        		 Path p = new Path();
        		 p.setStartStation(startStation);
        		 p.setEndStation(station);
        		 int distance = 1;//起始站点距离初始为1
                  
        		 p.setDistance(distance);
        		 p.getAllPassStations().add(station);//起始站点已经分析过
        		 path.put(station, p);
            }
        }
          
//      获取下一站点
        Station nextStation = getNextStation();
        if (nextStation.equals(endStation)) {
        	return path.get(nextStation);
        }
          
        List<Station> nextLinkStations = Station.getAllLinkStations(nextStation);
        for (Station station:nextLinkStations) {	  
        	// 已经分析过的station，不再做任何经分析
        	if (outList.contains(station)) {
        		continue;
            }
            //每增加一个站点，距离+1
        	int distance = path.get(nextStation).getDistance()+1;

        	List<Station> allPassStations = path.get(nextStation).getAllPassStations();
        	Path p = path.get(station);
        	if (p != null) {
        		//已经计算过到此station的距离比较出最小的距离
        		if (p.getDistance()>distance) {
        			p.setDistance(distance);
        			//重置start到各站的最短路径
        			p.getAllPassStations().clear();
        			p.getAllPassStations().addAll(allPassStations);
        			p.getAllPassStations().add(station);
                }
            } 
            
        	else {//还没有计算过到此station的距离
        		p = new Path();
        		p.setDistance(distance);
        		p.setStartStation(startStation);
        		p.setEndStation(station);

        		p.getAllPassStations().addAll(allPassStations);
        		p.getAllPassStations().add(station);
            }
        	path.put(station, p);
        }
        outList.add(nextStation);
        getShortestPath(startStation, endStation);//重复计算，往外面站点扩展
        
        return path.get(endStation);
     }
    
// 	获取下一站点
     private static Station getNextStation() {
     	int distance = Integer.MAX_VALUE;
     	Station result = null;
     	Set<Station> stations = path.keySet();
     	for (Station s:stations) {
     		if (outList.contains(s)) {
     			continue;
             }
     		Path p = path.get(s);
     		if (p.getDistance()<distance) {
     			distance = p.getDistance();
     			result = p.getEndStation();
             }
         }
     	return result;
     }
}

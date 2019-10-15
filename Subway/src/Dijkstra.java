import java.util.*;

public class Dijkstra {
	private static List<Station> outList = new ArrayList<>();//��¼�Ѿ���������վ��
	private static HashMap<Station, Path> path = new HashMap<>();
      
//	  �������·��������վ�����Ĭ�϶�Ϊ1��
     public static Path getShortestPath(Station startStation, Station endStation) { 
    	 //�����ʼվ��
    	 if (!outList.contains(startStation)) {
    		 outList.add(startStation);
         }
    	  
        //��һ������ʼվ�������վ���ʼ��
         if (path.isEmpty()) {
        	 List<Station> allLinkStations = Station.getAllLinkStations(startStation);
        	 for (Station station : allLinkStations) {
        		 Path p = new Path();
        		 p.setStartStation(startStation);
        		 p.setEndStation(station);
        		 int distance = 1;//��ʼվ������ʼΪ1
                  
        		 p.setDistance(distance);
        		 p.getAllPassStations().add(station);//��ʼվ���Ѿ�������
        		 path.put(station, p);
            }
        }
          
//      ��ȡ��һվ��
        Station nextStation = getNextStation();
        if (nextStation.equals(endStation)) {
        	return path.get(nextStation);
        }
          
        List<Station> nextLinkStations = Station.getAllLinkStations(nextStation);
        for (Station station:nextLinkStations) {	  
        	// �Ѿ���������station���������κξ�����
        	if (outList.contains(station)) {
        		continue;
            }
            //ÿ����һ��վ�㣬����+1
        	int distance = path.get(nextStation).getDistance()+1;

        	List<Station> allPassStations = path.get(nextStation).getAllPassStations();
        	Path p = path.get(station);
        	if (p != null) {
        		//�Ѿ����������station�ľ���Ƚϳ���С�ľ���
        		if (p.getDistance()>distance) {
        			p.setDistance(distance);
        			//����start����վ�����·��
        			p.getAllPassStations().clear();
        			p.getAllPassStations().addAll(allPassStations);
        			p.getAllPassStations().add(station);
                }
            } 
            
        	else {//��û�м��������station�ľ���
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
        getShortestPath(startStation, endStation);//�ظ����㣬������վ����չ
        
        return path.get(endStation);
     }
    
// 	��ȡ��һվ��
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

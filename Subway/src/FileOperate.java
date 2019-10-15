import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileOperate {
	public FileOperate() {
		// TODO Auto-generated constructor stub
    }
	
	public FileOperate(String str1, String str2) throws Exception {
        if(str1.equals("-map")){
            for(int i=0;i<getFile(str2).size();i++){
                getFilePrint(str2,i);
                System.out.println("");
            }
        }else{
//            System.out.println("查询地铁线路图，请按照格式输入如：java Subway -map Subway.txt");
        	throw new Exception("查询地铁线路图，请按照格式输入如：java Subway -map Subway.txt");
        }
    }
	
	public FileOperate(String str, String str1, String str2, String str3, String str4, String str5) throws Exception {
		if(str.equals("-a") && str2.equals("-map") && str4.equals("-o")){
			List<String> lines = getFile(str3);
			int flag = 0;
			for(int i=0;i<lines.size();i++) {
				String[] lineAndStations = lines.get(i).split(" ");
				if(lineAndStations[0].equals(str1)){
					getFilePrint(str3,i);
//            	    System.out.println(line.get(i));
					writeFile(lines.get(i),str5);
					flag = 1;
				}
            }
			if(flag==0)
//				System.out.println("不存在此线路!");
				throw new Exception("不存在此线路!");
        }
		else{
//			System.out.println("查询地铁线路，请按照格式输入如：java Subway -a 1号线 -map Subway.txt -o Station.txt");
			throw new Exception("查询地铁线路，请按照格式输入如：java Subway -a 1号线 -map Subway.txt -o Station.txt");
        }
    }
	 
	public FileOperate(String str, String str1, String str2, String str3, String str4, String str5, String str6) throws Exception {
		if(str.equals("-b") &&  str3.equals("-map") && str5.equals("-o")) {
			if(str1.equals(str2)) {
//				System.out.println("起始站点与目的站点相同，请重新输入!");
				throw new Exception("起始站点与目的站点相同，请重新输入!");
			}
			 
			List<String> lines = getFile(str4);
			int flag1 = 0;
			int flag2 = 0;
			for(int i=0;i<lines.size();i++) {
				String[] lineAndStations = lines.get(i).split(" ");
				for(int j=1;j<lineAndStations.length;j++) {
					if(lineAndStations[j].equals(str1)){
						flag1 = 1;
                	}	
					if(lineAndStations[j].equals(str2)){
						flag2 = 1;
                	}
            	}
            }
			if(flag1==0) {
//				System.out.println("起始站点不存在!");
				throw new Exception("起始站点不存在!");
			}
				
			if(flag2==0) {
//				System.out.println("目的站点不存在!");
				throw new Exception("目的站点不存在!");
			}
             
//          获取最短路径getPath(start,end)
			setAllLines(str4);
			getRoutine(str6,str1,str2);
			for(int i=0;i<getFile(str6).size();i++){
				getFilePrint(str6,i);
            }
		}
		else {
//			System.out.println("查询地铁线路，请按照格式输入如：java Subway -b 苹果园 雍和宫 -map Subway.txt -o Routine.txt");
			throw new Exception("查询地铁线路，请按照格式输入如：java Subway -b 苹果园 雍和宫 -map Subway.txt -o Routine.txt");
		}
	}

	public List<String> getFile(String path){
        List<String> line = new ArrayList();
        try {
        	File file = new File(path);
        	if(file.exists()) {
        		InputStream in = new FileInputStream(file);
        		Reader reader = new InputStreamReader(in, "GBK");
        		BufferedReader br = new BufferedReader(reader);
        		String s;
        		while ((s = br.readLine()) != null) {
        			line.add(s);
                }
        		br.close();
        		reader.close();
        		in.close();
        	}
        	else {
//        		System.out.println(path+"文件不存在！");
        		throw new Exception(path+"文件不存在！");
            }
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
        return line;
    }

    public void getFilePrint(String path,int num){
        String str=getFile(path).get(num);
        System.out.println(str);
    }

    public void writeFile(String line,String path){
    	try {
    		FileWriter writer=new FileWriter(path);
    		BufferedWriter bw=new BufferedWriter(writer);
    		bw.write(line);
    		bw.close();
    	}
    	catch(Exception e){
    		e.printStackTrace();
        }
    }
    
//	存储所有地铁线路信息
	public void setAllLines(String path) throws Exception {
		File file=new File(path);
		if(file.exists()) {
			InputStream in = new FileInputStream(file);
            Reader reader = new InputStreamReader(in, "GBK");
            BufferedReader br = new BufferedReader(reader);
            List<String> lines = new ArrayList<String>();
            String s;
            while ((s = br.readLine()) != null) {
                lines.add(s);
            }
			for(int i=0;i<lines.size();i++) {
				List<Station> lineStations=new ArrayList<Station>();//线路上的各站点
				String[] lineAndStations = lines.get(i).split(" ");
           	 	for(int j=1;j<lineAndStations.length;j++) {
//           	 	添加线路上的各站点
           	 		lineStations.add(new Station(lineAndStations[j],lineAndStations[0]));
           	 	}
//    	 		添加相邻站点
           	 	for(int k=0;k<lineStations.size();k++) {
        	 		List<Station> allLinkedStations=lineStations.get(k).getAllLinkStations();
//        	 		起始站点
        	 		if (k==0) {
        	 			allLinkedStations.add(lineStations.get(k+1));
        	 			lineStations.get(k).setAllLinkStations(allLinkedStations);
					} 
//        	 		中间的站点
        	 		else if (k<(lineStations.size()-1) ) {
        	 			allLinkedStations.add(lineStations.get(k+1));
        	 			allLinkedStations.add(lineStations.get(k-1));
        	 			lineStations.get(k).setAllLinkStations(allLinkedStations);
					}	
//        	 		终点
        	 		else {
        	 			allLinkedStations.add(lineStations.get(k-1)); 
        	 			lineStations.get(k).setAllLinkStations(allLinkedStations);
        	 		}	   
           	 	}
//           	添加线路
				Station.allLines.add(lineStations);
            }
			br.close();
			reader.close();
			in.close();
		}
		else {
//			System.out.println(path+"文件不存在！");
    		throw new Exception(path+"文件不存在！");
		}
	}
    
    public void getRoutine(String path,String startStation,String endStation) throws IOException {
    	try {
 			File file = new File(path);
 	     	FileWriter writer = new FileWriter(file);
 	     	BufferedWriter bw=new BufferedWriter(writer);
 	     	
 	     	Station start = Station.getStationByName(startStation,Station.allLines);
 	     	Station end = Station.getStationByName(endStation,Station.allLines);
 	     	Path p=Dijkstra.getShortestPath(start,end);

 	     	bw.write("需乘坐"+p.getAllPassStations().size()+"个站点。\n");
	     	if(p!=null) {
//	        	起始站点与目的站点相邻
	     		if(start.getAllLinkStations().contains(end)) {
	     			bw.write(start.getLineName()+":\n");
	     			bw.write(start.getStationName()+"\n");
	     			bw.write(end.getStationName()+"\n");
	     			bw.close();  
	     			writer.close();
	     		}
	     		else {
	     			bw.write(p.getAllPassStations().get(1).getLineName()+":\n");
	     			bw.write(start.getStationName()+"\n");
	     			for(int i=0;i<p.getAllPassStations().size()-1;i++) {
	     				bw.write(p.getAllPassStations().get(i).getStationName()+"\n");
	     				if(!p.getAllPassStations().get(i+1).getLineName().equals(p.getAllPassStations().get(i).getLineName())) {
	     					bw.write("（同站换乘）"+p.getAllPassStations().get(i+1).getLineName()+"：\n");
	     				}   
	     			} 
	     			bw.write(end.getStationName()+"\n");
	     			bw.close();
	     			writer.close();
	     		}
	     	}
 		}
 		catch (Exception e) {
 			e.printStackTrace(); 
 		}
 	}
}
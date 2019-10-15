import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

public class Subway {

	public static void main(String [] args) throws Exception{
//    	D:\eclipse-workspace\Subway\src
//    	chcp 936 
        new Subway();
//      java Subway -map Subway.txt
        if(args.length==2) {
            try {
            	new FileOperate(args[0], args[1]);
			} catch (Exception e) {
				System.exit(0);
			}
        }
//      java Subway -a 1ºÅÏß -map Subway.txt -o Station.txt
        else if(args.length==6){
            try {
            	new FileOperate(args[0],args[1],args[2],args[3],args[4],args[5]);
    		} catch (Exception e) {
    			System.exit(0);
    		}
        }
//      java Subway -b Æ»¹ûÔ° ÓººÍ¹¬ -map Subway.txt -o Routine.txt
        else if(args.length==7){
        	try {
        		new FileOperate(args[0],args[1],args[2],args[3],args[4],args[5],args[6]);
    		} catch (Exception e) {
    			System.exit(0);
    		}
        }
        else{
//            System.out.println("ERROR!");
        	throw new Exception("ERROR");
        }
    }
}
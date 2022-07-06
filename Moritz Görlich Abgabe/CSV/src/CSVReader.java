import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.google.gson.Gson;


public class CSVReader {

	public static void main(String[] args) {
		// Import CSV from path -> export Json to import path
		
	
		String path = "C:\\Users\\Moritz Görlich\\Desktop\\convertcsv.csv";
		String line ="";
		String [] values = null;

		try {
			//Get Number of Rows
			BufferedReader br = new BufferedReader(new FileReader(path));
			  String input;
			     int count = 0;
			     while((input = br.readLine()) != null)
			     {
			         count++;
			     }
			     System.out.println("Count lines in csv : "+count);
			     br.close();
			     
			//Get Keys     
			br = new BufferedReader(new FileReader(path));
			line = br.readLine();
			String [] keys = line.split(", ");
		   
			//Json Object
			Gson gson = new Gson();
			//LinkedHashMap to iterate in order
			List < LinkedHashMap <String, Object>>  ListStringMap = new ArrayList<LinkedHashMap <String, Object>>();				
			LinkedHashMap<String, Object> stringMap;
		
			//Count Keys number
			int size = keys.length;	 
			System.out.println("Number of Keys in CSV: "+ size);
			
			String str = "";
			for(int i = 0; i < count; i++) {
				//New Json entry each iteration
				stringMap = new LinkedHashMap<String, Object>();
				str=br.readLine();
				
					if(str != null) {	
					values = str.split(",");
						//Single Entry 
						for(int j = 0 ; j < size; j++) {
							stringMap.put(keys[j], values[j]);
						}	
					ListStringMap.add(stringMap);
					}
			}
			
			// Serialization
			String json = gson.toJson(ListStringMap); // ==> json is {"key":"value","null":"null-entry"}

			System.out.println("json: " + json);
			
			//Count characters
			int JsonSize = json.length();
			System.out.println("Count characters: " + JsonSize);
		
			//Export to Json File
			Path importPath = Paths.get(path);
			Path exportPath = importPath.getParent();
				try (PrintWriter out = new PrintWriter(new FileWriter(exportPath + "/CSVexport.json"))) {
	            out.write(json);
	        } catch (Exception e) {
	            e.printStackTrace();
	        
	        }
	            
	     
		
			//Count elements -> {Key: Value}
			String[] array = json.split(",");
			System.out.println("Count Key-Value: " + array.length);
            br.close();
        
			
				
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	
}

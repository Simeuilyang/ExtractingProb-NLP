import java.io.*;

public class execPython {

	public static void main(String[] args) {
		
		String s =null;
		
		try {
			
			String python="python3 ";
			String fileName="textRank.py";
			//String fileName="/Users/han-eunju/Documents/textRank/ExtractingProb-NLP-1/textRank.py";
			Process process=Runtime.getRuntime().exec(python+fileName);
			
			BufferedReader stdInput = new BufferedReader(new
					InputStreamReader(process.getInputStream()));
			
			BufferedReader stdError= new BufferedReader(new
					InputStreamReader(process.getErrorStream()));
			
			System.out.println("running python file..");
			while((s=stdInput.readLine())!=null) 
				System.out.println(s);
			
			while((s=stdError.readLine())!=null)
				System.out.println(s);
					
			System.exit(0);
			
		}
		catch(IOException e){
			e.printStackTrace();
			System.exit(-1);
			
		}
	}

}

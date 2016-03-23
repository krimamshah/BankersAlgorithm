
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;



public class BankersAlgorithmMain {
	
	int allocated[][], need[][],available[][],maxNeed[][],nProcesses, nResources;
	
	private void inputRequests()throws IOException{
		
		   FileReader fr = new FileReader ("M:\\BankersAlgorithmInput2.txt"); //Reads the input file       
	       BufferedReader br = new BufferedReader (fr);
	       int lineCount = 0;
	       
	    // Read the number of processes
	    String line = br.readLine();   
		nProcesses = Integer.parseInt(line);
		
		//Read the number of resources
		  line = br.readLine();   
		 nResources = Integer.parseInt(line);
			
		 
		//Create the allocated, maxneed , need and available matrix 
		allocated = new int[nProcesses][nResources];
		maxNeed = new int[nProcesses][nResources];
		need = new int[nProcesses][nResources];
		available = new int[1][nResources];
		
		
		//Fill the allocated matrix by reading the values from the text file
		while(line!=null && lineCount<nProcesses){
			
		for(int i=0;i<nProcesses;i++){
			line = br.readLine();
			String[] s = line.split(" ");
			for(int j=0;j<nResources;j++){
				allocated[i][j] = Integer.parseInt(s[j]);	 
			}
			 lineCount++;
				
		}
	}
		
		lineCount = 0;
		
		//Fill the maxNeed matrix by reading the values from the text file
		while(line!=null && lineCount<nProcesses){
			
		for(int i=0;i<nProcesses;i++){
			line = br.readLine();
			String[] s = line.split(" ");
			for(int j=0;j<nResources;j++){
				maxNeed[i][j] = Integer.parseInt(s[j]);
			}
			lineCount++;
			
		}
	}
		
		//Fill the available matrix by reading the values from the text file
		line = br.readLine();
		String[] s = line.split(" ");
			for(int j=0;j<nResources;j++){
				available[0][j] = Integer.parseInt(s[j]);
			} 
			
		fr.close();
	}
	
	//calculate the need matrix
	private void calcNeedMatrix(){
		
		for(int i=0; i<nProcesses;i++){
			for(int j=0; j< nResources;j++){
				need[i][j] = maxNeed[i][j]-allocated[i][j];
			}
		}
		
	}
	
	//Check if the requested resource is available or not
	private boolean checkResourceAllocation(int k){
		for(int i=0;i<nResources;i++){
			if(available[0][i]<need[k][i])
				return false;
		}
		return true;
	}

	// Check if by fulfilling the resource request the system remains in safe state
	public void checkSafeState() throws IOException{
		
		inputRequests();
		calcNeedMatrix();
		int count = 0;
		boolean checked[] = new boolean[nProcesses];
		String fileName = "M:\\BankersAlgorithmOutput2.txt";
		FileWriter fileWriter = new FileWriter(fileName);
		BufferedWriter bufferedWriter =new BufferedWriter(fileWriter);
		
		while(count<nProcesses){
			boolean allocatedProcess = false;
			for(int i=0;i<nProcesses;i++)
				if(!checked[i] && checkResourceAllocation(i)){
					for(int j=0;j<nResources;j++)
						available[0][j]= allocated[i][j] + available[0][j];
					
				
					//System.out.printf("Resources allocated to process: %d\n",i);
					bufferedWriter.write("Resources allocated to process:"+ String.valueOf(i));
					bufferedWriter.newLine();
					checked[i] = true;
					allocatedProcess = true;
					count++;
				}
			
			if(!allocatedProcess)
				break;
		}
		
		if(count == nProcesses){
			bufferedWriter.write("System is in safe state");
		
		}
		else{
			bufferedWriter.write("System is in unsafe state");
			
		}
		bufferedWriter.close();
					
	}
	
	public static void main(String[] args) throws IOException {
		
		new BankersAlgorithmMain().checkSafeState();

	}

}





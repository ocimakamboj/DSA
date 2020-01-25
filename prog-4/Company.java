import java.util.*;

public class Company{
	String companyID;//The unique company ID given to every participating company by T&P
	String companyInformation;//Name and other basic information about the company
	int capacity;//This is the hiring capacity of the company
	String[] graduatePreference;

	public Company(){
		graduatePreference = new String[0];
	}

	//throws exception if graduate doesn't exist
	public void UpdateGraduatePreference(String[] A, HashMap<String, Graduate> GraduateList)throws Exception{
		boolean graduateThere = true;
		int j = 0;
		for(int i = 0; i < A.length ; i++){
			if (GraduateList.containsKey(A[i]) == true){
				j++;
			}
		}
		graduatePreference = new String[j];
		int k = 0;
		for(int i = 0; i < A.length ; i++){
			if (GraduateList.containsKey(A[i]) == true){
				graduatePreference[k] = A[i];
				k++;
			}else{
				graduateThere = false; 
			}
		}
		if (graduateThere == false){
			throw new Exception();
		}
	}

	public void DeleteGraduate(String graduateID){
		boolean graduateThere = false;
		int j = 0;
		int size = graduatePreference.length;
		for(int i = 0; i < size ; i++){
			if (graduatePreference[i].compareTo(graduateID) == 0){
				graduateThere = true;
				break;
			}else{
				j++;
			}
		}

		if(graduateThere == true){
			String[] graduatePreferenceNew = new String[size-1];
			int k = 0;
			for(int i = 0; i < size; i++){
				if(i==j){}
				else{
					graduatePreferenceNew[k] = graduatePreference[i];
					k++;
				}
			}
			graduatePreference = graduatePreferenceNew;
		}
	}

	//checks whether some Graduate exists in the preference list, returns true if it exists
	public boolean CheckGraduate(String graduateID){
		boolean graduateThere = false;
		int j = 0;
		int size = graduatePreference.length;
		for(int i = 0; i < size ; i++){
			if (graduatePreference[i].compareTo(graduateID) == 0){
				graduateThere = true;
				break;
			}else{
				j++;
			}
		}
		return(graduateThere);
	}

	public void printGraduatePreference(){
		int size = graduatePreference.length;
		if(size == 0){
			System.out.println("[]");
		}
		else if (size == 1){
			System.out.println("["+graduatePreference[0]+"]");
		}
		else{
			for(int i = 0 ; i < size; i++){
				if(i==0){
					System.out.print("[" + graduatePreference[i] +", ");
				}else if(i==size-1){
					System.out.println(graduatePreference[i] + "]");
				}else{
					System.out.print(graduatePreference[i] + ", ");
				}
			}
		}
	}
}
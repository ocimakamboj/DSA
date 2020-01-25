import java.util.*;

class Graduate{
	String graduateID;//This could be entry number or any other ID
	String graduateName;//Name of the graduating student
	double CGPA;//CGPA of the graduate
	String[] companyPreference;

	//throws exception if company doesn't exist
	public void UpdateCompanyPreference(String[] A, HashMap<String, Company> CompanyList)throws Exception{
		boolean companyThere = true;
		int j = 0;
		for(int i = 0; i < A.length ; i++){
			if (CompanyList.containsKey(A[i]) == true){
				j++;
			}
		}
		companyPreference = new String[j];
		int k = 0;
		for(int i = 0; i < A.length ; i++){
			if (CompanyList.containsKey(A[i]) == true){
				companyPreference[k] = A[i];
				k++;
			}else{
				companyThere = false; 
			}
		}
		if (companyThere == false){
			throw new Exception();
		}
	}

	public void DeleteCompany(String companyID){
		boolean companyThere = false;
		int j = 0;
		int size = companyPreference.length;
		for(int i = 0; i < size ; i++){
			if (companyPreference[i].compareTo(companyID) == 0) {
				companyThere = true;
				break;
			}else{
				j++;
			}
		}

		if(companyThere == true){
			String[] companyPreferenceNew = new String[size-1];
			int k = 0;
			for(int i = 0; i < size; i++){
				if(i==j){}
				else{
					companyPreferenceNew[k] = companyPreference[i];
					k++;
				}
			}
			companyPreference = companyPreferenceNew;
		}
	}

	//returns true if company exists in preference list
	public boolean CheckCompany(String companyID){
		boolean companyThere = false;
		int j = 0;
		int size = companyPreference.length;
		for(int i = 0; i < size ; i++){
			if (companyPreference[i].compareTo(companyID) == 0) {
				companyThere = true;
				break;
			}else{
				j++;
			}
		}
		return(companyThere);
	}

	public void printCompanyPreference(){
		int size = companyPreference.length;
		if(size == 0){
			System.out.println("[]");
		}
		else if (size == 1){
			System.out.println("["+companyPreference[0]+"]");
		}
		else{
			for(int i = 0 ; i < size; i++){
				if(i==0){
					System.out.print("[" + companyPreference[i] +", ");
				}else if(i==size-1){
					System.out.println(companyPreference[i] + "]");
				}else{
					System.out.print(companyPreference[i] + ", ");
				}
			}
		}
	}
}

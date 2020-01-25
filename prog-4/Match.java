import java.util.*;

public class Match{
	HashMap<String, String> graduateMap;
	HashMap<String, GraduateEachCompany> companyMap;
	HashMap<String, Company> companyList;
	HashMap<String, Graduate> graduateList;
	
	public Match(){
		graduateMap = new HashMap<String, String>();
		companyMap = new HashMap<String, GraduateEachCompany>();
		companyList = new HashMap<String, Company>();
		graduateList = new HashMap<String, Graduate>();
	}

	public void SetInput(HashMap<String, Company> CompanyList, HashMap<String, Graduate> GraduateList){

		Iterator iteratorC = CompanyList.entrySet().iterator();
	    while(iteratorC.hasNext()) {
	        Map.Entry entryC = (Map.Entry)iteratorC.next();
	        String key = (String)entryC.getKey();
	        Company varC= new Company();
	        varC.companyID = ((Company)entryC.getValue()).companyID;
	        varC.companyInformation = ((Company)entryC.getValue()).companyInformation;
	        varC.capacity = ((Company)entryC.getValue()).capacity;
	        varC.graduatePreference = ((Company)entryC.getValue()).graduatePreference;
	        companyList.put(key, varC);
	    }

	    Iterator iteratorG = GraduateList.entrySet().iterator();
	    while(iteratorG.hasNext()) {
	        Map.Entry entryG = (Map.Entry)iteratorG.next();
	        String key = (String)entryG.getKey();
	        Graduate varG= new Graduate();
	        varG.graduateID = ((Graduate)entryG.getValue()).graduateID;
	        varG.graduateName = ((Graduate)entryG.getValue()).graduateName;
	        varG.CGPA = ((Graduate)entryG.getValue()).CGPA;
	        varG.companyPreference = ((Graduate)entryG.getValue()).companyPreference;
	        graduateList.put(key, varG);
	    }
	}

	//checks consistency of preference lists, given graduateList and company List as input, returns true if they are consistent
	public boolean CheckConsistency(){
		boolean returnValue = true;
		//check whether company contains a graduate who is not interested in it
		Iterator iteratorC = companyList.entrySet().iterator();
	    while(iteratorC.hasNext()) {
	        Map.Entry entryC = (Map.Entry)iteratorC.next();
	        String companyID = (String)entryC.getKey();
	        Company varC = (Company)entryC.getValue();
	        String[] checkList = varC.graduatePreference;
	        for(int i = 0; i < checkList.length; i++){
	        	Graduate varG = graduateList.get(checkList[i]);
	        	if(varG.CheckCompany(companyID) == false){
	        		returnValue = false;
	        		return(returnValue);
	        	} 
	        }
	    }

	    //check whether graduate contains a compnay who is not interested in him
	    Iterator iteratorG = graduateList.entrySet().iterator();
	    while(iteratorG.hasNext()) {
	        Map.Entry entryG = (Map.Entry)iteratorG.next();
	        String graduateID = (String)entryG.getKey();
	        Graduate varG = (Graduate)entryG.getValue();
	        String[] checkList = varG.companyPreference;
	        for(int i = 0; i < checkList.length; i++){
	        	Company varC = companyList.get(checkList[i]);
	        	if(varC.CheckGraduate(graduateID) == false){
	        		returnValue = false;
	        		return(returnValue);
	        	} 
	        }
	    }
	    return(returnValue);
	}

	public boolean isFull(String companyID){
		GraduateEachCompany varGC = companyMap.get(companyID);
		int capacity = companyList.get(companyID).capacity;
		if(varGC.isFull(capacity) == true){
			return(true);
		}else{return(false);}
	}

	public boolean isOverSubscribed(String companyID){
		GraduateEachCompany varGC = companyMap.get(companyID);
		int capacity = companyList.get(companyID).capacity;
		if(varGC.isOverSubscribed(capacity) == true){
			return(true);
		}else{return(false);}
	}

	public String worstGraduate(String companyID){
		GraduateEachCompany varGC = companyMap.get(companyID);
		Company varC = companyList.get(companyID);
		return(varGC.WorstGraduate(varC));
	}

	//returns the first graduate that is unassigned in Graduate List that has a non empty company preference, null otherwise
	public String areAllAssigned(){
		String value = null;
		Iterator iteratorG = graduateList.entrySet().iterator();
	    while(iteratorG.hasNext()) {
	        Map.Entry entryG = (Map.Entry)iteratorG.next();
	        String graduateID = (String)entryG.getKey();
	        Graduate varG = (Graduate)entryG.getValue();
	        if(varG.companyPreference.length != 0){
		        if(graduateMap.containsKey(graduateID) == false){
		        	value = graduateID;
		        	return(value);
		        }
		    }
	    }
	    return(value);
	}

	public void Assignment(){
		String graduateID = areAllAssigned();
		while(graduateID != null){
			Graduate varG = graduateList.get(graduateID);
			String companyID = varG.companyPreference[0];
			graduateMap.put(graduateID,companyID);
			GraduateEachCompany varGEC = companyMap.get(companyID);
			if(varGEC == null){
				varGEC = new GraduateEachCompany();
				varGEC.graduateList.put(graduateID,graduateID);
				companyMap.put(companyID, varGEC);
			}else{
				varGEC.graduateList.put(graduateID,graduateID);
				companyMap.put(companyID, varGEC);
			}

			if(isOverSubscribed(companyID) == true){
				String graduateIDx = worstGraduate(companyID);
				graduateMap.remove(graduateIDx);
				varGEC = companyMap.get(companyID);
				varGEC.graduateList.remove(graduateIDx);
			}

			if(isFull(companyID) == true){
				String graduateIDx = worstGraduate(companyID);
				Company varC = companyList.get(companyID);
				int j = 0;
				int size = varC.graduatePreference.length;
				for(int i = 0; i<size; i++){
					if(graduateIDx.compareTo(varC.graduatePreference[i]) == 0){
						j++;
						break;
					}else{j++;}
				}
				//for(int k = j; k<size; k++){
				int k = j;
				while(k < varC.graduatePreference.length){
					String graduateIDy = varC.graduatePreference[k];
					varC.DeleteGraduate(graduateIDy);
					varG = graduateList.get(graduateIDy);
					varG.DeleteCompany(companyID);
				}
			}
			graduateID = areAllAssigned();
		}
	}

	public void printAssignment(){
		Iterator iterator = graduateMap.entrySet().iterator();
	    while(iterator.hasNext()) {
	        Map.Entry mentry = (Map.Entry)iterator.next();
	        System.out.print(mentry.getKey() + ", ");
	        System.out.println(mentry.getValue());
	    }

	    Iterator iteratorG = graduateList.entrySet().iterator();
	    while(iteratorG.hasNext()) {
	        Map.Entry mentry = (Map.Entry)iteratorG.next();
	        if(((Graduate)mentry.getValue()).companyPreference.length == 0){
		        System.out.print(mentry.getKey() + ", ");
		        System.out.println("");
		    }
	    }
	}

	public void printCompanyList(){
		Iterator iteratorC = companyList.entrySet().iterator();
	    while(iteratorC.hasNext()) {
	        Map.Entry mentry = (Map.Entry)iteratorC.next();
	        System.out.print("key is: "+ mentry.getKey() + " & Value is: ");
	        System.out.print(((Company)mentry.getValue()).companyInformation + " " + ((Company)mentry.getValue()).capacity + " ");
	        ((Company)mentry.getValue()).printGraduatePreference();
	    }
	}

	public void printGraduateList(){
		Iterator iteratorG = graduateList.entrySet().iterator();
	    while(iteratorG.hasNext()) {
	        Map.Entry mentry = (Map.Entry)iteratorG.next();
	        System.out.print("key is: "+ mentry.getKey() + " & Value is: ");
	        System.out.print(((Graduate)mentry.getValue()).graduateName + " " + ((Graduate)mentry.getValue()).CGPA + " ");
	        ((Graduate)mentry.getValue()).printCompanyPreference();
	    }
	}
}
import java.util.*;

public class GraduateEachCompany{
	HashMap<String, String> graduateList;

	public GraduateEachCompany(){
		graduateList = new HashMap<String, String>();
	}

	public boolean isFull(int capacity){
		if (graduateList.size() == capacity){
			return(true);
		}else{
			return(false);
		}
	}

	public boolean isOverSubscribed(int capacity){
		if (graduateList.size() > capacity){
			return(true);
		}else{return(false);}
	}

	public String WorstGraduate(Company company){
		String worstGraduateID = null;
		String[] checkList = company.graduatePreference;
		Iterator iteratorG = graduateList.entrySet().iterator();
		if(iteratorG.hasNext()){
			Map.Entry entryG = (Map.Entry)iteratorG.next();
	        String graduateID = (String)entryG.getKey();
	        worstGraduateID = graduateID;
		}
	    while(iteratorG.hasNext()) {
	        Map.Entry entryG = (Map.Entry)iteratorG.next();
	        String graduateID = (String)entryG.getKey();
	        
	        for(int i = 0; i < checkList.length; i++){
	        	if(checkList[i].compareTo(graduateID) == 0){
	        		break;
	        	}
	        	else if(checkList[i].compareTo(worstGraduateID) == 0){
	        		worstGraduateID = graduateID;
	        	}
	        }
	    }
	    return(worstGraduateID);
	}
}
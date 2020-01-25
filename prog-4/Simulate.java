import java.io.*;
import java.util.*;

public class Simulate{
    public static String[] getContent(String s){
        String[] details = s.split(",");
        //remove white spaces
        for(int i=0 ; i < details.length ; i++){
            details[i] = details[i].trim();
        }
        return(details);
    }

	public static void main(String args[]){
        String line;
        HashMap<String, Company> CompanyList = new HashMap<String, Company>();
        HashMap<String, Graduate> GraduateList = new HashMap<String, Graduate>();

		try{
            BufferedReader BR = new BufferedReader(new FileReader("medium1.txt"));
            while((line = BR.readLine()) != null){
                String[] details;

                //this adds company to the system, outputs an exception if company already exists
                if (line.startsWith("ADD COMPANY") == true){
                	details = getContent(line.substring(11));
                	Company varC = new Company();
                	varC.companyID = details[0];
                	varC.companyInformation = details[1];
                	varC.capacity = Integer.parseInt(details[2]);
                	if (CompanyList.containsKey(details[0]) == true){
                		System.out.println("Exception in ADD COMPANY : Company ID already occupied");
                	}else{
                		CompanyList.put(details[0],varC);
                	}
                }

                //this adds graduate to the system, outputs an exception if Graduate ID already exists
                //also outputs an exception if the preference list contains a company not in the companylist
                else if (line.startsWith("ADD GRADUATE") == true){
                	details = getContent(line.substring(12));
                	Graduate varG = new Graduate();
                	varG.graduateID = details[0];
                	varG.graduateName = details[1];
                	varG.CGPA = Double.parseDouble(details[2]);
                	
                	if (GraduateList.containsKey(details[0]) == true){
                		System.out.println("Exception in ADD GRADUATE : Graduate ID already occupied");
                	}else{
                		//creating array A which contains the preference list in entirety
	                	int j = 0;
	                	String[] A = new String[details.length - 3];
	                	for(int i=3; i<details.length ; i++){
	                		A[j] = details[i];
	                		j++;
	                	}
	                	try{
	                		varG.UpdateCompanyPreference(A, CompanyList);
	                	}catch(Exception e){System.out.println("Exception in ADD GRADUATE : company in preference list not in CompanyList");}
                		GraduateList.put(details[0],varG);
                	}
                }

                //this ranks the graduate for a particular company, outputs an exception if company is not there in the system
                //also outputs an exception if a graduate not there in graduateList
                else if (line.startsWith("RANK GRADUATES") == true){
                	details = getContent(line.substring(14));  	
                	Company varC = CompanyList.get(details[0]);
                	if(varC != null){
                		try{ 
                			//creating array A which contains the preference list in entirety
		                	int j = 0;
		                	String[] A = new String[details.length - 1];
		                	for(int i=1; i<details.length ; i++){
		                		A[j] = details[i];
		                		j++;
		                	}               			
                			varC.UpdateGraduatePreference(A, GraduateList);
                		}catch(Exception e){System.out.println("Exception in RANK GRADUATES : graduate in preference list not in GraduateList");}
                	}else{
                		System.out.println("Exception in RANK GRADUATES : Company ID not in system");
                	}
                }

                //this displays company information
                else if (line.startsWith("DISPLAY COMPANY") == true){
                	details = getContent(line.substring(15));
                	Company varC = CompanyList.get(details[0]);
                	if (varC == null){
                		System.out.println("Exception in DISPLAY COMPANY : Company ID not in system");
                	}else{
                		System.out.println(varC.companyInformation + ", " + varC.capacity);
                	}
                }

                //this displays graduate information
                else if (line.startsWith("DISPLAY GRADUATE") == true){
                	details = getContent(line.substring(16));
                	Graduate varG = GraduateList.get(details[0]);
                	if (varG == null){
                		System.out.println("Exception in DISPLAY GRADUATE : Graduate ID not in system");
                	}else{
                		System.out.print(varG.graduateName + ", " + varG.CGPA + ", ");
                		int size = varG.companyPreference.length;
                		if (size > 0){
	                		for(int i =0; i < size - 1; i++){
	                			System.out.print(varG.companyPreference[i] + ", ");
	                		}
	                		System.out.println(varG.companyPreference[size - 1]);
	                	}else{System.out.println("null");}
                	}
                }

                //this displays ranking by a company
                else if (line.startsWith("DISPLAY RANKING") == true){
                	details = getContent(line.substring(15));
                	Company varC = CompanyList.get(details[0]);
                	if (varC == null){
                		System.out.println("Exception in DISPLAY RANKING : Company ID not in system");
                	}else{
                		int size = varC.graduatePreference.length;
                		if(size>0){
	                		for(int i =0; i < size - 1; i++){
	                			System.out.print(varC.graduatePreference[i] + ", ");
	                		}
	                		System.out.println(varC.graduatePreference[size - 1]);
	                	}else{System.out.println("null");}
                	}
                }

                //update capacity of a company, outputs an exception if company not in system
                else if (line.startsWith("UPDATE CAPACITY") == true){
                	details = getContent(line.substring(15));
                	int newCapacity = Integer.parseInt(details[1]);
                	Company varC = CompanyList.get(details[0]);
                	if (varC == null){
                		System.out.println("Exception in UPDATE CAPACITY : Company ID not in system");
                	}else{
                		varC.capacity = newCapacity;
                	}
                }

                //update CGPA, outputs an exception if graduate not in system
                else if (line.startsWith("UPDATE CGPA") == true){
                	details = getContent(line.substring(11));
                	double newCGPA = Double.parseDouble(details[1]);
                	Graduate varG = GraduateList.get(details[0]);
                	if (varG == null){
                		System.out.println("Exception in UPDATE CGPA : Graduate ID not in system");
                	}else{
                		varG.CGPA = newCGPA;
                	}
                }

                //updates graduate preference, throws exception if graduate ID not there
                //also outputs an exception if the preference list contains a company not in the companylist
                else if(line.startsWith("UPDATE GRADUATE PREFERENCE") == true){
                	details = getContent(line.substring(26));
                	Graduate varG = GraduateList.get(details[0]);
                	if (varG == null){
                		System.out.println("Exception in UPDATE GRADUATE PREFERENCE : Graduate ID not in system");
                	}else{
                		String[] oldList = varG.companyPreference;
                		//creating array A which contains the preference list in entirety
	                	int j = 0;
	                	String[] A = new String[details.length - 1];
	                	for(int i=1; i<details.length ; i++){
	                		A[j] = details[i];
	                		j++;
	                	}
	                	try{
	                		varG.UpdateCompanyPreference(A, CompanyList);
	                	}catch(Exception e){System.out.println("Exception in UPDATE GRADUATE PREFERENCE : company in preference list not in CompanyList");}
                		
                		//delete graduate from companies he is no longer interested in
                		for(int i = 0; i<oldList.length ; i++){
                			boolean isThere = false;
                			for(int k = 0; k < varG.companyPreference.length; k++){		
                				if(oldList[i].compareTo(varG.companyPreference[k]) == 0){
                					isThere = true;
                					break;
                				}
                			}
                			if (isThere == false){
                				Company varC = CompanyList.get(oldList[i]);
                				varC.DeleteGraduate(details[0]);
                			}
                		}
                	}
                }

                else if (line.startsWith("DELETE COMPANY") == true){
                	details = getContent(line.substring(14));
                	if (CompanyList.containsKey(details[0]) == true){
                		Iterator iteratorG = GraduateList.entrySet().iterator();
					    while(iteratorG.hasNext()) {
					        Map.Entry mentry = (Map.Entry)iteratorG.next();
					        ((Graduate)mentry.getValue()).DeleteCompany(details[0]);
					    }
					    CompanyList.remove(details[0]);
                	}
                }

                else if (line.startsWith("DELETE GRADUATE") == true){
                	details = getContent(line.substring(15));
                	if (GraduateList.containsKey(details[0]) == true){
                		Iterator iteratorC = CompanyList.entrySet().iterator();
					    while(iteratorC.hasNext()) {
					        Map.Entry mentry = (Map.Entry)iteratorC.next();
					        ((Company)mentry.getValue()).DeleteGraduate(details[0]);
					    }
					    GraduateList.remove(details[0]);
                	}
                }

                else if (line.startsWith("MATCH") == true){
                	Match varM = new Match();
                	varM.SetInput(CompanyList, GraduateList);
                	if(varM.CheckConsistency() == true){
	                	varM.Assignment();
	                	varM.printAssignment();
	                }else{System.out.println("Exception");}
                }
            }
            
 			/*Iterator iteratorC = CompanyList.entrySet().iterator();
		    while(iteratorC.hasNext()) {
		        Map.Entry mentry = (Map.Entry)iteratorC.next();
		        System.out.print("key is: "+ mentry.getKey() + " & Value is: ");
		        System.out.print(((Company)mentry.getValue()).companyInformation + " " + ((Company)mentry.getValue()).capacity + " ");
		        ((Company)mentry.getValue()).printGraduatePreference();
		    }

		    Iterator iteratorG = GraduateList.entrySet().iterator();
		    while(iteratorG.hasNext()) {
		        Map.Entry mentry = (Map.Entry)iteratorG.next();
		        System.out.print("key is: "+ mentry.getKey() + " & Value is: ");
		        System.out.print(((Graduate)mentry.getValue()).graduateName + " " + ((Graduate)mentry.getValue()).CGPA + " ");
		        ((Graduate)mentry.getValue()).printCompanyPreference();
		    }*/
            BR.close();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
	}
}
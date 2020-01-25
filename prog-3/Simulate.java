import java.io.*;

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
		
        AVLTreeE EventsList = new AVLTreeE();
        AVLTreeP ParticipantList = new AVLTreeP();
        heapOfHeaps MaxHeap = new heapOfHeaps();
        String line;

		try{
            BufferedReader BR = new BufferedReader(new FileReader("query.txt"));
            while((line = BR.readLine()) != null){
                String[] details;
                if (line.startsWith("ADD EVENT") == true){
                	details = getContent(line.substring(9));
                    Event varE = new Event();
                    varE.eventID = details[0];
                    varE.eventName = details[1];
                    varE.eventDescription = details[2];
                    try{
                        EventsList.put(varE.eventID, varE);
                    }catch(Exception e){System.out.println("eventID already exists");}
                }


                else if (line.startsWith("ADD PARTICIPANT") == true){
                    details = getContent(line.substring(15));
                    String key = details[0];
                    Participant varP = new Participant();
                    varP.participantID = details[0];
                    varP.participantName = details[1];
                    varP.universityName = details[2];
                    try{
                        ParticipantList.put(key, varP);
                    }catch(Exception e){System.out.println("participantID already exists in system");}
                }

                else if (line.startsWith("ADD") == true){
                    details = getContent(line.substring(4));
                    String eventID = details[1];
                    String participantID = details[0];
                    try{
                        if(EventsList.search(eventID) == true){
                            if(ParticipantList.search(participantID) == true){
                                Event varE1 = new Event();
                                Participant varP = new Participant();
                                varE1 = EventsList.get(eventID);
                                varP = ParticipantList.get(participantID);
                                varE1.AddParticipant(participantID, varP);
                            }
                        }
                    }catch(Exception e){System.out.println("Event doesn't exist/participant not in system/participant already in event ");}
                }

                else if (line.startsWith("UPDATE SCORE") == true){
                    details = getContent(line.substring(12));
                    String participantID = details[0];
                    String eventID = details[1];
                    int score = Integer.parseInt(details[2]);
                    try{
                        Event varE = new Event();
                        Participant varP = new Participant();
                        varE = EventsList.get(eventID);
                        varP = varE.GetParticipant(participantID);
                        varE.UpdateScore(score, varP);
                    }catch(Exception e){System.out.println("Event doesn't exist/participant not in event ");}
                }

                else if (line.startsWith("DELETE EVENT PARTICIPANT") == true){
                    details = getContent(line.substring(24));
                    String participantID = details[0];
                    String eventID = details[1];
                    try{
                        Event varE = new Event();
                        Participant varP = new Participant();
                        varE = EventsList.get(eventID);
                        varP = varE.GetParticipant(participantID);
                        varE.deleteParticipant(varP);
                    }catch(Exception e){}
                }

                else if (line.startsWith("DELETE PARTICIPANT") == true){
                    details = getContent(line.substring(18));
                    String participantID = details[0];
                    try{
                        Participant varP = new Participant();
                        varP = ParticipantList.get(participantID);
                        EventsList.DeleteParticipant(EventsList.root, varP);
                        ParticipantList.remove(participantID);
                    }catch(Exception e){}
                }

                else if (line.startsWith("DELETE EVENT") == true){
                    details = getContent(line.substring(12));
                    String eventID = details[0];
                    try{
                        EventsList.remove(eventID);
                    }catch(Exception e){}
                }

                else if (line.startsWith("TOP3 IN EVENT") == true){
                    details = getContent(line.substring(13));
                    String eventID = details[0];
                    try{
                        Event varE = new Event();
                        varE = EventsList.get(eventID);                       
                        varE.Top3();
                    }catch(Exception e){System.out.println("Event " + eventID + " doesn't exist" );}
                }

                else if (line.startsWith("TOP3")==true){
                    try{
                        MaxHeap.setInput(EventsList);
                        MaxHeap.Top3();
                    }catch(Exception e){}
                }

            }
            BR.close();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
	}
}
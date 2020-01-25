public class Event{
	String eventID;
	String eventName;
	String eventDescription;
	AVLTreeP participantList;
	MaxHeapP scoreList;

	public Event(){
		participantList = new AVLTreeP();
		scoreList = new MaxHeapP();
	}

	//this throws exception if participant already exists
	public void AddParticipant(String k, Participant p)throws Exception{
		try{
			participantList.put(k,p);
		}catch(Exception e){throw new Exception();}
	}

	//get participant details given key, throws exception if key doesn't exist
	public Participant GetParticipant(String k)throws Exception{
		try{
			return(participantList.get(k));
		}catch(Exception e){throw new Exception();}
	}

	//change participant details corresponding to a particular key and particular Participant, throws exception if key doesn't exist
	public void ChangeParticipant(String k, Participant p)throws Exception{
		try{
			participantList.change(k,p);
		}catch(Exception e){throw new Exception();}
	}

	//method inserts marks for a participant P, updates marks of P is already existed, throws exception if participant not enrolled
	public void UpdateScore(int s, Participant p)throws Exception{
		try{
			if (p.isScoreThere() == true){
				int k = p.score;
				Participant varP = new Participant();
				varP.participantID = p.participantID;
				varP.participantName = p.participantName;
				varP.universityName = p.universityName;
				varP.score = s;
				participantList.change(p.participantID,varP);
				//p.score = s;
				scoreList.changeKey(k,p,s);
				scoreList.change(s,p,varP);
			}else{
				Participant varP = new Participant();
				varP.participantID = p.participantID;
				varP.participantName = p.participantName;
				varP.universityName = p.universityName;
				varP.score = s;
				participantList.change(p.participantID,varP);
				scoreList.insert(s,varP);
			}
		}catch(Exception e){System.out.println("Exception");throw new Exception();}
	}

	public Participant deleteParticipant(Participant p){
		try{
			p = GetParticipant(p.participantID);
			Participant value = p;
			participantList.remove(p.participantID);
			int s = p.score;
			scoreList.delete(p.score,p);
			return(value);
		}
		catch(Exception e){return p;}	
	}

	public Participant TopFirst()throws Exception{
		try{
			return(scoreList.maxValue());
		}catch(Exception e){throw new Exception();}
	}

	public void Top3(){
		try{
			if(scoreList.size == 0){}		
			else if (scoreList.size == 1){
				Participant p = scoreList.maxValue();
				System.out.println(p.participantID + ", " + p.participantName + ", " + p.universityName + ", " + p.score);
			}else if(scoreList.size ==2){
				Participant p1 = scoreList.maxValue();
				System.out.println(p1.participantID + ", " + p1.participantName + ", " + p1.universityName + ", " + p1.score);
				scoreList.removeMax();
				Participant p2 = scoreList.maxValue();
				System.out.println(p2.participantID + ", " + p2.participantName + ", " + p2.universityName + ", " + p2.score);
				scoreList.insert(p1.score, p1);
			}else{
				Participant p1 = scoreList.maxValue();
				System.out.println(p1.participantID + ", " + p1.participantName + ", " + p1.universityName + ", " + p1.score);
				scoreList.removeMax();
				Participant p2 = scoreList.maxValue();
				System.out.println(p2.participantID + ", " + p2.participantName + ", " + p2.universityName + ", " + p2.score);
				scoreList.removeMax();
				Participant p3 = scoreList.maxValue();
				System.out.println(p3.participantID + ", " + p3.participantName + ", " + p3.universityName + ", " + p3.score);
				scoreList.insert(p1.score, p1);
				scoreList.insert(p2.score, p2);
			}
		}catch(Exception e){}
	}

	public void PrintParticipant(){
		participantList.preOrder(participantList.root);
	}
}
public class heapOfHeaps{
	AVLTreeE eventList;

	public heapOfHeaps(){
		eventList = new AVLTreeE();
	}

	public void setInput(AVLTreeE e){
		eventList = e;
	}

	public void Top3(){
		try{
			Event e1 = eventList.TopScore(eventList.root);
			if(e1==null){return;}
			Participant p1 = e1.TopFirst();
			System.out.println(p1.participantID + ", " + p1.participantName + ", " + p1.universityName + ", " + e1.eventID + ", " + e1.eventName+ ", " + p1.score);
			
			e1.deleteParticipant(p1);
			Event e2 = eventList.TopScore(eventList.root);
			if(e2==null){
				e1.AddParticipant(p1.participantID,p1);
				int score1 = p1.score;
				p1.score = 0;
				e1.UpdateScore(score1,p1);
				return;
			}	
			Participant p2 = e2.TopFirst();
			System.out.println(p2.participantID + ", " + p2.participantName + ", " + p2.universityName + ", " + e2.eventID + ", " + e2.eventName+ ", " + p2.score);
			
			e2.deleteParticipant(p2);
			Event e3 = eventList.TopScore(eventList.root);
			if(e3==null){
				e1.AddParticipant(p1.participantID,p1);
				e2.AddParticipant(p2.participantID,p2);
				int score1 = p1.score;
				int score2 = p2.score;
				p1.score = 0;
				p2.score = 0;
				e1.UpdateScore(score1,p1);
				e2.UpdateScore(score2,p2);
				return;
			}
			Participant p3 = e3.TopFirst();
			System.out.println(p3.participantID + ", " + p3.participantName + ", " + p3.universityName + ", " + e3.eventID + ", " + e3.eventName+ ", " + p3.score);
			
			e1.AddParticipant(p1.participantID,p1);
			e2.AddParticipant(p2.participantID,p2);

			int score1 = p1.score;
			int score2 = p2.score;
			p1.score = 0;
			p2.score = 0;
			e1.UpdateScore(score1,p1);
			e2.UpdateScore(score2,p2);
		}catch(Exception e){}
	}
}
public class Participant{
	String participantID;
	String participantName;
	String universityName;
	int score;

	public Participant(){
		score = 0;
	}

	public boolean isScoreThere(){
		if (score > 0){
			return true;
		}else{return false;}
	}
}
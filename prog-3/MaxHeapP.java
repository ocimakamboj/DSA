class EntryP{
	public int key;
	public Participant value;
	public EntryP(int k, Participant v){
		key = k;
		value = v;
	}
}

public class MaxHeapP{
	final int MaxHeapSize = 1000000;
	public EntryP[] A;
	public int size;
	public MaxHeapP(){
		size = 0;
		A = new EntryP[MaxHeapSize];
	}

	public void upHeapBubble(int i){
		if (i==0){
			return;
		}else{
			int iParent = (i - 1)/2;	//index of the parent
			if(A[iParent].key < A[i].key){
				EntryP dummy = A[i];
				A[i] = A[iParent];
				A[iParent] = dummy;
				upHeapBubble(iParent);
			}else if(A[iParent].key == A[i].key){
				if(A[iParent].value.participantName.compareTo(A[i].value.participantName) > 0){
					EntryP dummy = A[i];
					A[i] = A[iParent];
					A[iParent] = dummy;
				}
			}else{
				return;
			}
		}
	}

	public void downHeapBubble(int i){
		if (i >= size){
			return;
		}else{
			int ichildLeft = 2*i + 1;	//index of the left child
			int ichildRight = 2*i + 2;	//index of the right child
			if (ichildRight >= size || ichildLeft >=size){
				return;
			}

			//if parent is greater than its children
			if((A[i].key > A[ichildLeft].key) && (A[i].key > A[ichildRight].key)){
				return;
			}else{
				if(A[ichildLeft].key < A[ichildRight].key){
					if(A[ichildRight].key == A[i].key){
						if(A[ichildRight].value.participantName.compareTo(A[i].value.participantName) < 0){
							EntryP dummy = A[ichildRight];
							A[ichildRight] = A[i];
							A[i] = dummy;
						}
					}else{
						EntryP dummy = A[ichildRight];
						A[ichildRight] = A[i];
						A[i] = dummy;
						downHeapBubble(ichildRight);
					}
				}else{
					if(A[ichildLeft].key == A[i].key){
						if(A[ichildLeft].value.participantName.compareTo(A[i].value.participantName) < 0){
							EntryP dummy = A[ichildLeft];
							A[ichildLeft] = A[i];
							A[i] = dummy;
						}
					}else{
						EntryP dummy = A[ichildLeft];
						A[ichildLeft] = A[i];
						A[i] = dummy;
						downHeapBubble(ichildLeft);
					}
				}
			}
		}
	}

	//Returns value of MaxHeap
	public Participant maxValue()throws Exception{
		if (size > 0){
			return(A[0].value);
		}else{throw new Exception();}
	}

	//Returns key of Maxheap
	public int maxKey()throws Exception{
		if(size>=1){
			return(A[0].key);
		}else{throw new Exception();}
	}

	public void insert(int k, Participant v){
		EntryP insert = new EntryP(k,v);
		A[size] = insert;
		upHeapBubble(size);
		size++;
	}

	public Participant removeMax(){
		if(size <= 3){
			Participant value = A[0].value;
			A[0] = A[size - 1];
			A[size - 1] = A[size];
			if(size == 3){
				if(A[1].key > A[0].key){
					EntryP dummy = A[0];
					A[0] = A[1];
					A[1] = dummy; 
				}else if(A[1].key == A[0].key){
					if((A[0].value.participantName).compareTo(A[1].value.participantName) < 0){
						EntryP dummy = A[0];
						A[0] = A[1];
						A[1] = dummy; 
					}
				}
			}
			size--;
			return(value);
		}else{
			Participant value = A[0].value;
			A[0] = A[size - 1];
			A[size - 1] = A[size];
			size--;
			downHeapBubble(0);
			return(value);
		}

	}

	//deletes the first node with key k, and value v
	public void delete(int k, Participant v){
		int i;
		for(i=0 ; i<size;i++){
			if(A[i].key == k){
				if (A[i].value == v){
					break;
				}
			}
		}
		if(i<size){
			A[i] = A[size-1];
			A[size-1] = A[size];
			size--;
			downHeapBubble(i);
		}
	}

	//finds all the nodes with key k, value v and changes value to vNew
	public void change(int k, Participant v, Participant vNew){
		int i;
		for(i=0; i<size;i++){
			if(A[i].key == k){
				if (A[i].value == v){
					A[i].value = vNew;
				}
			}
		}
	}

	//finds all the nodes with key k, value v and changes value to vNew
	public void changeKey(int k, Participant v, int s){
		int i;
		for(i=0; i<size;i++){
			if(A[i].key == k){
				if (A[i].value == v){
					A[i].key = s;
				}
			}
		}
	}

	public void printHeap(){
		if (size >= 1){
			if (size == 1){
				System.out.print("[" + A[0].key + " " + A[0].value.participantID + " " + A[0].value.participantName + " "  + A[0].value.universityName + " "+ A[0].value.score + "]");
			}else{
				System.out.print("[" + A[0].key + " " + A[0].value.participantName + " " + A[0].value.score + ", ");
				for (int i = 1; i<size-1;i++){
					System.out.print(A[i].key + " " + A[i].value.participantName + " " + A[i].value.score + ", ");
				}
				System.out.println(A[size-1].key + " " + A[size-1].value.participantName + " " + A[size-1].value.score + "]");
			}
		}
	}
}
public class Graph{
	int noOfVertices;
	int[][] adjacencyList;
	int[] finishTimes;
	int time;

	public Graph(int v){
		noOfVertices = v;
		adjacencyList = new int[v + 1][];
		finishTimes = new int[2*v + 1];
		time = 0;
	}

	//setting the neighbours of vertex v
	public void setEdges(int v, int[] list){
		adjacencyList[v] = new int[list.length];
		for(int i=0 ; i<list.length ; i++){
			adjacencyList[v][i] = list[i];
		}	
	}

	public int[] DFS(int v, int[] flagList){
		//marking v as explored
		//System.out.println(v);
		flagList[v] = 1;
		if (adjacencyList[v] != null){
			for(int i=0 ; i < (adjacencyList[v]).length ; i++){
				if (flagList[adjacencyList[v][i]] == 0){
					DFS(adjacencyList[v][i], flagList);
				}
			}
		}
		return(flagList);
	}

	public int[] DFStime(int v, int[] flagList){
		//marking v as explored
		flagList[v] = 1;
		time++;
		if (adjacencyList[v] != null){
			for(int i=0 ; i < (adjacencyList[v]).length ; i++){
				if (flagList[adjacencyList[v][i]] == 0){
					DFStime(adjacencyList[v][i], flagList);
				}
			}
		}
		time++;
		finishTimes[time] = v;
		return(flagList);
	}

	public void GraphDFSwithTimes(){
		int[] flagList = new int[noOfVertices + 1];
		for(int i=0 ; i < (noOfVertices + 1) ; i++){
			flagList[i] = 0;
		}

		for(int j = 1 ; j <= noOfVertices ; j++){
			if (flagList[j] == 0){
				flagList = DFStime(j, flagList);
			}
		}
	}
	
	//whether v is reachable from u	
	public boolean isReachable(int v, int u){
		int[] flagList = new int[noOfVertices + 1];
		for(int i=0 ; i < (noOfVertices + 1) ; i++){
			flagList[i] = 0;
		}

		flagList = DFS(u,flagList);
		boolean answer;
		if(flagList[v] == 1){
			answer = true;
		}else{
			answer = false;
		}
		return(answer);
	}

	public int OneWayConnected(){
		GraphDFSwithTimes();
		
		int i = 0;
		int a=0;
		int b=0;
		while(true){
			if(finishTimes[i] == 0){
				i++;
			}else{
				break;
			}
		}

		a = finishTimes[i];
		while(i<2*noOfVertices){
			if(finishTimes[i] != 0){
				b = a;
				a = finishTimes[i+1];
				//System.out.println(b + " and " + a + " are reachable from each other");
				i++;
			}else{
				if(finishTimes[i+1] == 0){
					a = finishTimes[i+1];
					i++;
				}else{
					a = finishTimes[i+1];
					if(isReachable(b,a) == true || isReachable(a,b) == true){
						//System.out.println(b + " and " + a + " are reachable from each other");
						i++;
					}else{
						//System.out.println(b + " and " + a + " are not reachable from each other");
						return(0);
					}
				}
			}
		}
		return(1);
		/*for(int i = 1; i<=2*noOfVertices; i++){
			if(finishTimes[i] != 0){
				if(finishTimes[i-1] != 0){
					for(int k = 0; k<adjacencyList[finishTimes[i]].length ; k++){
						if(adjacencyList[finishTimes[i]][k] == finishTimes[i-1]){
							break;
						}else{
							if( k == adjacencyList[finishTimes[i]].length - 1){
								if(adjacencyList[finishTimes[i]][k] == finishTimes[i-1]){
									break;
								}else{
									return(0);
								}
							}
						}
					}
				}else{
					int k;
					for(k = i - 1; k > 0 ; k--){
						if(finishTimes[k] != 0){
							break;
						}
					}
					if(finishTimes[k] != 0){
						if(isReachable(finishTimes[k], finishTimes[i]) == false){
							return(0);
						}
					}
				}
			}
		}
		return(1);*/
		/*int number = 0;
		for(int i = 2*noOfVertices ; i >= 0 ; i--){
			if(finishTimes[i] != 0){
				if(flagList[finishTimes[i]] == 0){
					number++;
					flagList = ReverseGraph().DFS(finishTimes[i], flagList);
				}
			}
		}
		printFinishTimes();
		System.out.println("no of SinglyConnectedComponents is " + number);*/
	}

	/*public void GraphDFS(){
		int[] flagList = new int[noOfVertices + 1];
		for(int i=0 ; i < (noOfVertices + 1) ; i++){
			flagList[i] = 0;
		}

		for(int j = 1 ; j <= noOfVertices ; j++){
			if (flagList[j] == 0){
				flagList = DFS(j, flagList);
			}
		}
	}

	public void printFinishTimes(){
		for (int i=1 ; i <= 2*noOfVertices ; i++){
			if(finishTimes[i] != 0){
				System.out.println("finish time of " + finishTimes[i] + " is " + i);
			}
		}
	}

	public void printGraph(Graph G){
		int v = G.noOfVertices;
		int[][] list = G.adjacencyList;
		for (int i = 1 ; i <= v ; i++){
			System.out.print( i + " --> ");
			if(list[i] == null){
				System.out.print("null");
			}else{
				for(int j = 0; j < list[i].length; j++){
					System.out.print(list[i][j] + ", ");
				}
			}
			System.out.println("");
		}
	}

	public Graph ReverseGraph(){
		Graph reverseGraph = new Graph(noOfVertices);
		int[][] reverseAdjacencyList = new int[noOfVertices+1][];
		for(int i = 0 ; i <= noOfVertices ; i++){
			if(adjacencyList[i] != null){
				for(int j = 0 ; j < adjacencyList[i].length ; j++){
					int size;
					int[] sub;
					if(reverseAdjacencyList[adjacencyList[i][j]] == null){
						size = 1;
						sub = new int[size];
					}else{
						size = reverseAdjacencyList[adjacencyList[i][j]].length + 1;
						sub = new int[size];
						for(int k = 0; k < reverseAdjacencyList[adjacencyList[i][j]].length ; k++){
							sub[k] = reverseAdjacencyList[adjacencyList[i][j]][k];
						}
					}
					sub[size - 1] = i;
					reverseAdjacencyList[adjacencyList[i][j]] = sub;
				}
			}
		}
		reverseGraph.adjacencyList = reverseAdjacencyList;
		return(reverseGraph);
	}*/

}
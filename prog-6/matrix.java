import java.io.*;
import java.util.*;

class Array{
	int[] array;
	int[] arrayIndex;
	int size;

	public Array(int n){
		size = n;
		array = new int[n];
		arrayIndex = new int[n];
	}

	public void ConstructArray(int[] A){
		for(int i = 0; i<size ; i++){
			array[i] = A[i];
		}
	}

	public void ConstructArrayIndex(){
		for(int i = 0; i<size ; i++){
			arrayIndex[i] = i;
		}
	}
}

public class matrix{
	public Array rowSum;
	public Array columnSum;
	public int[][] Matrix;
	public int n;

	public matrix(int v){
		n = v;
		Matrix = new int[v][v];
		rowSum = new Array(v);
		columnSum = new Array(v);
	}

	public static int[] getContent(String s){
        String[] detailsString = s.split(",");
        int size = detailsString.length;
        int[] details = new int[size];
        for(int i = 0; i < size; i++){
        	details[i] = Integer.parseInt(detailsString[i]);
        }
        return(details);
    }

	public Array SortArray(Array inputArray){
		int size = inputArray.size;
		Array outputArray = new Array(size);
		for(int i = 0; i<size ; i++){
			outputArray.array[i] = inputArray.array[i];
			outputArray.arrayIndex[i] = inputArray.arrayIndex[i];
		}
		for(int i = 0; i<size-1 ; i++){
			for(int j = 0; j < (size - i - 1) ; j++){
				if(outputArray.array[j] < outputArray.array[j+1]){
					int dummy1 = outputArray.array[j];
					outputArray.array[j] = outputArray.array[j+1];
					outputArray.array[j+1] = dummy1;
					int dummy2 = outputArray.arrayIndex[j];
					outputArray.arrayIndex[j] = outputArray.arrayIndex[j+1];
					outputArray.arrayIndex[j+1] = dummy2;
				}
			}
		}
		return(outputArray);
	}

	public int[][] constructMatrix() throws Exception{
		Array varR = SortArray(rowSum);
		Array varC = SortArray(columnSum);
		int[] RSum = varR.array;
		int[] RIndex = varR.arrayIndex;
		int[] CSum = varC.array;
		int[] CIndex = varC.arrayIndex;

		for(int i = 0 ; i < n ; i++){
			for(int j = 0 ; j < RSum[i] ; j++){
				Matrix[RIndex[i]][CIndex[j]] = 1;
				CSum[j] = CSum[j] - 1;
				if(CSum[j] == -1){
					throw new Exception();
				}
			}
			varC.ConstructArray(CSum);
			varC = SortArray(varC);
			CSum = varC.array;
			CIndex = varC.arrayIndex;
		}
		if(CSum[0] > 0){
			throw new Exception();
		}
		return(Matrix);
	}

	public void PrintMatrix(){
		for(int i=0; i<n ; i++){
			for(int j=0; j<n-1 ; j++){
				System.out.print(Matrix[i][j] + ",");
			}
			System.out.println(Matrix[i][n-1]);
		}
	}

	public static void main(String args[]){
        String line;
        int[] details;
        matrix varM;
        try{
            BufferedReader BR = new BufferedReader(new FileReader("input.txt"));
            line = BR.readLine();
            int size = Integer.parseInt(line);
            varM = new matrix(size);

            line = BR.readLine();
            details =  getContent(line);
            varM.rowSum.ConstructArray(details);
            varM.rowSum.ConstructArrayIndex();
            line = BR.readLine();
            details =  getContent(line);
            varM.columnSum.ConstructArray(details);
            varM.columnSum.ConstructArrayIndex();

            PrintStream out = new PrintStream(new FileOutputStream("output.txt"));
            System.setOut(out);

            try{
                varM.constructMatrix();
                System.out.println(1);
                varM.PrintMatrix();
            }catch(ArrayIndexOutOfBoundsException e){
                System.out.println(0);
            }catch(Exception e){
                System.out.println(0);
            }
        BR.close();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
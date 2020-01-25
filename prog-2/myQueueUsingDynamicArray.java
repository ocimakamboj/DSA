class myQueueUsingDynamicArray{

    private dynamicArray A;
    private int queueSize;
    private int numberElements;
    private int dPointer;
    private int ePointer;
    
    public myQueueUsingDynamicArray(){
        A = new dynamicArray();
        queueSize = 1;
        numberElements = 0;
        dPointer = 0;
        ePointer = 0;
    }

    //This method should return the number of elements in the queue
    public int getSize(){
        return(numberElements);
    }
    
    //This should implement the enqueue operation of Queue
    public void enqueue(int value){
        if (ePointer < queueSize){
            A.modifyElement(value,ePointer);
            ePointer++;
            numberElements++;
        }
        else{
            //maintaining space complexity in the if block
            if (numberElements+1 <= queueSize){
                for(int index = 0; index < queueSize; index++){
                    if(index < ePointer - dPointer){
                        A.modifyElement(A.getElement(index + dPointer),index);
                    }
                    else{
                        A.modifyElement(0,index);    
                    }
                }

                
                ePointer = ePointer - dPointer;
                dPointer = 0;
                A.modifyElement(value,ePointer);
                ePointer++;
                numberElements++;
            }

            else{
                A.doubleSize();
                A.modifyElement(value,ePointer);
                ePointer++;
                numberElements++;
                queueSize = 2*queueSize;
            }
        }
    }
    
    //This should implement the dequeue operation of Queue
    //This method should throw an exception in case the queue is empty.
    public int dequeue(){
        int value = 0;

        try{
            if (numberElements == 0){
                System.out.println("Error : dequeue() invalid. The queue is empty");
                throw new Exception();
            }
            else{
                value = A.getElement(dPointer);
                A.modifyElement(0,dPointer);
                dPointer++;
                numberElements--;
            }
        }
        catch(Exception e){
            //e.printStackTrace();System.exit(0);
        }
        

        //checking space efficiency
        finally{
            if (numberElements == 0){
                A = new dynamicArray();
                queueSize = 1;
                dPointer = 0;
                ePointer = 0;
            }
            if (queueSize > 2*numberElements && numberElements!=0){
                for (int index = 0; index < queueSize/2; index++){
                    A.modifyElement(A.getElement(index + dPointer),index);
                }
        
                A.halveSize();
                queueSize = queueSize/2;
                dPointer = 0;
                ePointer = numberElements;
            }
        }

        return(value);
    }

    public void PrintQueue(){
        for (int index = 0; index < queueSize; index++){
            if (queueSize == 1){
                System.out.println("[" + A.getElement(index) + "]");
            }
            else{
                if (index==0){
                    System.out.print("[" + A.getElement(index) + ",");
                }
                else if (index == queueSize - 1){
                    System.out.println(A.getElement(index) + "]");
                }
                else{
                    System.out.print(A.getElement(index) + ",");
                }
            }
        }
    }
}
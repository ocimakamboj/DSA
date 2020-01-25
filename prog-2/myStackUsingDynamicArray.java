class myStackUsingDynamicArray{

    private dynamicArray A;
    private int stackSize;
    private int numberElements;
    private int pointer;
    
    public myStackUsingDynamicArray(){
        A = new dynamicArray();
        stackSize = 1;
        numberElements = 0;
        pointer = 0;
    }
    
    //This method should return the size of the stack
    public int getSize(){
        return(numberElements);
    }
    
    //This should implement the push operation of stack
    public void push(int value){
        if (pointer < stackSize){
            A.modifyElement(value,pointer);
            pointer++;
            numberElements++;
        }
        else{
            A.doubleSize();
            A.modifyElement(value,pointer);
            pointer++;
            numberElements++;
            stackSize = 2*stackSize;
        }
    }
    
    //This should implement the pop operation of stack.
    //This method should throw an exception in case the stack is empty.
    public int pop(){
        int value = 0;

        try{
            if (numberElements == 0){
                System.out.println("Error : pop() invalid. The Stack is empty");
                throw new Exception();
            }
            else{
                value = A.getElement(pointer-1);
                A.modifyElement(0,pointer-1);
                pointer--;
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
                stackSize = 1;
                pointer = 0;
            }
            if (stackSize >= 2*numberElements && numberElements!=0){
                A.halveSize();
                stackSize = stackSize/2;
                pointer = numberElements;
            }
        }

        return(value);
    }

    public void PrintStack(){
        for (int index = 0; index < stackSize; index++){
            if (stackSize == 1){
                System.out.println("[" + A.getElement(index) + "]");
            }
            else{
                if (index==0){
                    System.out.print("[" + A.getElement(index) + ",");
                }
                else if (index == stackSize - 1){
                    System.out.println(A.getElement(index) + "]");
                }
                else{
                    System.out.print(A.getElement(index) + ",");
                }
            }
        }
    }
}
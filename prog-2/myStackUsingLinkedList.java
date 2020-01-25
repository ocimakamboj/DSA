class myStackUsingLinkedList<E>{

    private SinglyLinkedList<E> L;
    private int size;
    
    public myStackUsingLinkedList(){
        L = new SinglyLinkedList<>();
        size = 0;
    }
    
    //This method should return the size of the stack
    public int getSize(){
        return(size);
    }
    
    //This should implement the push operation of stack
    public void push(E value){
        L.addLast(value);
        size++;
    }
    
    //This should implement the pop operation of stack.
    //This method should throw an exception in case the stack is empty.
    public E pop(){
        E answer = L.last();

        try{
            if(size==0){
                System.out.println("Error : pop() invalid. The stack is empty");
                throw new Exception();
            }
            else{
                int i = 1;
                while(i < size){
                    L.addLast(L.removeFirst());
                    i++;
                }
                L.removeFirst();
                size--;
            }
        }
        catch(Exception e){
            //e.printStackTrace();System.exit(0);
        }

        return(answer);
    }

    public void PrintStack(){
        int size1 = size;
        if (size1==0){
            System.out.println("null");
        }
        else{
            while(size1 != 1){
                System.out.print(L.first() + "-->");
                L.addLast(L.removeFirst());
                size1--;
            }

            System.out.println(L.first());
            L.addLast(L.removeFirst());
        }

        //L.PrintList();
    }
}
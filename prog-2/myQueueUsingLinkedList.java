class myQueueUsingLinkedList<E>{

    private SinglyLinkedList<E> L;
    private int size;
    
    public myQueueUsingLinkedList(){
        L = new SinglyLinkedList<>();
        size = 0;
    }
    
    //This method should return the size of the queue
    public int getSize(){
        return(size);
    }
    
    //This should implement the enqueue operation of queue
    public void enqueue(E value){
        L.addLast(value);
        size++;
    }
    
    //This should implement the dequeue operation of queue.
    //This method should throw an exception in case the queue is empty.
    public E dequeue(){
        E answer = L.first();

        try{
            if(size==0){
                System.out.println("Error : dequeue() invalid. The queue is empty");
                throw new Exception();
            }
            else{
                L.removeFirst();
                size--;
            }
        }
        catch(Exception e){
            //e.printStackTrace();System.exit(0);
        }

        return(answer);
    }

    public void PrintQueue(){
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
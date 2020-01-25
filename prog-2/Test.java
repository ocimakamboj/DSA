public class Test{
    public static void main(String[] args) {

        myQueueUsingDynamicArray Queue = new myQueueUsingDynamicArray();
        Queue.enqueue(1);
        Queue.PrintQueue();
        Queue.enqueue(2);
        Queue.PrintQueue();
        Queue.enqueue(3);
        Queue.PrintQueue();
        Queue.enqueue(4);
        Queue.PrintQueue();
        Queue.dequeue();
        Queue.PrintQueue();
        Queue.enqueue(5);
        Queue.PrintQueue();
        Queue.enqueue(6);
        Queue.PrintQueue();
        Queue.dequeue();
        Queue.PrintQueue();
        Queue.dequeue();
        Queue.PrintQueue();
        System.out.println("size = " + Queue.getSize());
        
        
        Queue.PrintQueue();
        Queue.enqueue(7);
        Queue.PrintQueue();
        Queue.enqueue(8);
        Queue.PrintQueue();
        System.out.println("dequeue is " + Queue.dequeue());
        Queue.PrintQueue();
        
        Queue.enqueue(9);
        Queue.PrintQueue();
        Queue.dequeue();
        Queue.PrintQueue();
        
        Queue.enqueue(10);
        Queue.PrintQueue();

        Queue.dequeue();
        Queue.PrintQueue();
        Queue.dequeue();
        Queue.PrintQueue();
        Queue.dequeue();
        Queue.PrintQueue();
        Queue.dequeue();
        Queue.PrintQueue();
        Queue.dequeue();
        Queue.PrintQueue();
        Queue.dequeue();
        Queue.PrintQueue();

        /*myStackUsingDynamicArray Stack = new myStackUsingDynamicArray();
        Stack.push(1);
        Stack.PrintStack();
        Stack.push(2);
        Stack.PrintStack();
        Stack.push(3);
        Stack.PrintStack();
        Stack.push(4);
        Stack.PrintStack();
        Stack.push(5);
        Stack.PrintStack();
        Stack.push(6);
        Stack.PrintStack();
        System.out.println("pop is " + Stack.pop());
        Stack.PrintStack();
        System.out.println("pop is " + Stack.pop());
        Stack.PrintStack();
        Stack.push(7);
        Stack.PrintStack();
        Stack.push(8);
        Stack.PrintStack();
        Stack.push(9);
        Stack.PrintStack();
        System.out.println("pop is " + Stack.pop());
        Stack.PrintStack();
        Stack.push(10);
        Stack.PrintStack();
        Stack.push(11);
        Stack.PrintStack();
        Stack.push(12);
        Stack.PrintStack();
        System.out.println("pop is " + Stack.pop());
        Stack.PrintStack();
        System.out.println("pop is " + Stack.pop());
        Stack.PrintStack();
        System.out.println("pop is " + Stack.pop());
        Stack.PrintStack();
        System.out.println("pop is " + Stack.pop());
        Stack.PrintStack();
        System.out.println("pop is " + Stack.pop());
        Stack.PrintStack();
        System.out.println("pop is " + Stack.pop());
        Stack.PrintStack();
        Stack.push(13);
        Stack.PrintStack();
        Stack.push(14);
        Stack.PrintStack();
        Stack.push(15);
        Stack.PrintStack();
        Stack.push(16);
        Stack.PrintStack();
        Stack.push(17);
        Stack.PrintStack();
        System.out.println("pop is " + Stack.pop());
        Stack.PrintStack();
        System.out.println("pop is " + Stack.pop());
        Stack.PrintStack();
        System.out.println("pop is " + Stack.pop());
        Stack.PrintStack();
        System.out.println("pop is " + Stack.pop());
        Stack.PrintStack();
        System.out.println("pop is " + Stack.pop());
        Stack.PrintStack();
        System.out.println("pop is " + Stack.pop());
        Stack.PrintStack();
        System.out.println("pop is " + Stack.pop());
        Stack.PrintStack();
        System.out.println("pop is " + Stack.pop());
        Stack.PrintStack();
        System.out.println("pop is " + Stack.pop());
        Stack.PrintStack();*/

        /*SinglyLinkedList<Integer> L = new SinglyLinkedList<>();
        
        L.PrintList();
        L.addFirst(1);
        L.PrintList();
        
        L.addLast(2);
        L.PrintList(); 
        L.addLast(3);
        L.PrintList(); 
        L.addLast(4);
        L.PrintList(); 
        L.addLast(5);
        L.PrintList(); 
        
        L.removeFirst();
        L.PrintList();*/

        /*myQueueUsingLinkedList<String> Queue = new myQueueUsingLinkedList<>();
        Queue.enqueue("John");
        Queue.enqueue("Sherlock");
        Queue.enqueue("Mycroft");
        Queue.enqueue("Mary");
        Queue.enqueue("Mrs. Hudson");
        Queue.enqueue("Lestrade");
        Queue.enqueue("Irene Adler");
        Queue.enqueue("Molly");
        Queue.enqueue("Moriarty");
        Queue.enqueue("Andrews");
        Queue.PrintQueue();

        System.out.println("dequeue is " + Queue.dequeue());
        Queue.PrintQueue();
        System.out.println("dequeue is " + Queue.dequeue());
        Queue.PrintQueue();
        System.out.println("dequeue is " + Queue.dequeue());
        Queue.PrintQueue();
        System.out.println("dequeue is " + Queue.dequeue());
        Queue.PrintQueue();
        System.out.println("dequeue is " + Queue.dequeue());
        Queue.PrintQueue();*/

        /*myStackUsingLinkedList<String> Stack = new myStackUsingLinkedList<>();
        Stack.push("John");
        Stack.push("Sherlock");
        Stack.push("Mycroft");
        Stack.push("Mary");
        System.out.println("pop is " + Stack.pop());
        Stack.PrintStack();
        System.out.println("pop is " + Stack.pop());
        Stack.PrintStack();
        Stack.push("Mrs Hudson");
        Stack.PrintStack();
        System.out.println("pop is " + Stack.pop());
        Stack.PrintStack();
        Stack.push("Lestrade");
        Stack.PrintStack();
        Stack.push("Irene Adler");
        Stack.PrintStack();
        Stack.push("Molly");
        Stack.PrintStack();
        System.out.println("pop is " + Stack.pop());
        Stack.PrintStack();
        System.out.println("pop is " + Stack.pop());
        Stack.PrintStack();
        Stack.push("Moriarty");
        Stack.PrintStack();
        Stack.push("Andrews");
        Stack.PrintStack();*/

        
    }
}


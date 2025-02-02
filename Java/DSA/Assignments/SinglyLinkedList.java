package Java.DSA.Assignments;
import java.util.Scanner;

public class SinglyLinkedList<AnyType> {
    //declare head and tail nodes; declare temp node for prepending
    private Node<AnyType> head;
    private Node<AnyType> tail;
    private Node<AnyType> temp;

    //constructor class setting head, tail, and temp to null upon instantiation
    public SinglyLinkedList() {
        this.head = null;
        this.tail = null;
        this.temp = null;
    }
    //private static class for Node operations, Node wont be accessed unless working with a linkedlist in this case
    private static class Node<AnyType>{

        //constructor for not knowing which node is next
        public Node(AnyType val) {
            this.val = val;
            this.next = null;
        }
        //declare node value and a node for the next reference variables
        public AnyType val;
        public Node<AnyType> next;
    }

    //add Node to end of linked list for enqueue to ADT queue
    public void appendNode (AnyType data) {
        //instantiate a Node
        Node<AnyType> node = new Node<>(data);
        //check to see if the head of list is Null before appending, if null, make the Node the head and tail of the list
        if (head == null) {
            head = node;
            tail = node;
        }
        //if not null, the instantiated Node becomes the new tail, pointing the current tail's next refernece to the new Node
        else {
            tail.next = node;
            tail = node;  
        }     
    }
    //add Node to beginning of linked list for pushing to ADT stack
    public void preppendNode (AnyType data) {
        //instantiate a Node
        Node<AnyType> node = new Node<>(data);
        //check to see if the head of list is Null before appending, if null, make the Node the head and tail of the list
        if (head == null) {
            head = node;
            tail = node;
        }
        //if not null, the instantiated Node becomes the new head, and then pointing the current Node's next reference to the previous head of the list
        else {
            temp = head;
            head = node;
            head.next = temp;  
        }     
    }

    public AnyType popHeadNode() {
        //if the head is empty, then return null
        if (head == null) {
            System.out.println("Linked List is empty!"); 
            return null;
        }
        //else, poppedValue temporarily stores the head value, the new list hit = the next node in the list
        else {
            AnyType poppedValue = head.val;
            head = head.next;
            return poppedValue;
        }

    }

    public AnyType peakHeadNodeValue() {
        //if the head is empty, then return null
        if (head == null) {
            System.out.println("Linked List is empty!"); 
            return null;
        }
        //else, poppedValue stores the head value, and we print/return that value
        else {
            AnyType peakedValue = head.val;
            System.out.print("The head Node Value is: ");
            return peakedValue;
        }
    }

    public AnyType peakTailNodeValue() {
        //if the tail or head is empty, then return null
        if (tail == null || head == null) {
            System.out.println("Linked List is empty!"); 
            return null;
        }
        //else, poppedValue stores the tail value, and we print/return that value
        else {
            AnyType peakedValue = tail.val;
            System.out.print("The tail Node Value is: ");
            return peakedValue;
        }
    }
    //check to see if list is empty, return a boolean
    public boolean isListEmpty() {

        if(head == null || tail == null) {
            return true;
        } 
        else {
            return false;
        }
    }
    
    public int getListLength() {
        //declare a counter variable locally, check to see if head or tail is null, and return -1 if true
        int counter = 0;
        if (head == null || tail == null) {
            System.out.println("Linked List is empty!");
            return -1;
        }
        //else, use the temp node class member to temporarily store the list's head node
        else {
            temp = head;
            //traverse the list until the temp node is null or the tail node's next pointer is null
            while (temp != null) {
                //increment the counter every time we traverse to a new node
                counter += 1;
                //assign the temp node to the next node in the list until there's no next node to jump to
                temp = temp.next;
            }
        }
        
        System.out.println("The Linked List's length is: ");
        return counter;
    }

    //method to traverse the list, and print each item if it's not null
    public void printList() {
        
        if (head == null) {
            System.out.println("Linked List is empty!"); 
        }

        else {
            //assign the class's temp member to the head of the list to start the loop
            temp = head;
            do {
                //print the temp node's value
                System.out.print(temp.val);
                //if the temp's next node is not null, then we print an arrow pointing to the next node
                if (temp.next != null){
                    System.out.print(" -> ");
                }
                //the current node in the traverse operation becomes the next node
                temp = temp.next;
            } while (temp != null);

        }
        
    }

    public static void main(String[] args) {

        Scanner scnr = new Scanner(System.in);
        SinglyLinkedList<Integer> ll = new SinglyLinkedList<Integer>();
        
        int adtSelection = 0;
        int option = 0;

        System.out.println("Select an ADT to implement via a Linked List:");
        System.out.println("Select 1 for Queue");
        System.out.println("Select 2 for Stack");
        adtSelection = scnr.nextInt();



        if (adtSelection == 1) {

            while (option <= 8) {

                System.out.println("Select an option:");
                System.out.println("1. Append a node to the queue.");
                System.out.println("2. Pop head node.");
                System.out.println("3. Peak the queue head value.");
                System.out.println("4. Peak the queue tail value.");
                System.out.println("5. Determine if the queue is empty.");
                System.out.println("6. Get the queue length.");
                System.out.println("7. Print the queue.");
                System.out.println("8. Exit program");

                option = scnr.nextInt();
                int input;
                switch (option) {

                    case 1:
                        System.out.println("Enter a value to append:");
                        input = scnr.nextInt();
                        ll.appendNode(input);
                        break;
                    
                    case 2:
                        System.out.println("The popped head node value is: " +  ll.popHeadNode());
                        break;

                    case 3:
                        System.out.println(ll.peakHeadNodeValue());
                        break;

                    case 4:
                        System.out.println(ll.peakTailNodeValue());
                        break;
                    
                    case 5:
                        System.out.println(ll.isListEmpty());
                        break;
                        
                    case 6:
                        System.out.println(ll.getListLength());
                        break;

                    case 7:
                        ll.printList();
                        break;
                    
                    default:
                        break;

                }

                System.out.println();
                if (option == 8) {
                    break;
                }

            }
        } 
        else if (adtSelection == 2) {

            while (option <= 8) {

                System.out.println("Select an option:");
                System.out.println("1. Preppend a node to the stack.");
                System.out.println("2. Pop head node.");
                System.out.println("3. Peak the stack's head value.");
                System.out.println("4. Peak the stack's tail value.");
                System.out.println("5. Determine if the list is empty.");
                System.out.println("6. Get the stack's length.");
                System.out.println("7. Print the stack.");
                System.out.println("8. Exit program");

                option = scnr.nextInt();
                int input;

                switch (option) {

                    case 1:
                        System.out.println("Enter a value to peppend:");
                        input = scnr.nextInt();
                        ll.preppendNode(input);
                        break;
                    
                    case 2:
                        System.out.println("The popped head node value is: " +  ll.popHeadNode());
                        break;

                    case 3:
                        System.out.println(ll.peakHeadNodeValue());
                        break;

                    case 4:
                        System.out.println(ll.peakTailNodeValue());
                        break;
                    
                    case 5:
                        System.out.println(ll.isListEmpty());
                        break;
                        
                    case 6:
                        System.out.println(ll.getListLength());
                        break;

                    case 7:
                        ll.printList();
                        break;
                    
                    default:
                        break;

                }

                System.out.println();
                if (option == 8) {
                    break;
                }

            }
        }
        else {
           System.out.println("Invalid Selection");
        }

    scnr.close();

    }

}



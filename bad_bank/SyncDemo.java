// A Java program to demonstrate working of synchronized. 
import java.io.*; 
import java.util.*; 

// Driver class
//7ms
//6ms
//6ms
//6.33ms
//28ms
//46ms
//32ms
//35.33ms
public class SyncDemo
{ 
    public static void main(String args[]) 
    {
        BankAccount account = new BankAccount(10); //10 cents 
        ThreadedDeposit depositer = new ThreadedDeposit(account); 
        ThreadedWithdraw withdrawer = new ThreadedWithdraw(account);

        long start = System.currentTimeMillis();
  
        // Start two threads
        depositer.start(); 
        withdrawer.start(); 
  
        // wait for threads to end 
        try
        {
			//When we invoke the join() method on a thread, the calling thread goes into a waiting state. It remains in a waiting state until the referenced thread terminates.
			//Source: www.baeldung.com/java-thread-join
            depositer.join(); 
            withdrawer.join(); 
        } 
        catch(Exception e) 
        { 
            System.out.println("Interrupted"); 
        }
        long end = System.currentTimeMillis();
		
		System.out.printf("Amount in account: %,d \u00A2 \n",account.getAmount());
		int expected = 10+5000000-1000000;
		System.out.printf("I expect to have %,d \u00A2 \n",expected);
		System.out.println((end - start)+"ms");
    } 
}
package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE
    @Test
    public void testThreeAddThreeRemove(){
        AListNoResizing<Integer> right = new AListNoResizing<>();
        BuggyAList<Integer> wrong = new BuggyAList<>();
        for (int i = 3; i < 6; i++){
            right.addLast(i);
            wrong.addLast(i);
        }
        for (int j = 0; j < 3; j++){
            assertEquals(right.removeLast(),wrong.removeLast());
        }
    }

    @Test
    public void randomizedTest(){
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> R = new BuggyAList<>();
        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                R.addLast(randVal);
            } else if (operationNumber == 1) {
                // size
                int size = L.size();
                int size1 = R.size();
                assertEquals(size, size1);
            }
            else if (operationNumber == 2){
                if (L.size() != 0 && R.size() != 0){
                    int last = L.getLast();
                    int last1 = R.getLast();
                    assertEquals(last, last1);
                }
                else if (R.size() != 0){
                    int last1 = R.getLast();
                }
                else if (L.size() != 0){
                    int last = L.getLast();
                }
            }
            else{
                if (L.size() != 0 && R.size() != 0){
                    int last = L.removeLast();
                    int last1 = R.removeLast();
                    assertEquals(last, last1);
                }
                else if (R.size() != 0){
                    int last1 = R.removeLast();
                }
                else if (L.size() != 0){
                    int last = L.removeLast();
                }
            }
        }
    }
}

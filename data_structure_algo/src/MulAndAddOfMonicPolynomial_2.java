
/**
 * Merging LinkedList
 * the primary purpose is to test about the foundational data structure like List
 * LinkedList manipulation
 * <p>
 * Similar types of questions: merge / reverse sequential linked list
 * <p>
 * Addition:
 * 3x^4 - 5x^2 + 6x - 2
 * 5x^20 - 7x^4 + 3x
 * <p>
 * Multiplication:
 * or Polynomial product: (a+b)(c+d) = ac + ad + bc + bd
 * <p>
 * INPUT SAMPLE:
 * 4 3 4 -5 2  6 1  -2 0
 * 3 5 20  -7 4  3 1
 * OUTPUT SAMPLE:
 * 15 24 -25 22 30 21 -10 20 -21 8 35 6 -33 5 14 4 -15 3 18 2 -6 1
 * 5 20 -4 4 -5 2 9 1 -2 0
 * <p>
 * 3 means 3 tuples with （Coefficients and terms）
 * <p>
 * Using array is easier but waste of space, using ArrayList is the easiest
 * linked ist will be much better in terms of space complexity
 */
public class MulAndAddOfMonicPolynomial_2 {
    /**
     * System Design:
     * 4 functions:
     * 1. polynomial reader
     * 2. polynomial multiplication
     * 3. polynomial addition
     * 4. polynomial output result
     */
    class ListNode {
        int coefficient;
        int term; // power
        ListNode next;

        ListNode() {
            next = null;
        }

        ListNode(int x, int y) {
            coefficient = x;
            term = y;
            next = null;
        }

        @Override
        public String toString() {
            return "ListNode{" +
                    "coefficient=" + coefficient +
                    ", term=" + term +
                    ", next=" + next +
                    '}';
        }
    }

//    private ListNode polyMul(ListNode p1, ListNode p2) {
//        ListNode result = new ListNode();
//        result.next = new ListNode();
//        ListNode res = result.next;
//        while(p1.next != null && p2.next != null){
//            if(p1.term == p2.term){
//                res.coefficient = p1.coefficient + p2.coefficient;
//                res.term = p1.term;
//            }else if(p1.term < p2.term){
//                res.coefficient = p1.coefficient;
//                res.term = p1.term;
//            }else { // >
//                res.coefficient = p2.coefficient;
//                res.term = p2.term;
//            }
//
//        }
//        return result;
//    }

    private ListNode polyAdd(ListNode p1, ListNode p2) {
        ListNode t1 = p1, t2 = p2;
        ListNode result = new ListNode();
        result.next = new ListNode();
        ListNode res = result.next;

        while (t1 != null && t2 != null) {
            if (t1.term == t2.term) {
                int coe = t1.coefficient + t2.coefficient;
                if (coe == 0) {
                    t1 = t1.next;
                    t2 = t2.next;
                    continue;
                }
                res.coefficient = coe;
                res.term = t1.term;
                t1 = t1.next;
                t2 = t2.next;
            } else if (t1.term > t2.term) {
                res.coefficient = t1.coefficient;
                res.term = t1.term;
                t1 = t1.next;
            } else { // t1 < t2
                res.coefficient = t2.coefficient;
                res.term = t2.term;
                t2 = t2.next;
            }
            res.next = new ListNode();
            res = res.next;
        }

        if (t1 != null) {
            res.next = t1;
        }

        if (t2 != null) {
            res.next = t2;
        }

        return result.next;
    }

    private ListNode polyReader(int size, String in) {
        String[] s = in.split(" ");
        ListNode result = new ListNode();
        result.next = new ListNode();
        ListNode res = result.next;
        for (int i = 0; i < size; i += 2) {
            res.coefficient = Integer.valueOf(s[i]);
            res.term = Integer.valueOf(s[i + 1]);
            // the last node then node.next is null
            res.next = i == size - 2 ? null : new ListNode();
            res = res.next;
        }
        return result.next;
    }

    public static void main(String[] args) {
        ListNode p1, p2, pm, pa;
        MulAndAddOfMonicPolynomial_2 m = new MulAndAddOfMonicPolynomial_2();
        p1 = m.polyReader(4 * 2, "3 4 -5 2 6 1 -2 0");
        p2 = m.polyReader(3 * 2, "5 20 -7 4 3 1");
//        System.out.println(p1);
//        System.out.println(p2);
//        System.out.println(p1);
//        System.out.println(p2);
//        pm = m.polyMul(p1, p2);
        pa = m.polyAdd(p1, p2);
        System.out.println(pa);
    }
}



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Merging LinkedList
 * ##############################################################################
 * 记住！！！
 * DUMMY节点的next指针为空，然后每次用dummy的cp currentNode.next = new Node(..., ...) 赋值
 * Only concerning ATTACH the new node with VALUE !!!
 * instead of adding an empty node by default
 * then messing around with the value inside !!!
 * ##############################################################################
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

    private ListNode polyMul(ListNode p1, ListNode p2) {
        ListNode dummyHead = new ListNode(0, 0);
        ListNode curr = dummyHead;

        ListNode t1 = p1;
        while (p2 != null) {
            if (p1 == null) { // 当p1指针走完就恢复到起点继续和p2匹配
                p1 = t1; // 当p1指针走完就恢复到起点继续和p2匹配
                p2 = p2.next; // 同时p2后移
                continue; // 为了回到循环起始去check p2是否走完了，这里也可以check p2为null就直接break
            }
            int coe = p1.coefficient * p2.coefficient;
            int term = p1.term + p2.term;
            if (coe != 0) { // 仅当系数不为0时才添加节点
                curr.next = new ListNode(coe, term);
                curr = curr.next;
            }
            p1 = p1.next;
        }

        // distinct duplicates
        curr = dummyHead;
        HashMap<Integer, Integer> duplicates = new HashMap<>();
        while (curr != null) {
            if (duplicates.containsKey(curr.term)) { // existed
                duplicates.put(curr.term, duplicates.get(curr.term) + curr.coefficient);
            } else { // REMEMBER to use ELSE, there is no RETURN !!!
                duplicates.put(curr.term, curr.coefficient);
            }
            curr = curr.next;
        }

        // sort the key list and 组装 result in dummy
        List<Integer> lt = new ArrayList<>(duplicates.keySet());
        lt.sort((a, b) -> b - a);

        ListNode dummy = new ListNode(0, 0);
        ListNode cur = dummy;
        for (Integer term : lt) {
            cur.next = new ListNode(duplicates.get(term), term); // attach to next (rear)
            cur = cur.next;
        }

        return dummy.next;
    }

    private ListNode polyAdd(ListNode p1, ListNode p2) {
        ListNode t1 = p1, t2 = p2;
        /** only concerning ATTACH the new node with VALUE !!!
         instead of adding an empty node by default
         then messing around with the value inside !!! **/
        ListNode dummyHead = new ListNode(0, 0);
        ListNode curr = dummyHead;

        while (t1 != null && t2 != null) {
            if (t1.term == t2.term) {
                int coe = t1.coefficient + t2.coefficient;
                if (coe != 0) { // 仅当系数和不为0时才添加节点
                    curr.next = new ListNode(coe, t2.term);
                    curr = curr.next;
                }
                t1 = t1.next;
                t2 = t2.next;
            } else if (t1.term > t2.term) {
                curr.next = new ListNode(t1.coefficient, t1.term);
                curr = curr.next;
                t1 = t1.next;
            } else { // t1 < t2
                curr.next = new ListNode(t2.coefficient, t2.term);
                curr = curr.next;
                t2 = t2.next;
            }
        }

        // rest of nodes either from t1 or t2 adding to the rear of linked list
        curr.next = (t1 != null) ? t1 : t2;
        return dummyHead.next;
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
        pm = m.polyMul(p1, p2);
        pa = m.polyAdd(p1, p2);

        System.out.println("Merging LinkedList: 多项式乘法：");
        System.out.println(pm);
        System.out.println("Merging LinkedList: 多项式加法：");
        System.out.println(pa);
    }
}



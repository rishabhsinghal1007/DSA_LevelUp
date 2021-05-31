import java.util.*;

public class L11_Segregate01 {
    public static Scanner scn = new Scanner(System.in);

    public static class ListNode {
        int val = 0;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    public static ListNode segregate01(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode one = new ListNode(-1);
        ListNode zero = new ListNode(-1);
        ListNode op = one, zp = zero, curr = head;

        while (curr != null) {
            if (curr.val != 0) {
                op.next = curr;
                op = op.next;
            } else {
                zp.next = curr;
                zp = zp.next;
            }
            curr = curr.next;
        }

        zp.next = one.next;
        op.next = null;

        head = zero.next;

        zero.next = one.next = null;
        return head;
    }

    public static void printList(ListNode node) {
        while (node != null) {
            System.out.print(node.val + " ");
            node = node.next;
        }
    }

    public static ListNode createList(int n) {
        ListNode dummy = new ListNode(-1);
        ListNode prev = dummy;
        while (n-- > 0) {
            prev.next = new ListNode(scn.nextInt());
            prev = prev.next;
        }

        return dummy.next;
    }

    public static void main(String[] args) {
        int n = scn.nextInt();
        ListNode h1 = createList(n);
        h1 = segregate01(h1);
        printList(h1);
    }
}

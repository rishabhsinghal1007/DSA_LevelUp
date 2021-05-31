import java.util.*;

public class L5_Unfold {
    public static Scanner scn = new Scanner(System.in);

    public static class ListNode {
        int val = 0;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    public static ListNode reverse(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode curr = head;
        ListNode prev = null;

        while (curr != null) {
            ListNode forw = curr.next;

            curr.next = prev;
            prev = curr;
            curr = forw;
        }
        return prev;
    }

    public static void unfold(ListNode head) {
        if (head == null || head.next == null)
            return;

        ListNode l1 = new ListNode(-1);
        ListNode l2 = new ListNode(-1);
        ListNode p1 = l1, p2 = l2, c1 = head, c2 = head.next;

        while (c1 != null && c2 != null) {
            p1.next = c1;
            p2.next = c2;

            p1 = p1.next;
            p2 = p2.next;

            if (c2 != null) // for odd Len
                c1 = c2.next;
            if (c1 != null) // for even
                c2 = c1.next;
        }

        p1.next = null;
        p2 = reverse(l2.next);
        p1.next = p2;
    }

    // 2nd Method
    public static void unfold2(ListNode head) {
        if (head == null || head.next == null)
            return;

        ListNode l1 = new ListNode(-1);
        ListNode l2 = new ListNode(-1);

        ListNode p1 = l1, p2 = l2;

        p1.next = head;
        p2.next = head.next;

        p1 = p1.next;
        p2 = p2.next;

        while (p2 != null && p2.next != null) {
            ListNode f = p2.next;
            p1.next = f;
            p2.next = f.next;

            p1 = p1.next;
            p2 = p2.next;

        }

        p1.next = null;
        p2 = reverse(l2.next);
        p1.next = p2;
    }

    public static void printList(ListNode node) {
        while (node != null) {
            System.out.print(node.val + " ");
            node = node.next;
        }
    }

    public static void main(String[] args) {
        int n = scn.nextInt();
        ListNode dummy = new ListNode(-1);
        ListNode prev = dummy;
        while (n-- > 0) {
            prev.next = new ListNode(scn.nextInt());
            prev = prev.next;
        }

        ListNode head = dummy.next;
        unfold(head);
        printList(head);
    }

}

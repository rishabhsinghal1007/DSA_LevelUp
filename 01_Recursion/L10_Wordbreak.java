import java.util.*;

public class L10_Wordbreak {
    public static int wordBreak(String str, int idx, String ans, int len, HashSet<String> dict) {
        if (idx >= str.length()) {
            System.out.println(ans);
            return 1;
        }
        int count = 0;
        for (int i = idx; i <= str.length(); i++) {
            String word = str.substring(idx, i);
            if (word.length() > len)
                break;
            if (dict.contains(word)) {
                count += wordBreak(str, i, ans + word + " ", len, dict);
            }
        }
        return count;
    }

    public static void wordBreak(String str, String ans, HashSet<String> dict) {
        int len = 0;
        for (String s : dict)
            len = Math.max(len, s.length());
        wordBreak(str, 0, ans, len, dict);
    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        System.out.print("Enter the length of dictionary : ");
        int n = scn.nextInt();
        System.out.print("Enter the words in dictionary : ");

        HashSet<String> dict = new HashSet<>();
        for (int i = 0; i < n; i++) {
            dict.add(scn.next());
        }
        System.out.print("Enter the String : ");
        String sentence = scn.next();
        System.out.println("Output is : ");
        wordBreak(sentence, "", dict);
    }
}

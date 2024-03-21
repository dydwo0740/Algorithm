import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
    static class Trie{
        TrieNode rootNode = new TrieNode();

        void insert(String[] words){
            TrieNode node = rootNode;

            for (int i = 0; i < words.length; i++) {
                node = node.childs.computeIfAbsent(words[i], s -> new TrieNode());
            }
        }

        void print(TrieNode node, int depth){
            if (node.childs.isEmpty()) {
                return;
            }

            Iterator<String> iter = node.childs.keySet().iterator();

            while (iter.hasNext()) {
                String key = iter.next();
                System.out.println("-".repeat(depth*2) + key);
                print(node.childs.get(key), depth + 1);
            }
        }

        static class TrieNode{
            Map<String, TrieNode> childs = new TreeMap<>();
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        Trie trie = new Trie();

        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());

            int M = Integer.parseInt(st.nextToken());

            String[] word = new String[M];

            for(int j=0;j<M;j++) {
                word[j] = st.nextToken();
            }
            trie.insert(word);
        }


        trie.print(trie.rootNode, 0);


        bw.flush();
        bw.close();
        br.close();
    }
}

import java.io.*;
import java.util.*;

public class Main {

    static class Tree{
        Map<String, Tree> child = new HashMap<>();

        public void insert(String str) {
            Tree tree = this;
            String[] s = str.split("\\\\");
            for(int i=0;i<s.length;i++){
                tree.child.putIfAbsent(s[i], new Tree());
                tree = tree.child.get(s[i]);
            }
        }

        public void print(Tree parent, int depth){
            List<String> list = new ArrayList<>(parent.child.keySet());
            list.sort(Comparator.naturalOrder());
            for (int i = 0; i < list.size(); i++) {
                System.out.println(" ".repeat(depth) + list.get(i));
                print(parent.child.get(list.get(i)), depth + 1);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        Tree root = new Tree();

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());

            root.insert(st.nextToken());
        }

        root.print(root, 0);


        bw.flush();
    }







}
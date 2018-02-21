import java.util.ArrayList;
import java.util.Stack;

// Java program to find kth smallest element in BST

/* Class containing left and right child of current
   node and key value*/
class Node {
    int data;
    Node left, right;
    public Node(int item) {
        data = item;
        left = right = null;
    }
}

class BinaryTree {
  // Root of the Binary Tree
  Node root;

  public BinaryTree() {
      root = null;
  }
  // Only works when tree is BST
  // Recursive O(log(N) + k)
  public int kthSmallestRecursive(Node root, int k) {
    ArrayList<Integer> buffer = new ArrayList<Integer>();
    inorderSearch(root, buffer, k);
    return buffer.get(k-1);
  }

  public void inorderSearch(Node node, ArrayList<Integer> buffer, int k){
      if(buffer.size() >= k)
          return;
      if(node.left != null){
          inorderSearch(node.left, buffer, k);
      }
      buffer.add(node.data);
      if(node.right != null){
          inorderSearch(node.right, buffer, k);
      }
  }

  public int kthSmallestIterative(Node root, int k) {
      // Use stack to traverse tree in order iteratively
      Stack<Node> stack = new Stack<Node>();
      // We need a counter to balance for kth smallest
      int counter = 0;
      // Root node
      Node cur = root;
      // keep going left and add all nodes into stack
      while(cur != null){
          stack.push(cur);
          cur = cur.left;
      }
      // start popping and checking them
      while(!stack.isEmpty()){
          Node ptr=stack.pop();
          counter++;
          if(counter == k)return ptr.data;
          // repeat process for right nodes
          Node rt = ptr.right;
          while(rt != null){
              stack.push(rt);
              rt = rt.left;
          }
      }
      return 0;
  }

  // Can we go beyond O(log(N) + K)?
  /*
  If we can change the BST node structure, We can add a new Integer to mark the number of element in the left sub-tree.
  when the node is not null.

  if k == node.leftNum + 1, return node
  if k > node.leftNum + 1, make k -= node.leftNum + 1, and then node = node.right
  otherwise, node = node.left
  The time complexity of algorithm above will be O(h) = O(log(N)) for BST, h is the height of the input tree.
  */

  /* Driver program to test above functions */
  public static void main(String args[]) {
     BinaryTree tree = new BinaryTree();
     tree.root = new Node(4);
     tree.root.left = new Node(2);
     tree.root.right = new Node(5);
     tree.root.left.left = new Node(1);
     tree.root.left.right = new Node(3);
     int k = 5;
     System.out.printf("%d th smallest element is ", k);
     // int res = tree.kthSmallestRecursive(tree.root, k);
     int res = tree.kthSmallestIterative(tree.root, k);
     System.out.printf("\n%d \n", res);
  }

}

// Java implementation to check if given Binary tree
// is a BST or not

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

    // To keep track of previous node in Inorder Traversal
    Node prev;

    boolean isBST()  {
        prev = null;
        // return isBST2(root);
        return isBST1(root, null, null);
        // return isBST0(root);
        // return isBSTFalse(root);
    }

    /* Wrong but intuitive */
    /* doesn't work for BST ...e.g.
            3
          /  \
        2     5
       / \
      1  4
      Would return true but is NOT a BST
    */
    boolean isBSTFalse(Node root) {
      if (root == null)
        return true;

      /* false if left is > than root */
      if (root.left != null && root.left.data > root.data)
        return false;

      /* false if right is < than root */
      if (root.right != null && root.right.data < root.data)
        return false;

      /* false if, recursively, the left or right is not a BST */
      if (!isBSTFalse(root.left) || !isBSTFalse(root.right))
        return false;

      /* passing all that, it's a BST */
      return true;
    }

    // Returns the min value in a binary tree
    int minValue(Node node) {
        if (node == null)
            return Integer.MAX_VALUE;
        int res = node.data;
        int lres = minValue(node.left);
        int rres = minValue(node.right);
        if (lres < res)
          res = lres;
        if (rres < res)
          res = rres;
        return res;
    }

    // Returns the max value in a binary tree
    int maxValue(Node node) {
        if (node == null)
            return Integer.MIN_VALUE;
        int res = node.data;
        int lres = maxValue(node.left);
        int rres = maxValue(node.right);
        if (lres > res)
            res = lres;
        if (rres > res)
            res = rres;
        return res;
    }

    /* Correct but inefficient O(n^2) */
    boolean isBST0(Node root) {
      if (root == null) {
        return true;
      }

      /* false if the max of the left is > than us */
      if (root.left!=null && maxValue(root.left) > root.data)
        return false;

      /* false if the min of the right is <= than us */
      if (root.right!=null && minValue(root.right) < root.data)
        return false;

      /* false if, recursively, the left or right is not a BST */
      if (!isBST0(root.left) || !isBST0(root.right))
        return false;

      /* passing all that, it's a BST */
      return true;
    }

    /* Correct and efficient O(n) */
    boolean isBST1(Node root, Node left, Node right) {
    // Base condition
    if (root == null)
        return true;

    // if left node exist that check it has
    // correct data or not
    if (left != null && root.data < left.data)
        return false;

    // if right node exist that check it has
    // correct data or not
    if (right != null && root.data > right.data)
        return false;

    // check recursively for every node.
    return isBST1(root.left, left, root) && isBST1(root.right, root, right);
    }

    /* Using inorder traversal O(n) */
    boolean isBST2(Node node) {
        // traverse the tree in inorder fashion and
        // keep a track of previous node
        if (node != null) {
            if (!isBST2(node.left))
                return false;
            // allows only distinct values node
            if (prev != null && node.data <= prev.data )
                return false;
            prev = node;
            return isBST2(node.right);
        }
        return true;
    }

    /* Driver program to test above functions */
    public static void main(String args[]) {
        BinaryTree tree = new BinaryTree();
        // is BST
        tree.root = new Node(4);
        tree.root.left = new Node(2);
        tree.root.right = new Node(5);
        tree.root.left.left = new Node(1);
        tree.root.left.right = new Node(3);

        // is not BST
        // tree.root = new Node(3);
        // tree.root.left = new Node(2);
        // tree.root.right = new Node(5);
        // tree.root.left.left = new Node(1);
        // tree.root.left.right = new Node(4);

        if (tree.isBST())
            System.out.println("IS BST");
        else
            System.out.println("Not a BST");
    }
}

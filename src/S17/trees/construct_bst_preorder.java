import java.util.Stack;

// Java program to construct BST from given preorder traversal
// https://www.geeksforgeeks.org/?p=25203
// https://www.geeksforgeeks.org/construct-bst-from-given-preorder-traversal-set-2/
// A binary tree node
class Node {
    int data;
    Node left, right;

    Node(int d) {
        data = d;
        left = right = null;
    }
}

class Index {
    int index = 0;
}

class BinaryTree {
  /*
    O(N^2) solution: start from root and recursively calculate sub-trees
    For example in {10, 5, 1, 7, 40, 50}, 10 is the first element, so we make it root.
    Now we look for the first element greater than 10, we find 40.
    So we know the structure of BST is as following.

               10
             /    \
            /      \
    {5, 1, 7}       {40, 50}
    We recursively follow above steps for subarrays {5, 1, 7} and {40, 50}, and get the complete tree.
    */

    Index index = new Index();

    // A recursive function to construct BST from pre[]. preIndex is used
    // to keep track of index in pre[].
    Node constructTreeUtil(int pre[], Index preIndex, int key,
            int min, int max, int size) {
        // Base case
        if (preIndex.index >= size) {
            return null;
        }
        Node root = null;
        // If current element of pre[] is in range, then
        // only it is part of current subtree
        if (key > min && key < max) {
            // Allocate memory for root of this subtree and increment preIndex
            root = new Node(key);
            preIndex.index = preIndex.index + 1;

            if (preIndex.index < size) {
                // Contruct the subtree under root
                // All nodes which are in range {min .. key} will go in left
                // subtree, and first such node will be root of left subtree.
                root.left = constructTreeUtil(pre, preIndex, pre[preIndex.index],
                        min, key, size);
                // All nodes which are in range {key..max} will go in right
                // subtree, and first such node will be root of right subtree.
                root.right = constructTreeUtil(pre, preIndex, pre[preIndex.index],
                        key, max, size);
            }
        }
        return root;
    }

    // The main function to construct BST from given preorder traversal.
    // This function mainly uses constructTreeUtil()
    // O(n)
    Node constructTreeRecursive(int pre[], int size) {
        int preIndex = 0;
        return constructTreeUtil(pre, index, pre[0], Integer.MIN_VALUE,
                Integer.MAX_VALUE, size);
    }

    // The main function that constructs BST from pre[]
    Node constructTreeIterative(int pre[], int size) {
        // The first element of pre[] is always root
        Node root = new Node(pre[0]);
        // Use stack to keep track of BST constraint
        Stack<Node> s = new Stack<Node>();
        // Push root
        s.push(root);
        // Iterate through rest of the size-1 items of given preorder array
        for (int i = 1; i < size; ++i) {
            Node temp = null;
            /* Keep on popping while the next value is greater than
             stack's top value. */
            while (!s.isEmpty() && pre[i] > s.peek().data) {
                temp = s.pop();
            }
            // Make this greater value as the right child and push it to the stack
            if (temp != null) {
                temp.right = new Node(pre[i]);
                s.push(temp.right);
            }
            // If the next value is less than the stack's top value, make this value
            // as the left child of the stack's top node. Push the new node to stack
            else {
                temp = s.peek();
                temp.left = new Node(pre[i]);
                s.push(temp.left);
            }
        }
        return root;
    }

    // A utility function to print inorder traversal of a Binary Tree
    void printInorder(Node node) {
        if (node == null) {
            return;
        }
        printInorder(node.left);
        System.out.print(node.data + " ");
        printInorder(node.right);
    }

    void printPreorder(Node node) {
        if (node == null) {
            return;
        }
        System.out.print(node.data + " ");
        printPreorder(node.left);
        printPreorder(node.right);
    }
    /*
    Time Complexity: O(n).
    The complexity looks more from first look.
    If we take a closer look, we can observe that every item is pushed and popped only once.
    So at most 2n push/pop operations are performed in the main loops of constructTree().
    Therefore, time complexity is O(n).
    */
    // Driver program to test above functions
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        int pre[] = new int[]{2,1,3};
        int size = pre.length;
        Node root = tree.constructTreeRecursive(pre, size);
        // Node root = tree.constructTreeIterative(pre, size);
        System.out.println("Inorder traversal of the constructed tree is ");
        tree.printInorder(root);
        System.out.println("\nPreorder traversal of the constructed tree is ");
        tree.printPreorder(root);
        System.out.println("");
    }
}

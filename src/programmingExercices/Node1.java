package programmingExercices;

/* Java program to determine if binary tree is 
height balanced or not */

/* A binary tree node has data, pointer to left child,
and a pointer to right child */
class Node1 
{
 int data;
 Node1 left, right;
 Node1(int d) {
     data = d;
     left = right = null;
 }
}

class BinaryTree {
 Node1 root;

 /* Returns true if binary tree with root as root is height-balanced */
 boolean isBalanced(Node1 node)  {
     int lh; /* for height of left subtree */

     int rh; /* for height of right subtree */

     /* If tree is empty then return true */
     if (node == null)
         return true;

     /* Get the height of left and right sub trees */
     lh = height(node.left);
     rh = height(node.right);

     if (Math.abs(lh - rh) <= 1
             && isBalanced(node.left)
             && isBalanced(node.right)) 
         return true;

     /* If we reach here then tree is not height-balanced */
     return false;
 }

 /* UTILITY FUNCTIONS TO TEST isBalanced() FUNCTION */
 /*  The function Compute the "height" of a tree. Height is the
     number of nodes along the longest path from the root node
     down to the farthest leaf node.*/
 int height(Node1 node) {
     /* base case tree is empty */
     if (node == null)
         return 0;

     /* If tree is not empty then height = 1 + max of left
      height and right heights */
     return 1 + Math.max(height(node.left), height(node.right));
 }

 public static void main(String args[]) {
     BinaryTree tree = new BinaryTree();
     tree.root = new Node1(1);
     tree.root.left = new Node1(2);
     tree.root.right = new Node1(3);
     tree.root.left.left = new Node1(4);
     tree.root.left.right = new Node1(5);
     tree.root.left.left.left = new Node1(8);

     if(tree.isBalanced(tree.root))
         System.out.println("Tree is balanced");
     else
         System.out.println("Tree is not balanced");
 }
}
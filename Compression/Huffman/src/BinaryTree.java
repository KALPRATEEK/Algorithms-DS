
import java.util.function.Consumer;

/**
 * Interface für einen Binärbaum.
 *
 */
public interface BinaryTree<Huffnode> {

    /**
     * Getter für das gespeicherte Element in
     * diesem Knoten.
     * @return Das in diesem Knoten gespeicherte Element
     */
    Huffnode  getData();

    /**
     * Setter für das in diesem Node gespeicherte
     * Element.
     * @param data Das zu speichernde Element
     */
    void setData( Huffnode   data);

    /**
     * Der linke Teilbaum dieses Baumes.
     * @return Der linke <code>BinaryTree</code> dieses Knotens
     */
    BinaryTree< Huffnode  > getLeftNode();

    /**
     * Der rechte Teilbaum dieses Baumes.
     * @return Der rechte <code>BinaryTree</code> dieses Knotens
     */
    BinaryTree< Huffnode  > getRightNode();

    /**
     * <code>true</code>, sollte dieser Knoten ein Blatt sein, d. h. keine children haben
     * @return <code>true</code> falls ein Blatt, <code>false</code> sonst
     */
    boolean isLeaf();

    /**
     * Durchläuft den Baum in preorder und führt pro Knoten den Consumer aus.
     *
     * @param visitor Der <code>Consumer</code>, der für die einzelnen Knoten ausgeführt wird.
     */
    void visitPreOrder(Consumer<BinaryTree< Huffnode  >> visitor);

    /**
     * Durchläuft den Baum in inorder und führt pro Knoten den Consumer aus.
     *
     * @param visitor Der <code>Consumer</code>, der für die einzelnen Knoten ausgeführt wird.
     */
    void visitInOrder(Consumer<BinaryTree< Huffnode  >> visitor);

    /**
     * Durchläuft den Baum in postorder und führt pro Knoten den Consumer aus.
     *
     * @param visitor Der <code>Consumer</code>, der für die einzelnen Knoten ausgeführt wird.
     */
    void visitPostOrder(Consumer<BinaryTree< Huffnode  >> visitor);

}

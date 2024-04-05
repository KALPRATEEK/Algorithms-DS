public class HuffNode {
    int character;
    int frequency;

    public HuffNode(int character, int frequency) {
        this.character = character;
        this.frequency = frequency;
    }

    public int getCharacter() {
        return character;
    }

    public int getFrequency() {
        return frequency;
    }

    @Override
    public String toString() {
        return "HuffNode{" +
                "character=" + character +
                ", frequency=" + frequency +
                '}';
    }
}

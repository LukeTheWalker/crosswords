package YamlStructure;
import java.util.List;

public class PhysicalComposition {
    private Size size;
    private List<Coords> blacks;
    private List<Number> numbers;

    public Size getSize() {
        return size;
    }
    public List<Number> getNumbers() {
        return numbers;
    }
    public void setNumbers(List<Number> numbers) {
        this.numbers = numbers;
    }
    public List<Coords> getBlacks() {
        return blacks;
    }
    public void setBlacks(List<Coords> blacks) {
        this.blacks = blacks;
    }
    public void setSize(Size size) {
        this.size = size;
    }
}

import java.util.List;

public class PhysicalComposition {
    private Size size;
    private Integer n_blacks;
    private List<Coords> blacks;
    private Integer n_numbers;
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
    public Integer getN_numbers() {
        return n_numbers;
    }
    public void setN_numbers(Integer n_numbers) {
        this.n_numbers = n_numbers;
    }
    public List<Coords> getBlacks() {
        return blacks;
    }
    public void setBlacks(List<Coords> blacks) {
        this.blacks = blacks;
    }
    public Integer getN_blacks() {
        return n_blacks;
    }
    public void setN_blacks(Integer n_blacks) {
        this.n_blacks = n_blacks;
    }
    public void setSize(Size size) {
        this.size = size;
    }
}

package interpreter.bytecode;

public interface BranchCode {

    String getLabel();

    void setAddress(int newAddress);

}
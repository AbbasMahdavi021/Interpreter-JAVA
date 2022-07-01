package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;

import java.util.ArrayList;

public class LabelCode extends ByteCode implements BranchCode{

    private String label;

    @Override
    public void init(ArrayList<String> args) {
        label = args.get(1);
    }

    @Override
    public void execute(VirtualMachine vm) {
    }

    public String getLabel() {
        return label;
    }

    @Override
    public void setAddress(int newAddress) {
    }

    @Override
    public void dump(VirtualMachine vm) {
        System.out.println("Label " + label);
    }

}

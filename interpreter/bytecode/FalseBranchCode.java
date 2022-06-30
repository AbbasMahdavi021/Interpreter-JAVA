package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;

import java.util.ArrayList;

public class FalseBranchCode extends ByteCode implements BranchCode{

    private String label;
    private int address;

    @Override
    public void init(ArrayList<String> args) {
        label = args.get(1);
    }

    @Override
    public void execute(VirtualMachine vm) {
        if (vm.popRunTimeStack() == 0) {
            vm.setProgramCounter(address -1);
        }
    }

    @Override
    public void dump(VirtualMachine vm) {
        System.out.println("FALSEBRANCH " + label);

    }

    @Override
    public String getLabel() {
        return label;
    }

    @Override
    public void setAddress(int newAddress) {
        address = newAddress;

    }
}

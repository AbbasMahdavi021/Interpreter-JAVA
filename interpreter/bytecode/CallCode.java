package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;

import java.util.ArrayList;

public class CallCode extends ByteCode implements BranchCode{

    private String label;
    private int address;
    private String x;

    @Override
    public void init(ArrayList<String> args) {
        label = args.get(1);
        x = args.get(1).split("<<", 2)[0];
    }

    @Override
    public void execute(VirtualMachine vm) {
        vm.setProgramCounter();
        vm.setAddress(address -1);
    }

    @Override
    public void dump(VirtualMachine vm) {
        System.out.println("CALL " + label + "\t" + x + "()");
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

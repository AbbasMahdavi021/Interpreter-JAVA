package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;

import java.util.ArrayList;

public class StoreCode extends ByteCode{

    private int offset;
    private int identifiers;
    private String x = "";

    @Override
    public void init(ArrayList<String> args) {
        offset = Integer.parseInt(args.get(1));
        if (args.size() > 2) {
            x = args.get(2);
        }
    }

    @Override
    public void execute(VirtualMachine vm) {
        identifiers = vm.peekRunTimeStack();
        vm.storeRunTimeStack(offset);
    }

    @Override
    public void dump(VirtualMachine vm) {
        System.out.println("STORE " + offset + " " + x + "\t" + x + " = " + identifiers);
    }

}

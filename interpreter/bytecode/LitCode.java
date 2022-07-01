package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;

import java.util.ArrayList;

public class LitCode extends ByteCode{

    private int value;
    private String x = "";

    @Override
    public void init(ArrayList<String> args) {
        value = Integer.parseInt(args.get(1));
        if (args.size() > 2) {
            x = args.get(2);
        }
    }

    @Override
    public void execute(VirtualMachine vm) {
        vm.pushRunTimeStack(value);
    }

    @Override
    public void dump(VirtualMachine vm) {
        System.out.print("LIT " + value);
        if (!x.equals("")) {
            System.out.print("\t" + "int " + x);
        }
        System.out.println();
    }

}
package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;

import java.util.ArrayList;

public class LoadCode extends ByteCode{

    private int offset;

    private String x = "";

    @Override
    public void init(ArrayList<String> args) {
        offset = Integer.parseInt(args.get(1));
        if (args.size() > 2){
            x = args.get(2);
        }
    }

    @Override
    public void execute(VirtualMachine vm) {
        vm.loadRunTimeStack(offset);
    }

    @Override
    public void dump(VirtualMachine vm) {
        System.out.println("LOAD " + offset);
        if (!x.equals("")) {
            System.out.println(" " + x + "\t" + "<load " + x + ">");
        }
        System.out.println();

    }
}

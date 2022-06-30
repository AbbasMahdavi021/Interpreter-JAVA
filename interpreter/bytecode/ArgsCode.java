package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;

import java.util.ArrayList;

public class ArgsCode extends ByteCode{

    private int n;

    @Override
    public void init(ArrayList<String> args) {
        n = Integer.parseInt(args.get(1));
    }

    @Override
    public void execute(VirtualMachine vm) {
        vm.newFrameAt(n);
    }

    @Override
    public void dump(VirtualMachine vm) {
        System.out.println("ARGS " + n);
        vm.setArguments(n);
    }
}
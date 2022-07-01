package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;

import java.util.ArrayList;

public class ArgsCode extends ByteCode{

    private int value;

    @Override
    public void init(ArrayList<String> args) {
        value = Integer.parseInt(args.get(1));
    }

    @Override
    public void execute(VirtualMachine vm) {
        vm.newFrameAt(value);
    }

    @Override
    public void dump(VirtualMachine vm) {
        System.out.println("ARGS " + value);
        vm.loadArgs();
    }

}
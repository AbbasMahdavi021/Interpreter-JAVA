package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;

import java.util.ArrayList;

public class PopCode extends ByteCode {

    private int n;

    @Override
    public void init(ArrayList<String> args) {
        n = Integer.parseInt(args.get(1));
    }

    @Override
    public void execute(VirtualMachine vm) {
        for (int i = 0; i < n; i++) {
            vm.popRunTimeStack();
        }
    }

    @Override
    public void dump(VirtualMachine vm) {
        System.out.println("POP " + n);
    }
}

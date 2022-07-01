package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;

import java.util.ArrayList;

public class PopCode extends ByteCode {

    private int N;

    @Override
    public void init(ArrayList<String> args) {
        N = Integer.parseInt(args.get(1));
    }

    @Override
    public void execute(VirtualMachine vm) {
        for (int i = 0; i < N; i++) {
            vm.popRunTimeStack();
        }
    }

    @Override
    public void dump(VirtualMachine vm) {
        System.out.println("POP " + N);
    }
}

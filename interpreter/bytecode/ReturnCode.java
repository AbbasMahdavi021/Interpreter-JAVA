package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;

import java.util.ArrayList;

public class ReturnCode extends ByteCode{

    private String function = "";
    private String baseID = "";

    @Override
    public void init(ArrayList<String> args) {
        if (args.size() > 1) {
            function = args.get(1);
            baseID = args.get(1).split("<<" , 2) [1];
        }
    }

    @Override
    public void execute(VirtualMachine vm) {
        vm.popFrame();
        vm.returnProgramCounter();
    }

    @Override
    public void dump(VirtualMachine vm) {
        int top = vm.peekRunTimeStack();
        System.out.println("RETURN " + function + "  EXIT " + baseID + ": " + top);
    }
}
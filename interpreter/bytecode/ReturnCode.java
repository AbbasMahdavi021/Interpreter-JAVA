package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;

import java.util.ArrayList;

public class ReturnCode extends ByteCode{

    private String funnction = "";
    private String baseID = "";

    @Override
    public void init(ArrayList<String> args) {
        if (args.size() > 1) {
            funnction = args.get(1);
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
        System.out.println("RETURN " + funnction + "\t" + "exit " + baseID + ":");
        vm.setArguments(2);
        vm.printArguments();
        System.out.println();
    }
}

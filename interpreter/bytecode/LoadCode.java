package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;

import java.util.ArrayList;

public class LoadCode extends ByteCode{

    private int offset;
    private String identifier = "";

    @Override
    public void init(ArrayList<String> args) {
        offset = Integer.parseInt(args.get(1));
        if (args.size() > 2){
            identifier = args.get(2);
        }
    }

    @Override
    public void execute(VirtualMachine vm) {
        vm.loadRunTimeStack(offset);
    }

    @Override
    public void dump(VirtualMachine vm) {
        System.out.print("LOAD "+offset);
        if (!("".equals(identifier))){
            System.out.println(" "+ identifier + "\t" + "<load "+ identifier +">");
        }else{
            System.out.println();
        }
    }
}

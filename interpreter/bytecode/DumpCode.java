package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;

import java.util.ArrayList;

public class DumpCode extends ByteCode{

    private boolean isDump;

    @Override
    public void init(ArrayList<String> args) {
        isDump = args.get(1).equals("ON");
    }

    @Override
    public void execute(VirtualMachine vm) {
        vm.setDump(isDump);
    }

    @Override
    public void dump(VirtualMachine vm) {
    }

}

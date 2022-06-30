package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;

import java.util.ArrayList;

public class BopCode extends ByteCode{

    private String op;

    @Override
    public void init(ArrayList<String> args) {
        op = args.get(1);
    }

    @Override
    public void execute(VirtualMachine vm) {

        int val2 = vm.popRunTimeStack();
        int val1 = vm.popRunTimeStack();

        if ("+".equals(op)){
            vm.pushRunTimeStack(val1 + val2);
        }
        else if ("-".equals(op)){
            vm.pushRunTimeStack(val1 - val2);
        }
        else if ("/".equals(op)){
            vm.pushRunTimeStack(val1/val2);
        }
        else if ("*".equals(op)){
            vm.pushRunTimeStack(val1*val2);
        }
        else if ("==".equals(op)){
            vm.pushRunTimeStack((val1 == val2) ? 1 : 0);
        }
        else if ("!=".equals(op)){
            vm.pushRunTimeStack((val1 != val2) ? 1 : 0);
        }
        else if ("<=".equals(op)){
            vm.pushRunTimeStack((val1 <= val2) ? 1 : 0);
        }
        else if (">".equals(op)){
            vm.pushRunTimeStack((val1 > val2) ? 1 : 0);
        }
        else if (">=".equals(op)) {
            vm.pushRunTimeStack((val1 >= val2) ? 1: 0);
        }
        else if ("<".equals(op)) {
            vm.pushRunTimeStack((val1 < val2) ? 1 : 0);
        }
        else if ("|".equals(op)){
            vm.pushRunTimeStack((val1 != 0) || (val2 != 0) ? 1:0 );
        }
        else if ("&".equals(op)){
            vm.pushRunTimeStack((val1 != 0) && (val2 != 0) ? 1:0 );
        }
    }

    @Override
    public void dump(VirtualMachine vm) {
        System.out.println("BOP " + op);

    }
}

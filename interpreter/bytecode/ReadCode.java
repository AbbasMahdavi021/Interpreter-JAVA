package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;

import java.util.ArrayList;
import java.util.Scanner;

public class ReadCode extends ByteCode{
    @Override
    public void init(ArrayList<String> args) {

    }

    @Override
    public void execute(VirtualMachine vm) {

        int userInput;
        boolean flag = true;
        while (flag){


            Scanner obj = new Scanner(System.in);

            System.out.print("Please enter integer: ");
            String value = obj.nextLine();
            try{
                userInput = Integer.parseInt(value);
                vm.pushRunTimeStack(userInput);
                flag = false;
                break;

            }catch(NumberFormatException e){
                System.err.println("Invalid Input.");
            }
        }
    }

    @Override
    public void dump(VirtualMachine vm) {
        System.out.println("READ");
    }
}

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
        while (true){

            Scanner obj = new Scanner(System.in);

            System.out.print("Please enter an integer: ");
            String value = obj.nextLine();
            try{
                userInput = Integer.parseInt(value);
                vm.pushRunTimeStack(userInput);
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

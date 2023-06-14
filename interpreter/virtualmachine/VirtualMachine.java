package interpreter.virtualmachine;

import interpreter.bytecode.ByteCode;
import interpreter.bytecode.DumpCode;
import interpreter.bytecode.HaltCode;
import interpreter.bytecode.LabelCode;

import java.util.Stack;

public class VirtualMachine {

    private RunTimeStack   runTimeStack;
    private Stack<Integer> returnAddress;
    private Program        program;
    private int            programCounter;
    private boolean        isRunning;
    private boolean        dumpingIsON;



    public VirtualMachine(Program program) {
        this.program = program;
    }
    public void notRunning() {
        isRunning = false;
    }
    public void setDump(boolean on) {
        this.dumpingIsON = on;
    }
    public void loadArgs() {
        this.runTimeStack.setArguments();
    }



    public int popRunTimeStack() {
        return this.runTimeStack.pop();
    }
    public int peekRunTimeStack() {
        return this.runTimeStack.peek();
    }
    public void pushRunTimeStack(Integer value) {
        this.runTimeStack.push(value);
    }
    public void storeRunTimeStack(int offsetFromFramePointer) {
        this.runTimeStack.store(offsetFromFramePointer);
    }
    public void loadRunTimeStack(int offsetFromFramePointer) {
        this.runTimeStack.load(offsetFromFramePointer);
    }
    public void newFrameAt(int offsetFromFramePointer) {
        this.runTimeStack.newFrameAt(offsetFromFramePointer);
    }
    public void popFrame() {
        this.runTimeStack.popFrame();
    }



    public void pushAddress(int value) {
        this.returnAddress.push(value);
    }
    public void setProgramCounter() {
        this.pushAddress(programCounter);
    }
    public void setAddress(int programCounter) {
        this.programCounter = programCounter;
    }
    public void returnAddress() {
        this.setAddress( returnAddress.pop());
    }



    public void executeProgram() {

        programCounter = 0;
        runTimeStack = new RunTimeStack();
        returnAddress = new Stack<>();
        isRunning = true;
        dumpingIsON = false;          //Switch to true , to turn on dumping function

        while (isRunning) {
            ByteCode code = program.getCode(programCounter);
            code.execute(this);

            if (dumpingIsON && !(code instanceof DumpCode)) {
                //do not dump LabelCode or Halt in the output.
                if (!LabelCode.class.equals(code.getClass()) && !HaltCode.class.equals(code.getClass())){
                    code.dump(this);
                    runTimeStack.dump();
                }
            }
            programCounter++;
        }
    }
}
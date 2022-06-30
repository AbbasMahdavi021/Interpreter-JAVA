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
    private boolean isDumping;

    public VirtualMachine(Program program) {
        this.program = program;
    }

    public void notRunning() {
        isRunning = false;
    }

    public void executeProgram() {

        programCounter = 0;
        runTimeStack = new RunTimeStack();
        returnAddress = new Stack<Integer>();
        isRunning = true;
        isDumping = true;

        while (isRunning) {
            ByteCode code = program.getCode(programCounter);
            code.execute(this);

            // DumpCode does not get dumped and does not dump runStack state
            if (isDumping && !(code instanceof DumpCode)) {
                if (!LabelCode.class.equals(code.getClass()) && !HaltCode.class.equals(code.getClass())){
                    code.dump(this); // Dumping byte code happens after execute
                    runTimeStack.dump(); // Used to dump runstack state.
                }
            }
            programCounter++;
        }
    }

    public void setDump(boolean on) {
        isDumping = on;
    }

    public void setArguments(int count) {
        runTimeStack.setArguments(count);
    }

    public void printArguments() {
        runTimeStack.printArguments();
    }

    public int popRunTimeStack() {
        return runTimeStack.pop();
    }

    public int peekRunTimeStack() {
        return runTimeStack.peek();
    }

    public void pushRunTimeStack(Integer value) {
        runTimeStack.push(value);
    }

    public void storeRunTimeStack(int offsetFromFramePointer) {
        runTimeStack.store(offsetFromFramePointer);
    }

    public void loadRunTimeStack(int offsetFromFramePointer) {
        runTimeStack.load(offsetFromFramePointer);
    }

    public void newFrameAt(int offsetFromFramePointer) {
        runTimeStack.newFrameAt(offsetFromFramePointer);
    }

    public void popFrame() {
        runTimeStack.popFrame();
    }





    public void pushProgramCounter(int value) {
        returnAddress.push(value);
    }

    public void returnProgramCounter() {
        if (!returnAddress.isEmpty()) {
            setProgramCounter( returnAddress.pop());
        }
    }

    public void saveProgramCounter() {
        pushProgramCounter(programCounter);
    }

    public void setProgramCounter(int programCounter) {
        this.programCounter = programCounter;
    }

}

package interpreter.virtualmachine;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class RunTimeStack {

    private List<Integer>  runTimeStack;
    private Stack<Integer> framePointer;
    private int arguments;

    public RunTimeStack() {
        runTimeStack = new ArrayList<>();
        framePointer = new Stack<>();
        // Add initial Frame Pointer, main is the entry
        // point of our language, so its frame pointer is 0.
        framePointer.add(0);
    }

    public void dump() {
        int pos = 0;
        if (framePointer.size() > 1) {
            for (int i = 1; i < framePointer.size(); i++) {
                System.out.print(runTimeStack.subList(pos, framePointer.get(i)) + " ");
                pos = framePointer.get(i);
            }
        }
        System.out.println(runTimeStack.subList(pos, runTimeStack.size()));
    }

    private int lastIndex(){
        return Math.max(runTimeStack.size() -1,0);
    }

    int peek(){
        return this.runTimeStack.get(lastIndex());
    }

    int push(int value) {
        runTimeStack.add(value);
        return value;
    }

    int pop() {
        if (!runTimeStack.isEmpty()) {
            return runTimeStack.remove(lastIndex());
        }
        return 0;
    }

    public int store(int offsetFromFramePointer) {
        int top = pop();
        runTimeStack.set(framePointer.peek()+offsetFromFramePointer, top);
        return top;
    }

    public int load(int offsetFromFramePointer) {
        int newFrame = runTimeStack.get(framePointer.peek());
        return push(newFrame + offsetFromFramePointer);
    }

    public void newFrameAt(int offsetFromTopOfRunStack) {
        int range = runTimeStack.size();
        framePointer.push( range -offsetFromTopOfRunStack);
    }

    public void popFrame () {
        if(!framePointer.isEmpty()){
            int topVal = pop();
            int frameVal = framePointer.pop();

            for (int i = lastIndex(); i >= frameVal; i--) {
                pop();
            }

            push(topVal);
        }
    }

    public void setArguments(int x) {
        arguments = x;
    }

    public void printArguments() {
        int size = runTimeStack.size();

        for (int i = 0; i < arguments; i++) {
            System.out.print(size - arguments + i);
            if (i != arguments - 1) {
                System.out.print(", ");
            }
        }
    }
}

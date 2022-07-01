package interpreter.virtualmachine;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class RunTimeStack {

    private final List<Integer>  runTimeStack;
    private final Stack<Integer> framePointer;

    public RunTimeStack() {
        runTimeStack = new ArrayList<>();
        framePointer = new Stack<>();
        framePointer.add(0);
    }

    public void setArguments () {
    }

    public void dump() {
        int getIndex = 0;
        if (framePointer.size() > 1) {
            for (int i = 1; i < framePointer.size(); i++) {
                System.out.print(runTimeStack.subList(getIndex, framePointer.get(i)) + " ");
                getIndex = framePointer.get(i);
            }
        }
        System.out.println(runTimeStack.subList(getIndex, runTimeStack.size()));
    }

    private int lastIndex(){
        return Math.max(this.runTimeStack.size() -1,0);
    }

    int peek(){
        return this.runTimeStack.get(lastIndex());
    }

    void push(int value) {
        this.runTimeStack.add(value);
    }

    int pop() {
        if (!runTimeStack.isEmpty()) {
            return this.runTimeStack.remove(lastIndex());
        }
        return 0;
    }

    public void store(int offsetFromFramePointer) {
        int top = pop();
        this.runTimeStack.set(framePointer.peek()+offsetFromFramePointer, top);
    }

    public void load(int offsetFromFramePointer) {
        int newFrame = this.runTimeStack.get(framePointer.peek());
        push(newFrame + offsetFromFramePointer);
    }

    public void newFrameAt(int offsetFromTopOfRunStack) {
        int range = this.runTimeStack.size();
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

}

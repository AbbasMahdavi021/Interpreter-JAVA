package interpreter.virtualmachine;

import interpreter.bytecode.BranchCode;
import interpreter.bytecode.ByteCode;
import interpreter.bytecode.LabelCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Program {
    private final List<ByteCode> program;

    public Program(ArrayList<ByteCode> args) {
        program = args;
    }

    protected ByteCode getCode(int programCounter) {
        return this.program.get(programCounter);
    }

    public void resolveAddress() {

        HashMap<String, Integer> addressOfLabel = new HashMap<>();

        for (int i = 0; i < program.size(); i++) {
            if (program.get(i) instanceof LabelCode) {
                addressOfLabel.put(((LabelCode) program.get(i)).getLabel(), i);
            }
        }

        for (ByteCode bc : program) {
            if (bc instanceof BranchCode) {
                ((BranchCode) bc).setAddress(addressOfLabel.get(((BranchCode) bc).getLabel()));
            }
        }
    }
}
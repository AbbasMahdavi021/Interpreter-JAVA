package interpreter.virtualmachine;

import interpreter.bytecode.BranchCode;
import interpreter.bytecode.ByteCode;
import interpreter.bytecode.LabelCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Program {
    private List<ByteCode> program;

    public Program() {
        program = new ArrayList<>();
    }

    public Program(ArrayList<ByteCode> args) {
        program = args;
    }

    protected ByteCode getCode(int programCounter) {
        return this.program.get(programCounter);
    }

    /**
     * This function should go through the program and resolve all addresses.
     * Currently all labels look like LABEL <<num>>>, these need to be converted into
     * correct addresses so the VirtualMachine knows what to set the Program Counter
     * HINT: make note what type of data-structure ByteCodes are stored in.
     * **** METHOD SIGNATURE CANNOT BE CAHNGED *****
     */
    public void resolveAddress() {

        HashMap<String, Integer> addresses = new HashMap<>();

        for (int i = 0; i < program.size(); i++) {
            if (program.get(i) instanceof LabelCode) {
                addresses.put(((LabelCode) program.get(i)).getLabel(), i);
            }
        }

        for (ByteCode bc : program) {
            if (bc instanceof BranchCode) {
                ((BranchCode) bc).setAddress(addresses.get(((BranchCode) bc).getLabel()));
            }
        }
    }
}

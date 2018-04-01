package kernel.cmd;

import kernel.IMicroprocessor;

public class CMD_NULL implements ICommand {

    private String arg;

    public CMD_NULL(String arg) {
        this.arg = arg.toUpperCase();
    }

    @Override
    public void execute(IMicroprocessor microprocessor) {

    }

    @Override
    public int getSize() {
        return 1;
    }

    @Override
    public String getName() {
        return "(no command)";
    }
}
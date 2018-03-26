package kernel.cmd;

import kernel.IMicroprocessor;

public interface ICommand {
    void execute(IMicroprocessor microprocessor);
    int getSize();
    String getName();
}

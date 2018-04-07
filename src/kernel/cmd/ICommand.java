package kernel.cmd;

import kernel.IMicroprocessorAdapterForCommands;

public interface ICommand {
    void execute(IMicroprocessorAdapterForCommands microprocessor);
    int getSize();
    String getName();
}

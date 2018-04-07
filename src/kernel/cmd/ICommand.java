package kernel.cmd;

import kernel.IMicroprocessorCommandsAdapter;

public interface ICommand {
    void execute(IMicroprocessorCommandsAdapter microprocessor);
    int getSize();
    String getName();
}

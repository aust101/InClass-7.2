package com.overaitis.inclass_7_2.command;

import com.overaitis.inclass_7_2.command.sources.CommandResult;
import com.overaitis.inclass_7_2.command.sources.CommandSource;

public interface Command {
    CommandResult execute(CommandSource src, String[] args);
}

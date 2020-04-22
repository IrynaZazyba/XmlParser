package by.jwd.xmlparser.controller.command;

import by.jwd.xmlparser.controller.command.impl.*;

import java.util.HashMap;
import java.util.Map;

public final class CommandProvider {

    private final static CommandProvider instance = new CommandProvider();

    private final Map<CommandName, Command> repository = new HashMap<>();

    private CommandProvider() {
        repository.put(CommandName.PARSE_XML, new ParseXML());
        repository.put(CommandName.START_PAGE, new StartPage());
    }


    public static CommandProvider getInstance() {
        return instance;
    }


    public Command getCommand(String name) {
        CommandName commandName;
        Command command;

        if (name == null) {
            command = repository.get(CommandName.START_PAGE);
        } else {

            commandName = CommandName.valueOf(name.toUpperCase());
            command = repository.get(commandName);

            if (command == null) {
                command = repository.get(CommandName.START_PAGE);
            }
        }
        return command;
    }
}

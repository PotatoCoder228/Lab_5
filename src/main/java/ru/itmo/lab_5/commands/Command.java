package ru.itmo.lab_5.commands;

import ru.itmo.lab_5.console.Console;

public abstract class Command implements ExecuteCommand{
    protected String nameOfCommand;
    protected String description;
}

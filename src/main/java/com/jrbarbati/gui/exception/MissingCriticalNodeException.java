package com.jrbarbati.gui.exception;

public class MissingCriticalNodeException extends RuntimeException
{
    public MissingCriticalNodeException(String message)
    {
        super(message);
    }

    public MissingCriticalNodeException(String format, Object... args)
    {
        this(String.format(format, args));
    }
}

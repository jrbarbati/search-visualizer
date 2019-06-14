package com.jrbarbati.gui.exception;

public class BoundsException extends RuntimeException
{
    public BoundsException(String message)
    {
        super(message);
    }

    public BoundsException(String format, Object... args)
    {
        this(String.format(format, args));
    }
}

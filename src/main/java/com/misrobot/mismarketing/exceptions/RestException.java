package com.misrobot.mismarketing.exceptions;

import com.misrobot.mismarketing.base.RestCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by susun on 2016/10/15.
 */
@SuppressWarnings("unused")
public class RestException extends Exception {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass().getName());
    private int code;
    private String description;
    private RestCommand command;

    public RestException() {

    }

    public RestException(RestCommand command) {
        this.code = command.getStatusCode();
        this.description = command.getStatusDescription();
        this.command = command;
        LOGGER.error(String.format(
                "Request command %s failed with error code %d, description: %s",
                this.command, this.code, this.description));
    }

    public RestException(int code,String descr) {
        this.code = code;
        this.description = descr;
    }

    public RestException(String code,String descr) {
        this.code = Integer.parseInt(code);
        this.description = descr;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getExceptionCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return description;
    }

    public RestCommand getCommand() {
        return command;
    }

    public void setCommand(RestCommand command) {
        this.command = command;
    }
}


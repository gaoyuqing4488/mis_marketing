package com.misrobot.mismarketing.base;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.misrobot.mismarketing.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

/**
 * Created by GYQ on 2017/7/26.
 */
public class RestCommand {
    private static final ObjectMapper OBJECTMAPPER = new ObjectMapper();
    private static final Properties ERROR_CODES = new Properties();

    private ObjectNode command;

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass().getName());

    static {
        InputStream appProperties = RestCommand.class.getResourceAsStream("/errorcode.properties");
        try {
            ERROR_CODES.load(appProperties);
        } catch (IOException e) {
            LoggerFactory.getLogger(RestCommand.class.getName()).error(e.getMessage(), e);
        }
    }

    public static RestCommand[] parse(String jsonString) {
        try {
            JsonNode nodes = OBJECTMAPPER.readTree(jsonString);
            if (nodes.isObject()) {
                return parse(nodes);
            }
        } catch (IOException e) {

        }
        return null;
    }

    public static RestCommand[] parse(JsonNode nodes) {
        ArrayList<RestCommand> commands = new ArrayList<RestCommand>();
        for (JsonNode node : nodes) {
            commands.add(new RestCommand(node));
        }
        return commands.toArray(new RestCommand[commands.size()]);
    }

    public final void setStatusCode(int errorCode) {
        String message;
        command.put("errcode", String.valueOf(errorCode));
        message = ERROR_CODES.getProperty(String.valueOf(errorCode));
        if (message == null) {
            message = "Unknown Error Code";
        }
        command.put("errdesc", message);
    }

    public RestCommand() {
        command = OBJECTMAPPER.createObjectNode();
    }

    public RestCommand(String cmd) {
        this();
        command.put("command", cmd);
    }

    public RestCommand(JsonNode cmd) {
        this();
        if (cmd instanceof ObjectNode) {
            command.setAll((ObjectNode) cmd);
        }
    }

    public RestCommand(String cmd, int status) {
        this();
        command.put("command", cmd);
        setStatusCode(status);
    }

    public String getCommand() {
        return command.get("command").asText();
    }

    public void setCommand(String cmd) {
        command.put("command", cmd);
    }

    public String getStatusDescription() {
        if (command.has("errdesc")) {
            return command.get("errdesc").asText();
        }
        return "";
    }

    public void setStatusDescription(String status) {
        command.put("errdesc", status);
    }

    public int getStatusCode() {
        if (command.has("errcode")) {
            return Integer.valueOf(command.get("errcode").asText());
        }
        return 0;
    }

    public void setParameter(String value) {
        command.setAll((ObjectNode) JsonUtil.getJsonNode(value));
    }

    public void setParameter(ObjectNode value) {
        command.setAll(value);
    }

    public void setParameter(JsonNode value) {
        command.setAll((ObjectNode) value);
    }

    public void setParameter(String name, String value) {
        command.put(name, value);
    }

    public void setParameter(String name, int value) {
        command.put(name, value);
    }

    public void setParameter(String name, JsonNode value) {
        if (value instanceof ArrayNode) {
            command.putArray(name).addAll((ArrayNode) value);
        } else {
            command.putObject(name).setAll((ObjectNode) value);
        }
    }

    public String getParameter(String name) {
        String value = "";
        try {
            if (null != command.get(name)) {
                value = command.get(name).asText();
            }
        } catch (Exception e) {
            LOGGER.error(String.format("Command [%s] was missing parameter [%s].",
                    getCommand(), name));
            return null;
        }
        return value;
    }

    public JsonNode getParameterAsJsonNode(String name) {
        if (null != command.get(name)) {
            return command.get(name);
        }
        return null;
    }

    public JsonNode toJsonObject() {
        return command;
    }
}

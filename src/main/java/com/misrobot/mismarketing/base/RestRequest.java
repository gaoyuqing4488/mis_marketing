package com.misrobot.mismarketing.base;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by GYQ on 2017/7/26.
 */
public class RestRequest {
    private final static Logger logger = LoggerFactory.getLogger(RestRequest.class.getName());
    private final static ObjectMapper mapper = new ObjectMapper();
    private List<RestCommand> restCommands;

    public RestRequest() {
        restCommands = new ArrayList<RestCommand>();
    }

    public RestRequest(RestCommand command) {
        this();
        if (command != null) {
            restCommands.add(command);
        }
    }

    public RestRequest(RestCommand[] commands) {
        this();
        if (commands == null) return;
        Collections.addAll(restCommands, commands);
    }

    public List<RestCommand> getRestCommands() {
        return restCommands;
    }

    public JsonNode toJsonObject() {
        int index = 0;
        ObjectNode node = mapper.createObjectNode();
        for (RestCommand command : restCommands) {
            node.putObject(String.valueOf(++index)).setAll((ObjectNode) command.toJsonObject());
        }
        return node;
    }

    public String toString() {
        JsonNode node = this.toJsonObject();
        if (null != node) {
            return node.toString();
        }
        return "";
    }
}

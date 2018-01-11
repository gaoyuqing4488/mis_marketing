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
public class RestResponse {
    private static final ObjectMapper OBJECTMAPPER = new ObjectMapper();

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass().getName());
    private List<RestCommand> restCommands;

    public RestResponse() {
        restCommands = new ArrayList<RestCommand>();
    }

    public RestResponse(RestCommand command) {
        this();
        restCommands.add(command);
    }

    public RestResponse(RestCommand[] commands) {
        this();
        Collections.addAll(restCommands, commands);
    }

    public List<RestCommand> getRestCommands() {
        return restCommands;
    }

    public JsonNode toJsonObject() {
        int index = 0;
        ObjectNode resp = OBJECTMAPPER.createObjectNode();
        for (RestCommand cmd : restCommands) {
            resp.putObject(String.valueOf(++index)).setAll((ObjectNode) cmd.toJsonObject());
        }
        return resp;
    }

    @Override
    public String toString() {
        JsonNode node = toJsonObject();
        if (node != null) {
            return node.toString();
        }
        return "";
    }
}

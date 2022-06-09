package it.univr.webapp.exception;

import com.fasterxml.jackson.annotation.JsonIgnore;
import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class AgentNotFoundException extends RuntimeException implements GraphQLError {
    private String agentCode;

    public AgentNotFoundException(String agentCode) {
        this.agentCode = agentCode;
    }

    @Override
    public String getMessage() {
        return "Agent with agentCode " + agentCode + " could not be found";
    }

    @Override
    @JsonIgnore
    public StackTraceElement[] getStackTrace() {
        return super.getStackTrace();
    }

    @Override
    public List<SourceLocation> getLocations() {
        return null;
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.ValidationError;
    }

    @Override
    public Map<String, Object> getExtensions() {
        return Collections.singletonMap("agentCode", agentCode);
    }
}

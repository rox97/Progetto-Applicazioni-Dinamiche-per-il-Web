package it.univr.webapp.exception;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonIgnore;
import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

public class OrderNotFoundException extends RuntimeException implements GraphQLError {
    private int ordNum;

    public OrderNotFoundException(int ordNum) {
        this.ordNum = ordNum;
    }

    @Override
    public String getMessage() {
        return "Order with ordNum " + ordNum + " could not be found";
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
        return Collections.singletonMap("ordNum", ordNum);
    }
}

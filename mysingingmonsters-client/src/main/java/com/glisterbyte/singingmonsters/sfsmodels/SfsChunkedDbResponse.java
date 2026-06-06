package com.glisterbyte.singingmonsters.sfsmodels;

import java.lang.reflect.Field;
import java.util.List;

public abstract class SfsChunkedDbResponse extends DbResponse {

    IllegalStateException exception() {
        return new IllegalStateException(
                "Chunked response type '" + this.getClass().getName()
                        + "' should have exactly one default-instantiated list field"
        );
    }

    private Field getChunkedField() throws IllegalStateException {
        Field[] fields = this.getClass().getDeclaredFields();
        if (fields.length != 1) throw exception();
        return fields[0];
    }

    public List<SfsModel> getChunkedList() {

        try {
            Field field = getChunkedField();
            Object object = field.get(this);
            if (!(object instanceof List<?> list)) throw exception();
            if (!list.stream().allMatch(SfsModel.class::isInstance)) throw exception();

            @SuppressWarnings("unchecked")
            List<SfsModel> castedList = (List<SfsModel>)list;

            return castedList;
        }
        catch (IllegalAccessException | IllegalStateException ex) {
            throw new RuntimeException(ex);
        }

    }

    public String getChunkedPropertyKey() throws IllegalStateException {
        return getChunkedField().getName();
    }

}
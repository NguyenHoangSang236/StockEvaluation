package com.stock_trading.evaluation.utils.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

@Component
public class ValueParsingUtil {
    public Date convertFromSecondsToDate(long second) {
        return new Date(second * 1000);
    }


    public Date convertFromSecondsToDate(int second) {
        return new Date(second * 1000L);
    }


    public long convertFromDateToSeconds(Date date) {
        return date.getTime() / 1000;
    }


    public Map<String, Object> convertObjectToMap(Object object) {
        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.convertValue(object, Map.class);
    }


    public <T> T convertMapToObject(Map<String, Object> map, Class<T> tClass) {
        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.convertValue(map, tClass);
    }
}

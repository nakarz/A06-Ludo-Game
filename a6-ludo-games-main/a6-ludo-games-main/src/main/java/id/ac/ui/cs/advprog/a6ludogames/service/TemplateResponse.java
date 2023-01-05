package id.ac.ui.cs.advprog.a6ludogames.service;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class TemplateResponse {
    public Map<Object, Object> responseSuccess(Object obj){
        Map<Object, Object> map = new HashMap<>();
        map.put("error", false);
        map.put("message", "SUCCESS");
        map.put("data", obj);
        return map;
    }

    public Map<Object, Object> responseError(Object obj){
        Map<Object, Object> map = new HashMap<>();
        map.put("error", true);
        map.put("message", obj);

        return map;
    }
}

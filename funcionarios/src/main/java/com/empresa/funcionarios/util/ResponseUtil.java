/**
 * 
 */
package com.empresa.funcionarios.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Vinicius
 *
 */
public class ResponseUtil {
    public static Map<String, String> getResponse(String msg, String level, String erro) {
        Map<String, String> map = new HashMap<>();
        map.put("mensagem", msg);
        map.put("level", level);
        map.put("erro", erro);
        return map;
    }
}

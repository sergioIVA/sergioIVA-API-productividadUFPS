package util;

import com.google.gson.Gson;
import spark.ResponseTransformer;

/**
 *
 * @author Ingeniero sergio
 */
public class JsonTransformer implements ResponseTransformer {

    @Override
    public String render(Object data) throws Exception {
        return new Gson().toJson(data);
    }
    
}

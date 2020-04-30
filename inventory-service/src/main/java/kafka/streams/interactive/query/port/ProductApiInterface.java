package kafka.streams.interactive.query.port;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

public interface ProductApiInterface {

    @RequestMapping(value = "add", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    void addProduct(@RequestParam("name") String name, @RequestParam("brand") String brand, @RequestParam("price") Long price);

    @RequestMapping(value = "delete", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    void deleteProduct(String id);
}


package hungthinh.htid.rest;


import hungthinh.htid.model.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@FeignClient(name = "user", url = "https://profile-demo-ht.herokuapp.com/", configuration = {ClientConfiguration.class})
public interface InfoUserClient {

    @RequestMapping(method = RequestMethod.GET, value = "api/profile/nha")
    Object getUser();

}

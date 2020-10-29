package hungthinh.htid.service;

import hungthinh.htid.model.UserResponse;
import hungthinh.htid.rest.InfoUserClient;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserService {
    private final ApplicationContext applicationContext;

    public UserService(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }
//    private final InfoUserClient infoUserClient;
//
//    public UserService(InfoUserClient infoUserClient) {
//        this.infoUserClient = infoUserClient;
//    }

    public Object getUser() {
        InfoUserClient infoUserClient = applicationContext.getBean(InfoUserClient.class);
        return infoUserClient.getUser();
    }
}

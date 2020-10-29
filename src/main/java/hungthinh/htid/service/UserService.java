package hungthinh.htid.service;

import hungthinh.htid.model.UserResponse;
import hungthinh.htid.rest.InfoUserClient;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final InfoUserClient infoUserClient;

    public UserService(InfoUserClient infoUserClient) {
        this.infoUserClient = infoUserClient;
    }

    public UserResponse getUser() { return infoUserClient.getUser();}
}

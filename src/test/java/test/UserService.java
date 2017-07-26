package test;

import com.game.annotion.HandlerModel;
import com.game.net.CmdBase;

/**
 * Created by 文江 on 2017/7/26.
 */
@HandlerModel(moder = "1")
public interface UserService {

    @CmdBase(cmdId = 2)
    public String login();

}

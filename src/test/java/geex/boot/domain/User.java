package geex.boot.domain;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;


/**
 * //TODO description.
 *
 * @author JuChen
 * @date 2019/3/4
 */
@Data
@Table(name = "USER")
public class User {
    @Id
    private Integer nId;

    /**
     * UUID
     */
    private String cUid;

    private String cIdNo;

    /**
     * 手机号码
     */
    private String cPhoneNum;

    /**
     * 邮箱
     */
    private String cMail;

    private String cName;
}

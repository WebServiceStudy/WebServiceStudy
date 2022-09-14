package com.wss.webservicestudy.web.domain.user.entity;

import com.wss.webservicestudy.web.common.enums.Auth;
import com.wss.webservicestudy.web.common.enums.Gender;
import com.wss.webservicestudy.web.common.enums.LoginType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Builder
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userIdx;       /** 고유 시퀀스 */

    @Column(nullable = false)
    private String email;       /** 이메일 */

    @Column(nullable = false)
    private String passwd;      /** 비밀번호 */

    private String nm;          /** 이름 */

    @Column(nullable = false, length = 20)
    private String nickNm;      /** 닉네임 */

    private String birth;       /** 생년월일 */

    private String town;        /** 주소 - 도시 */

    private String adrLineOne;  /** 주소 - 동 */

    private String adrLineTwo;  /** 주소 - 기타 나머지 */

    private String phone;       /** 핸드폰 */

    private String profile;     /** 프로필 사진 */

    @Column(length = 1)
    private String snsYn;       /** sms 동의 */

    @Enumerated(EnumType.STRING)
    private LoginType loginType;   /** 접속 경로*/

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Auth auth;

    public User() {}

    @Builder
    public User(Long userIdx, String email, String passwd, String nm, String nickNm, String birth, String town, String adrLineOne, String adrLineTwo, String phone, String profile, String snsYn, LoginType loginType, Gender gender, Auth auth) {
        this.userIdx = userIdx;
        this.email = email;
        this.passwd = passwd;
        this.nm = nm;
        this.nickNm = nickNm;
        this.birth = birth;
        this.town = town;
        this.adrLineOne = adrLineOne;
        this.adrLineTwo = adrLineTwo;
        this.phone = phone;
        this.profile = profile;
        this.snsYn = snsYn;
        this.loginType = loginType;
        this.gender = gender;
        this.auth = auth;
    }

    /**
     * enum 항목
     * gender           - 성별
     * auth             - 권한
     * loginType - 접속 경로
     *
     * Base Entity 항목
     * createDate       - 생성일
     * UpdateDate       - 업데이트일
     * lastDate         - 마지막 접속일
     */

}

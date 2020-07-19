package com.yeongjae.damoim.domain.member.entity;

import com.yeongjae.damoim.global.jpa.JpaBasePersistable;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

/**
 * @NoArgsConstructor(access = AccessLevel.PROTECTED)
 * => 객체 생성 시 안전성 보장 ( 외부에서 해당 생성자 접근을 제한 )
 *
 * @AttributeOverride
 * => 추상 클래스에서 정의한 컬럼명을 그대로 상속받는 것이 아니고 컬럼명을 지정
 *
 * @DynamicUpdate
 * => 수정된 데이터 대해서만 update를 실행하는 쿼리를 실행하여 데이터 전송량 낭비를 줄인다.
 */

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Table(name = "tbl_member")
@Entity
@AttributeOverride(name = "id", column = @Column(name = "member_id"))
@Where(clause = "deleted=0")
@DynamicUpdate
public class Member extends JpaBasePersistable implements Serializable {

    @Column(unique = true, name = "email", length = 50, nullable = false)
    private String email;
    @Column(name = "nick_name", length = 30, nullable = false)
    private String nickName;
    @Column(name = "password" , length = 65)
    private String password;
    @Column(name = "location", length = 50)
    private String location;
    @Column(unique = true, name = "phone", length = 20, nullable = false)
    private String phone;
    @Column(name = "sex", length = 5, nullable = false)
    private String sex;
    @Column(name = "imagePath")
    private String imagePath;
    @Column(name = "isVerified", nullable = false, columnDefinition = "BIT default 0")
    private Boolean isVerified = false;
    @Column(name = "birth", nullable = false, length = 20)
    private String birth;
    @Column(name = "fcmToken")
    private String fcmToken;
    @Column(name = "role", nullable = false)
    private String role;
    @Column(name = "provider", length = 100)
    private String provider;

    @Builder
    public Member(final String email,
                  final String nickName,
                  final String password,
                  final String location,
                  final String phone,
                  final String sex,
                  final String imagePath,
                  final String birth,
                  final Boolean isVerified,
                  final String role,
                  final String provider) {
        this.email = email;
        this.nickName = nickName;
        this.password = password;
        this.location = location;
        this.phone = phone;
        this.sex = sex;
        this.imagePath = imagePath;
        this.birth = birth;
        this.isVerified = isVerified;
        this.role = role;
        this.provider = provider;
    }

    public void updateToken(String token) {
        this.fcmToken = token;
    }
}

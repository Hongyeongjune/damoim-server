package com.yeongjae.damoim.global.jpa;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @MappedSuperclass
 * => 객체의 입장에서 공통 매핑 정보가 필요할 때 사용한다.
 * => id는 객체의 입장에서 볼 때 계속 나온다.
 * => 이렇게 공통 매핑 정보가 필요할 때, 부모 클래스에 선언하고 속성만 상속 받아서 사용하고 싶을 때 사용한다.
 *
 * @EntityListeners(AuditingEntityListener.class)
 * => JPA를 사용하여 도메인을 관계형 DataBase 테이블에 매핑할 때 공통적으로 도메인들이 가지고 있는 필드나 컬럼들이 존재한다.
 * => 생성일자, 수정일자, 생성자, 수정자 컬럼에 값을 자동으로 넣어주는 기능이다.
 *
 * @EqualsAndHashCode(of="id", callSuper = false)
 * => equals : 두 객체의 내용이 같은지, 동등성(equality)을 비교하는 연산자
 * => hashCode : 두 객체가 같은 객체인지, 동일성(identity)을 비교하는 연산자
 * => 자바 Bean에서 동등한 비교를 위해 equals와 hashcode 메소드를 오버라이딩해서 사용하는데, 이 어노테이션을 사용하면 자동으로 이 메소드를 생성한다.
 * => callSuper 속성을 통해 메소드 자동 생성 시 부모 클래스의 필드까지 감안하는지의 여부를 설정할 수 있다.
 * => of 속성을 사용하면 명시적으로 특정 필드를 포함하게 한다.
 *
 *
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@EqualsAndHashCode(of="id", callSuper = false)
@Getter
public class JpaBasePersistable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(name = "deleted", nullable = false, columnDefinition = "BIT default 0")
    protected Boolean deleted = false;

    @CreatedBy
    @Column(name = "created_by", updatable = false, length = 64)
    protected String createdBy;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    protected LocalDateTime createdAt = null;

    @LastModifiedBy
    @Column(name = "last_modified_by", length = 64)
    protected String lastModifiedBy = "";

    @LastModifiedDate
    @Column(name = "last_modified_at", nullable = false)
    protected LocalDateTime lastModifiedAt = null;

    public void delete(){
        deleted = true;
    }
}

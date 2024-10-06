//package promaxject.what_tv.user.domain.user;
//
//
//import com.group.what_tv.board.domain.entity.TimeEntity;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//
//import javax.persistence.*;
//
//@AllArgsConstructor         // Repository를 주입하기 위해 사용합니다.
//@NoArgsConstructor          // 파라미터가 없는 기본 생성자를 추가하는 어노테이션. ( JPA 사용을위해 기본 생성자 생성은 필수 ) 쓰기 싫음. 기본 생성자 추가
//@Builder                    // 빌더 패턴 클래스를 사용해주는 어노테이션, setter보다 안정성 있음.
//@Getter                     // Getter를 자동 생성해주는 어노테이션
//@Entity                     // 객체를 테이블과 매핑 할 엔티티라고 JPA에게 알려주는 역할을 하는 어노테이션 JPA가 관리하며 엔티티 클래스라고 함.
//public class User extends TimeEntity {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(nullable = false, length = 20, unique = true)
//    private String username;
//
//    @Column(nullable = false, length = 300)
//    private String password;
//
//    @Column(nullable = false, length = 255)
//    private String email;
//
//    @Enumerated(EnumType.STRING)
//    @Column(nullable = false)
//    private Role role; // 유저인지, 관리자인지 확인
//}

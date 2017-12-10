package spring.boot.core.domain;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 用户持久层操作接口
 */
public interface UserRepository extends JpaRepository<User, Long> {

}

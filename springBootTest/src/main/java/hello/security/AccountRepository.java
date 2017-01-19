package hello.security;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by sren on 17-1-18.
 */
public interface AccountRepository extends JpaRepository<Account, String> {


    public Account findByUsername(String username);

}

package id.ac.ui.cs.advprog.a6ludogames.repository;

import id.ac.ui.cs.advprog.a6ludogames.model.User;
import id.ac.ui.cs.advprog.a6ludogames.model.UserFollow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserFollowRepository extends JpaRepository<UserFollow, Integer> {
    UserFollow findOneByFollowingAndFollowersAndStatus(User following,User followers , boolean status);
}

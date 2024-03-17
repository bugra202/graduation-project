package com.bugratasdemir.graduationproject.repository;

import com.bugratasdemir.graduationproject.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query(nativeQuery = true, value = """
                SELECT
                    c.restaurant_id,
                    AVG(CASE c.rate
                        WHEN 'ONE' THEN 1
                        WHEN 'TWO' THEN 2
                        WHEN 'THREE' THEN 3
                        WHEN 'FOUR' THEN 4
                        WHEN 'FIVE' THEN 5
                    END) AS avg_rate
                FROM comment c
                GROUP BY c.restaurant_id
""")
    List<Object[]> findAverageRateByRestaurantId();
}

package study.querydsl;

import static org.assertj.core.api.Assertions.*;

import com.querydsl.jpa.impl.JPAQueryFactory;
import java.awt.event.MouseWheelEvent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import study.querydsl.entity.Hello;
import study.querydsl.entity.QHello;

@SpringBootTest
@Transactional
@Commit
class QuerydslApplicationTests {

	@PersistenceContext
	EntityManager entityManager;

	@Test
	void contextLoads() {
		Hello hello = new Hello();
		entityManager.persist(hello);

		JPAQueryFactory query = new JPAQueryFactory(entityManager);
		QHello qhello = QHello.hello;
		Hello result = query.selectFrom(qhello)
				.fetchOne();

		assertThat(result).isEqualTo(hello);
		assertThat(result.getId()).isEqualTo(hello.getId());
	}

}

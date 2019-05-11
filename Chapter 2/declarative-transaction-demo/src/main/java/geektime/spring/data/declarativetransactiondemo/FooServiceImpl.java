package geektime.spring.data.declarativetransactiondemo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
public class FooServiceImpl implements FooService {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private FooService fooService;

    /**
     * 前提：insertRecord()开启了事务，执行插入AAA，调用insertThenRollback()，插入BBB
     * 1.Propagation.NESTED 嵌套事务时：外边抛出异常，外边事务及子事务都会回滚；子事务抛出异常，不会回滚外边的事务；
     * 2.Propagation.REQUIRES_NEW 开启新事务：两个独立的事务不会相互影响，无论是外边抛异常还是里面的抛异常，都只是回滚自己的事务
     *
     * @throws RollbackException
     */
    @Override
    @Transactional
    public void insertRecord() throws RollbackException {
        jdbcTemplate.execute("INSERT INTO FOO (BAR) VALUES ('AAA')");
        fooService.insertThenRollback();
//        throw new RuntimeException();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = RollbackException.class)
    public void insertThenRollback() throws RollbackException {
        jdbcTemplate.execute("INSERT INTO FOO (BAR) VALUES ('BBB')");
        throw new RollbackException();
    }

    @Override
    public void invokeInsertThenRollback() throws RollbackException {
        insertThenRollback();
    }
}

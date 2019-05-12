package geektime.spring.springbucks.jpademo;

import geektime.spring.springbucks.jpademo.model.Coffee;
import geektime.spring.springbucks.jpademo.repository.CoffeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Iterator;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class JpaDemoApplicationTests {

    @Autowired
    private CoffeeRepository coffeeRepository;

    @Test
    public void contextLoads() {
        Iterable<Coffee> list = coffeeRepository.queryIdByOrderByIdDesc();
//        Iterator<Coffee> iterator = list.iterator();
//        while (iterator.hasNext()) {
//            log.info("查询结果：{}", iterator.next());
//        }
        list.forEach(i -> {
            log.info("查询结果：{}", i);
        });
    }

}


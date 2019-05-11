package geektime.spring.data.declarativetransactiondemo;

public interface FooService {
    void insertRecord() throws RollbackException;
    void insertThenRollback() throws RollbackException;
    void invokeInsertThenRollback() throws RollbackException;
}

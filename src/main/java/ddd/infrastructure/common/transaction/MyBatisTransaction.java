package ddd.infrastructure.common.transaction;

import java.io.InputStream;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import ddd.application.common.exception.DDDSystemException;
import ddd.infrastructure.common.config.DDDConfig;
import ddd.infrastructure.common.config.DDDDB;
import ddd.interfaces.ResponseStatus;

/**
 * MyBatisトランザクション
 */
public class MyBatisTransaction implements TransactionIF {

    /** MyBatisトランザクション_エラー001 */
    private static final String ERROR_REPOSITORY_TRANSACTION_001 = "error.repository.transaction.001";

    /** MyBatisトランザクション_エラー002 */
    private static final String ERROR_REPOSITORY_TRANSACTION_002 = "error.repository.transaction.002";

    /** MyBatis設定ファイル名 */
    private static final String MYBATIS_CONFIG_NAME = "mybatis-config.xml";

    private static SqlSessionFactory updFactory;

    private static SqlSessionFactory refFactory;

    @Inject
    private MyBatisTransaction() throws DDDSystemException {
        updFactory = loadUpdSessionFactory();
        refFactory = loadRefSessionFactory();
    }

    private static SqlSessionFactory loadUpdSessionFactory() throws DDDSystemException {
        if (updFactory != null) {
            return updFactory;
        }
        try (InputStream mybatisStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(MYBATIS_CONFIG_NAME);) {
            updFactory = new SqlSessionFactoryBuilder().build(
                    mybatisStream, DDDDB.DDD_UPD.value(), DDDConfig.getDDDProperties());
            return updFactory;
        } catch (Exception e) {
            throw new DDDSystemException(ERROR_REPOSITORY_TRANSACTION_001, ResponseStatus.INTERNAL_SERVER_ERROR.value(), e);
        }
    }

    private static SqlSessionFactory loadRefSessionFactory() throws DDDSystemException {
        if (refFactory != null) {
            return refFactory;
        }
        try (InputStream mybatisStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(MYBATIS_CONFIG_NAME);) {
            refFactory = new SqlSessionFactoryBuilder().build(
                    mybatisStream, DDDDB.DDD_REF.value(), DDDConfig.getDDDProperties());
            return refFactory;
        } catch (Exception e) {
            throw new DDDSystemException(ERROR_REPOSITORY_TRANSACTION_002, ResponseStatus.INTERNAL_SERVER_ERROR.value(), e);
        }
    }

    @Override
    public SqlSessionFactory createUpdSessionFactory() throws DDDSystemException {
        return updFactory;
    }

    @Override
    public SqlSessionFactory createRefSessionFactory() throws DDDSystemException {
        return refFactory;
    }

}

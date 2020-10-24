package ddd.infrastructure.common.transaction;

import org.apache.ibatis.session.SqlSessionFactory;

import ddd.application.common.exception.DDDSystemException;

/**
 * トランザクションIF
 */
public interface TransactionIF {

    /**
     * 更新セッションファクトリー作成
     *
     * @return セッションファクトリー
     * @throws DDDSystemException システム例外
     */
    SqlSessionFactory createUpdSessionFactory() throws DDDSystemException;

    /**
     * 参照セッションファクトリー作成
     *
     * @return セッションファクトリー
     * @throws DDDSystemException システム例外
     */
    SqlSessionFactory createRefSessionFactory() throws DDDSystemException;
}

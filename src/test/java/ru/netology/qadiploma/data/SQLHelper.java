package ru.netology.qadiploma.data;

import lombok.val;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import lombok.SneakyThrows;
import ru.netology.qadiploma.data.tables.CreditRequestEntity;
import ru.netology.qadiploma.data.tables.OrderEntity;
import ru.netology.qadiploma.data.tables.PaymentEntity;

public class SQLHelper {
    private static Connection conn;
    static String url = System.getProperty("db.url");
    static String user = System.getProperty("db.login");
    static String password = System.getProperty("db.pass");


    private static final String creditSQLQuery = "SELECT * FROM credit_request_entity WHERE created IN (SELECT max(created) " +
            "FROM credit_request_entity);";
    private static final String orderSQLQuery = "SELECT * FROM order_entity WHERE created IN (SELECT max(created) " +
            "FROM order_entity);";
    private static final String paymentSQLQuery = "SELECT * FROM payment_entity WHERE created IN (SELECT max(created) " +
            "FROM payment_entity);";

    @SneakyThrows
    private static Connection getConnection() {
        try {
            conn = DriverManager.getConnection(url, "app", "pass");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    @SneakyThrows
    public static void cleanDatabase() {
        val runner = new QueryRunner();
        runner.update(getConnection(), "DELETE FROM credit_request_entity;");
        runner.update(getConnection(), "DELETE FROM payment_entity;");
        runner.update(getConnection(), "DELETE FROM order_entity;");
    }

    @SneakyThrows
    public static PaymentEntity getLastPaymentRecord() {
        val runner = new QueryRunner();
        return runner.query(getConnection(), paymentSQLQuery, new BeanHandler<>(PaymentEntity.class));
    }

    @SneakyThrows
    public static CreditRequestEntity getLastCreditRecord() {
        val runner = new QueryRunner();
        return runner.query(getConnection(), creditSQLQuery, new BeanHandler<>(CreditRequestEntity.class));
    }

    @SneakyThrows
    public static OrderEntity getLastOrderRecord() {
        val runner = new QueryRunner();
        return runner.query(getConnection(), orderSQLQuery, new BeanHandler<>(OrderEntity.class));
    }
}
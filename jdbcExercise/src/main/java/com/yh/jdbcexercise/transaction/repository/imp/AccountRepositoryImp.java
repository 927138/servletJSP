package com.yh.jdbcexercise.transaction.repository.imp;

import com.yh.jdbcexercise.transaction.domain.Account;
import com.yh.jdbcexercise.transaction.repository.AccountRepository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class AccountRepositoryImp implements AccountRepository {

     @Override
     public Optional<Account> findByAccountNumber(Connection connection, long accountNumber){
          //todo 계좌-조회
          String sql = "select account_number, name, balance from jdbc_account where account_number = ? ";
          ResultSet rs = null;
          try(PreparedStatement psmt = connection.prepareStatement(sql)){
               psmt.setLong(1,accountNumber);
               rs = psmt.executeQuery();
               if(rs.next()){
                    return Optional.of(new Account(rs.getLong("account_number"), rs.getString("name"),rs.getLong("balance")));
               }
          } catch (SQLException e) {
               throw new RuntimeException(e);
          }finally {
               try {
                    rs.close();
               } catch (SQLException e) {
                    throw new RuntimeException(e);
               }
          }
          return Optional.empty();
     }

     @Override
     public int save(Connection connection, Account account) {
          String sql = "insert into jdbc_account values (?, ?, ?)";

          try (PreparedStatement psmt = connection.prepareStatement(sql)){
               psmt.setLong(1, account.getAccountNumber());
               psmt.setString(2, account.getName());
               psmt.setLong(3, account.getBalance());

               return psmt.executeUpdate();
          } catch (SQLException e){
               throw new RuntimeException(e);
          }
     }

     @Override
     public int countByAccountNumber(Connection connection, long accountNumber) {
          int count = 0;
          String sql = "select count(*) as cnt from jdbc_account where account_number=?";

          ResultSet rs = null;
          try (PreparedStatement psmt = connection.prepareStatement(sql)){

               psmt.setLong(1, accountNumber);
               rs = psmt.executeQuery();
               if(rs.next()){
                    count = rs.getInt(1);
               }

          }catch (SQLException e){
               throw new RuntimeException(e);
          } finally {
               try {
                    rs.close();
               } catch (SQLException e){
                    throw new RuntimeException(e);
               }
          }

          return count;
     }

     @Override
     public int deposit(Connection connection, long accountNumber, long amount) {
          String sql = "update jdbc_account set balance=balance+? where account_number=? ";

          try (PreparedStatement psmt = connection.prepareStatement(sql)){
               psmt.setLong(1, amount);
               psmt.setLong(2, accountNumber);
               int result = psmt.executeUpdate();
               return result;
          }catch (SQLException e){
               throw new RuntimeException(e);
          }
     }

     @Override
     public int withdraw(Connection connection, long accountNumber, long amount) {
          String sql = "update jdbc_account set balance=balance-? where account_number=?";
          try(PreparedStatement psmt = connection.prepareStatement(sql)){
               psmt.setLong(1, amount);
               psmt.setLong(2, accountNumber);
               int result = psmt.executeUpdate();
               return result;
          } catch (SQLException e) {
               throw new RuntimeException(e);
          }
     }

     @Override
     public int deleteByAccountNumber(Connection connection, long accountNumber) {
          String sql = "delete from jdbc_account where account_number=?";
          try(PreparedStatement psmt = connection.prepareStatement(sql)){
               psmt.setLong(1,accountNumber);
               int result = psmt.executeUpdate();
               return result;
          } catch (SQLException e) {
               throw new RuntimeException(e);
          }
     }
}

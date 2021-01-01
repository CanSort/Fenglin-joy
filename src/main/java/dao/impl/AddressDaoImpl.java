package dao.impl;

import dao.AddressDao;
import entity.Address;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import utils.JDBCUtils;

import java.util.List;

public class AddressDaoImpl implements AddressDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<Address> findAddressByUserId(int user_id) {
        //通过用户id找到地址表
        try {
            String sql = "select * from tb_address where user_id = ?";
            List<Address> a = template.query(sql, new BeanPropertyRowMapper<Address>(Address.class),user_id);
            return a;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Address findAddressByAddressId(int address_id) {
        //通过地址id找到地址表
        try {
            String sql = "select * from tb_address where address_id = ?";
            Address a = template.queryForObject(sql, new BeanPropertyRowMapper<Address>(Address.class),address_id);
            return a;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void addAddress(Address a) {
        //新增地址
        try {
            String sql = "insert into tb_address(user_id,user_name,user_phone,user_address) values(?,?,?,?)";
            template.update(sql,a.getUser_id(),a.getUser_name(),a.getUser_phone(),a.getUser_address());
        } catch (DataAccessException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delAddressById(int aid2) {
        //删除地址
        try {
            String sql = "delete from tb_address where address_id = ?";
            template.update(sql,aid2);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }
}

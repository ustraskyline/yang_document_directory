package jdbc;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;

public class UserDaoImpl implements UserDao {
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public int addUser(User user) {
		String sql = "insert into t_user(username, password) values (?, ?)";
		Object[] obj = new Object[] {
				user.getUsername(),
				user.getPassword()
		};
		
		int flag = this.jdbcTemplate.update(sql, obj);
		return flag;
	}

	@Override
	public int updateUser(User user) {
		String sql = "update t_user set username=?, password = ? where userid=?";
		Object[] params = new Object[] {
				user.getUsername(),
				user.getPassword(),
				user.getUserid()
		};
		
		int flag = this.jdbcTemplate.update(sql, params);
		return flag;
	}

	@Override
	public int deleteUserById(int id) {
		String sql = "delete from t_user where userid = ?";
		int flag = this.jdbcTemplate.update(sql, id);
		return flag;
	}

	@Override
	public User findUserById(int id) {
		String sql = "select * from t_user where userid = ?";
		//使用Spring提供的默认实现类ParameterizedBeanPropertyRowMapper将结果集通过反射
		//映射到Java对象中, 此类要求数据表的列必须和Java对象的属性对应
		RowMapper<User> rowMapper = ParameterizedBeanPropertyRowMapper.newInstance(User.class);
		
		//返回单行记录
		return this.jdbcTemplate.queryForObject(sql, rowMapper, id);
	}

	@Override
	public List<User> findAllUser() {
		String sql = "select * from t_user";
		RowMapper<User> rowMapper = ParameterizedBeanPropertyRowMapper.newInstance(User.class);
		
		//返回值是一个集合
		return this.jdbcTemplate.query(sql, rowMapper);
	}

}

package com.zdk.user.service.Impl;


import com.zdk.user.entity.po.UserPo;
import com.zdk.user.mapper.UserDao;
import com.zdk.user.service.UserService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * (User)表服务实现类
 *
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public UserPo queryById(Long id) {
        return this.userDao.queryById(id);
    }


    /**
     * 分页查询
     *
     * @param user 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
//    @Override
//    public Page<User> queryByPage(User user, PageRequest pageRequest) {
//        long total = this.userDao.count(user);
//        return new PageImpl<>(this.userDao.queryAllByLimit(user, pageRequest), pageRequest, total);
//    }

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public UserPo insert(UserPo user) {
        this.userDao.insert(user);
        return user;
    }

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public UserPo update(UserPo user) {
        this.userDao.update(user);
        return this.queryById(user.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.userDao.deleteById(id) > 0;
    }
}

package DongYu.WebBase.System.Service;


import DongYu.WebBase.System.Entity.SysBase.Sorte;
import DongYu.WebBase.System.Entity.SysBase.WebMessage;
import DongYu.WebBase.System.Entity.User;

import java.util.List;

public interface UserService {
	
	public Long getCount(User record);

	public List<User> findAllByRoleIds(User record, List<Long> roleIds);

	public List<User> findAllByRoleIds(User record, Long roleId, Integer type);

	public List<User> findPage(User record, Integer start, Integer limit, Sorte[] sortes);
	
	public WebMessage<User[]> save(User[] records);

	public WebMessage<User> delete(User[] records);

	public List<User>  getDifferent(List<User> list1, List<User> list2);



}
